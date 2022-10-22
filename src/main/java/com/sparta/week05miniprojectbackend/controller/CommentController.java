package com.sparta.week05miniprojectbackend.controller;

import com.sparta.week05miniprojectbackend.dto.requestDto.CommentRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/posts/{postId}/comments")
public class CommentController {

//    private CommentService commentService;
//
//    @PostMapping //댓글 작성
//    public ResponseDto<?> create(@PathVariable("post_id") Long postId,
//                                 @RequestBody CommentRequestDto dto
//                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
//
//        return new ResponseDto<>(true,"댓글작성완료!",null);
//    }
//
//    @DeleteMapping("/{commentsId}") //댓글삭제
//    public ResponseDto<?> delete(@PathVariable("post_id") Long postId,
//                                 @PathVariable("comment_id") Long commentsId){
//
//        return new ResponseDto<>(true,"댓글삭제완료!",null);
//    }

}
