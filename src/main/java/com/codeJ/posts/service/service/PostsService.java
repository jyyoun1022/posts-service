package com.codeJ.posts.service.service;

import com.codeJ.posts.service.dto.PostsDTO;
import com.codeJ.posts.service.entity.Posts;
import com.codeJ.posts.service.entity.User;
import com.codeJ.posts.service.repository.PostsRepository;
import com.codeJ.posts.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    /** Create */
    @Transactional
    public Long save(PostsDTO.Request  dto,String nickname){
        //User 정보를 가져와 dto에 담아준다.
        User user = userRepository.findByNickname(nickname);
        dto.setUser(user);
        log.info("PostsService save() 실행");
        Posts posts = dto.dtoToEntity();
        postsRepository.save(posts);

        return posts.getId();
    }

    /** Read 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public PostsDTO.Response findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostsDTO.Response(posts);
    }

    /** UPDATE (dirty cheicking 영속성 컨텍스트)
     * User 객체를 영속화시키고, 영속화된 User 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional
    public void update(Long id,PostsDTO.Request dto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.id= " + id));

        posts.update(dto.getTitle(),dto.getContent());

    }

    /** DELTE */
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id= " + id));
        postsRepository.delete(posts);
    }
    /** Views Counting */
    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }

    /** paging and sort*/
    @Transactional(readOnly = true)
    public Page<Posts> pageList(Pageable pageable){
        return postsRepository.findAll(pageable);
    }

    /** search */
    @Transactional(readOnly = true)
    public Page<Posts> search(String keyword,Pageable pageable){
        Page<Posts> postsList = postsRepository.findByTitleContaining(keyword, pageable);

        return postsList;
    }

}
