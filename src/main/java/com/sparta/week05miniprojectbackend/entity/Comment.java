package com.sparta.week05miniprojectbackend.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
//@AllArgsConstructor

public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commentId", nullable = false)
    private Long id;        // 댓글 고유 id

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    public Comment (User user, Post post, String content){
        this.user = user;
        this.post = post;
        this.content = content;
    }

//    public void update(String content){
//
//            this.content = content;
//        }

        public boolean checkOwnerByMemberId(Long userId){

            return this.user.getId().equals(userId);
    }
    public boolean checkPostByPostId(Long postId) {
        return post.getId().equals(postId);
    }
}