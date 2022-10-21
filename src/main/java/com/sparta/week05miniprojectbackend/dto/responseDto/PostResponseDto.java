package com.sparta.week05miniprojectbackend.dto.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String image;
    private String exercise;
    private String time;
    private String date;

    private List<CommentResponseDto> commentResponseDtoList;
}
