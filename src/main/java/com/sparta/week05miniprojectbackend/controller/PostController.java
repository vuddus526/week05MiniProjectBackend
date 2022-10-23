package com.sparta.week05miniprojectbackend.controller;

import com.sparta.week05miniprojectbackend.dto.requestDto.PostRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

   private final PostService postService;

    // 게시글 작성
    @PostMapping("/auth/posts")
    public ResponseDto<?> createPost(@RequestPart(required = false,value = "file") List<MultipartFile> multipartFile,
                                     @RequestPart(value = "postRequestDto") PostRequestDto postRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        return postService.createPost(multipartFile, postRequestDto, userDetailsImpl.getUser().getUserId());
    }

    // 게시글 수정
    @PutMapping("/auth/posts/{postId}")
    public ResponseDto<?> updatePost(@PathVariable("postId") Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return postService.updatePost(id, postRequestDto, userDetailsImpl.getUser().getUserId());
    }
    // 게시글 삭제
    @DeleteMapping("/auth/posts/{postId")
    public ResponseDto<?> deletePost(@PathVariable("postId") Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return postService.deletePost(id, userDetailsImpl.getUser().getUserId());
    }

    // 게시글 전체조회
    @GetMapping("/posts")
    public ResponseDto<?> getAllPost() {
        return postService.getAllPost();
    }

    // 게시글 상세조회
    @GetMapping("/posts/{postId}")
    public ResponseDto<?> getDetailPost(@PathVariable("postId") Long id) {
        return postService.getDetailPost(id);
    }
}
