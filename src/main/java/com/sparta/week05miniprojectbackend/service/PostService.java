package com.sparta.week05miniprojectbackend.service;

import com.sparta.week05miniprojectbackend.dto.requestDto.PostRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.CommentResponseDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.PostResponseDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.entity.Comment;
import com.sparta.week05miniprojectbackend.entity.Post;
import com.sparta.week05miniprojectbackend.entity.User;
import com.sparta.week05miniprojectbackend.repository.CommentRepository;
import com.sparta.week05miniprojectbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // userId를 이용해서 User 있는지 확인 후 User 객체 만들기
    private User getUser(String userId) {
        User user = postRepository.findByUserId(userId)
                .orElseThrow(IllegalAccessError::new);
        return user;
    }

    // postId를 이용해서 Post 있는지 확인 후 Post 객체 만들기
    private Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalAccessError::new);
        return post;
    }

    // postId를 이용해서 해당 글의 댓글 찾기
//    private List<CommentResponseDto> getCommentList(Long postId) {
//        // postId와 동일한 코멘트 전부 불러오기
//        List<Comment> commentList = commentRepository.findAllByPostId(postId);
//        // 불러온 코멘트 객체를 담을 리스트
//        List<CommentResponseDto> commentListResponseDto = new ArrayList<>();
//        // 불러온 코멘트 객체로 만들기
//        for(Comment comment : commentList) {
//            commentListResponseDto.add(
//                    CommentResponseDto.builder()
//                            .id(comment.getId())
//                            .nickName(comment.getUser().getNickName())
//                            .comment(comment.getComment())
//                            .createAt(comment.getCreateAt())
//                            .build()
//            );
//        }
//
//        return commentListResponseDto;
//    }

    // 게시글 작성
    public ResponseDto<?> createPost(PostRequestDto postRequestDto, String userId) {
        // 받아온 userId로 user 객체 생성
        User user = getUser(userId);
        // JSON으로 넘어온 데이터 + User객체 Post객체로 만들기
        Post post = new Post(postRequestDto, user);
        // DB에 저장
        postRepository.save(post);

        return ResponseDto.success(
                "게시글 작성 완료"
        );
    }

    // 게시글 수정
    @Transactional
    public ResponseDto<?> updatePost(Long postId, PostRequestDto postRequestDto, String userId) {
        // 받아온 userId로 user 객체 생성
        User user = getUser(userId);
        // 받아온 postId로 post 객체 생성
        Post post = getPost(postId);
        // 로그인한 아이디와 post 작성한 아이디가 동일한지 확인
        if(!user.getId().equals(post.getUser().getId())){
            return ResponseDto.fail("404", "작성자만 게시글 수정이 가능 합니다");
        }
        // JSON으로 넘어온 데이터 update메서드로 post 객체에 담기
        post.update(postRequestDto);
        // DB에 저장
        postRepository.save(post);

        return ResponseDto.success(
                "게시글 수정 완료"
        );
    }

    // 게시글 삭제
    @Transactional
    public ResponseDto<?> deletePost(Long postId, String userId) {
        // 받아온 userId로 user 객체 생성
        User user = getUser(userId);
        // 받아온 postId로 post 객체 생성
        Post post = getPost(postId);
        // 로그인한 아이디와 post 작성한 아이디가 동일한지 확인
        if(!user.getId().equals(post.getUser().getId())){
            return ResponseDto.fail("404", "작성자만 게시글 삭제가 가능 합니다");
        }
        // DB에서 삭제
        postRepository.deleteById(postId);
        // 해당 게시글에 쓰여있는 댓글 삭제하기
//        List<Comment> commentList = commentRepository.findAllByPostId(postid);
//        for(Comment comment : commentList) {
//            commentRepository.deleteById(comment.getId());
//        }

        return ResponseDto.success(
                "게시글 삭제 완료"
        );
    }

    // 게시글 전체조회
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(
                postRepository.findAllByOrderByModifiedAtDesc()
        );
    }

    // 게시글 상세조회
    public ResponseDto<?> getDetailPost(Long postId) {
        // 받아온 postId로 post 객체 생성
        Post post = getPost(postId);
        // 해당 글의 댓글 찾기
//        List<CommentResponseDto> commentListResponseDto = getCommentList(postId);

        return ResponseDto.success(
                PostResponseDto.builder()
                        .id(post.getId())
                        //.nickName(post.getUser().getNickName())
                        .content(post.getContent())
                        .exercise(post.getExercise())
                        .time(post.getTime())
                        .date(post.getDate())
                        .build()
        );
    }
}
