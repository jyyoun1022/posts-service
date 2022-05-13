package com.codeJ.posts.service.dto;

import com.codeJ.posts.service.entity.Role;
import com.codeJ.posts.service.entity.User;
import lombok.*;

import java.io.Serializable;

public class UserDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private Long id;

        private String username;

        private String password;

        private String nickname;

        private String email;

        private Role role;

        public User dtoToEntity(){
            User user = User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(role.USER)
                    .build();
            return user;
        }
    }
    /** 인증된 사용자정보를 세션에 저장하기 위한 클래스
     * 세션을 저장하기 위해 User 엔티티 클래스를 직접 사용하게 되면 직렬화를 해야 하는데,
     * 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
     * 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있기 때문에
     * 세션 저장용 DTO 클래스 생성*/
    @Getter
    public static class Response implements Serializable{

        private Long id;
        private String username;
        private String nickname;
        private String email;
        private Role role;
        private String modifiedDate;

        public Response(User user){
            this.id=user.getId();
            this.username=user.getUsername();
            this.nickname=user.getNickname();
            this.email=user.getEmail();
            this.role=user.getRole();
            this.modifiedDate=user.getModifiedDate();
        }
    }
}
