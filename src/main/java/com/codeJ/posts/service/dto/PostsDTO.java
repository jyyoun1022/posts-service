package com.codeJ.posts.service.dto;

import com.codeJ.posts.service.entity.Posts;
import com.codeJ.posts.service.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/** request, response DTO 클래스에 하나로 묶어 InnerStaticClass로 한 번에 정리 */
public class PostsDTO {

    /** 게시물의 등록과 수정을 처리할 요청(Request) 클래스*/
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String title;
        private String writer;
        private String content;
        private String createdDate,modifiedDate;
        private int view;
        private User user;

        /** DTO -> Entity*/
        public Posts dtoToEntity(){
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .view(0)
                    .user(user)
                    .build();
            return posts;
        }
    }

    /** 게시글 정보를 리턴할 응답클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지*/
    @Getter
    public static class Response{
        private Long id;
        private String title;
        private String writer;
        private String content;
        private String createdDate,modifiedDate;
        private int view;
        private Long userId;
        private List<CommentDTO.Response> comments;

        /** Entity -> DTO*/
        public Response(Posts posts){
            this.id= posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.userId = posts.getUser().getId();
            this.comments = posts.getComments().stream().map(i->new CommentDTO.Response(i)).collect(Collectors.toList());
        }
    }


}
