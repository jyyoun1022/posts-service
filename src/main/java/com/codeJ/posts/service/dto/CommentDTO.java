package com.codeJ.posts.service.dto;

import com.codeJ.posts.service.entity.Comment;
import com.codeJ.posts.service.entity.Posts;
import com.codeJ.posts.service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        private User user;
        private Posts posts;

        public Comment dtoToEntity(){
            Comment comments = Comment.builder()
                    .id(id)
                    .comment(comment)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .user(user)
                    .posts(posts)
                    .build();
            return comments;
        }
    }

    public static class Response{

        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        private String nickname;
        private Long userId;
        private Long postsId;

        /** Entity -> Dto*/
        public Response(Comment comment){
            this.id= comment.getId();
            this.comment=comment.getComment();
            this.createdDate= comment.getCreatedDate();
            this.modifiedDate=comment.getModifiedDate();
            this.nickname=comment.getUser().getNickname();
            this.userId = comment.getUser().getId();
            this.postsId = comment.getPosts().getId();
        }
    }
}
