package com.sparta.week05miniprojectbackend.entity;

import com.sparta.week05miniprojectbackend.dto.requestDto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "exercise", nullable = false)
    private String exercise;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "date", nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Post(PostRequestDto postRequestDto, User user) {
        this.content = postRequestDto.getContent();
        this.image = postRequestDto.getImage();
        this.exercise = postRequestDto.getExercise();
        this.time = postRequestDto.getTime();
        this.date = postRequestDto.getDate();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
        this.image = postRequestDto.getImage();
        this.exercise = postRequestDto.getExercise();
        this.time = postRequestDto.getTime();
        this.date = postRequestDto.getDate();
    }
}
