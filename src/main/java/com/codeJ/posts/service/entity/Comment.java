package com.codeJ.posts.service.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String comment;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name ="modified_Date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name="posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** 댓글 수정 */
    public void update(String comment){
        this.comment = comment;
    }
}
