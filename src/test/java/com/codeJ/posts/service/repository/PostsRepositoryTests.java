package com.codeJ.posts.service.repository;

import com.codeJ.posts.service.entity.Posts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostsRepositoryTests {

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void reset(){
        postsRepository.deleteAll();
    }
//    @AfterEach
//    public void clear(){
//        postsRepository.deleteAll();
//    }
    @Test
    public void 게시글_생성(){
        //give
        Posts posts = Posts.builder()
                .title("Test_Title")
                .content("Test_Content")
                .writer("codeJ")
                .build();
        //when
        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();
        Posts post = postsList.get(0);
        //then

        assertThat(posts.getTitle()).isEqualTo("Test_Title");
        assertThat(posts.getContent()).isEqualTo("Test_Content");

        System.out.println(post);



    }
}
