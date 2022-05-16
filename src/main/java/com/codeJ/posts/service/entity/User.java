package com.codeJ.posts.service.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@ToString
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 30,unique = true)
    private String username;

    @Column(nullable = false,length = 50,unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String password;

    @Column(nullable = false,length = 50,unique = true)
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /** 회원정보 수정 */
    public void modify(String nickname,String password){
        this.nickname=nickname;
        this.password=password;
    }

    /**
     * 소셜 로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서 기존 데이터를 보존하도록 예외처리
     */
    public User updateModifiedDate(){
        this.onPreUpdate();
        return this;
    }
    public String getRoleValue(){
        return this.role.getValue();
    }
}
