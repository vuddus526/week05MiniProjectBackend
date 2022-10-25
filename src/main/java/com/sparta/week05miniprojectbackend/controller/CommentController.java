package com.sparta.week05miniprojectbackend.controller;

import com.sparta.week05miniprojectbackend.dto.requestDto.CommentRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.security.user.UserDetailsImpl;
import com.sparta.week05miniprojectbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping //댓글 작성
    public ResponseDto<?> create(@PathVariable("postId") Long postId,
                                 @RequestBody CommentRequestDto dto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return commentService.createComment(postId, dto, userDetailsImpl.getUser().getUserId());
    }

    @DeleteMapping("/{commentsId}") //댓글삭제
    public ResponseDto<?> delete(
                                 @PathVariable("commentsId") Long commentId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return commentService.deleteComment(commentId,userDetailsImpl.getUser().getUserId());
    }
}
