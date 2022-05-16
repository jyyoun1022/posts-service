package com.codeJ.posts.service.service;

import com.codeJ.posts.service.dto.CommentDTO;
import com.codeJ.posts.service.entity.Comment;
import com.codeJ.posts.service.entity.Posts;
import com.codeJ.posts.service.entity.User;
import com.codeJ.posts.service.repository.CommentRepository;
import com.codeJ.posts.service.repository.PostsRepository;
import com.codeJ.posts.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    /** CREATE */
    @Transactional
    public Long save(Long id, String nickname, CommentDTO.Request dto){
        User user = userRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시글이 존재하지 않습니다.+id: " + id));
        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.dtoToEntity();
        commentRepository.save(comment);

        return comment.getId();
    }
    /** READ */
    @Transactional(readOnly = true)
    public List<CommentDTO.Response> findAll(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        List<Comment> comments = posts.getComments();
        return comments.stream().map(i->new CommentDTO.Response(i)).collect(Collectors.toList());
    }

    /** UPDATE */
    @Transactional
    public void update(Long id,CommentDTO.Request dto){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id: " + id));
        comment.update(dto.getComment());
    }
    /** DELETE */
    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        commentRepository.delete(comment);
    }
}
