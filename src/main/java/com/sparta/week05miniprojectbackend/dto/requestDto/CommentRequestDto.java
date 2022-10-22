package com.sparta.week05miniprojectbackend.dto.requestDto;


import com.sparta.week05miniprojectbackend.entity.Comment;
import com.sparta.week05miniprojectbackend.entity.Post;
import lombok.Data;

import java.lang.reflect.Member;

@Data
public class CommentRequestDto {

    private String content;

    public Comment toEntity(Member member, Post post){

        return new Comment(member, post, this.content);
    }


}
