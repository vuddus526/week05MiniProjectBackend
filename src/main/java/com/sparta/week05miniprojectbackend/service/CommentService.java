package com.sparta.week05miniprojectbackend.service;

import com.sparta.week05miniprojectbackend.dto.requestDto.CommentRequestDto;
import com.sparta.week05miniprojectbackend.dto.responseDto.ResponseDto;
import com.sparta.week05miniprojectbackend.entity.Comment;
import com.sparta.week05miniprojectbackend.entity.Post;
import com.sparta.week05miniprojectbackend.entity.User;
import com.sparta.week05miniprojectbackend.repository.CommentRepository;
import com.sparta.week05miniprojectbackend.repository.PostRepository;
import com.sparta.week05miniprojectbackend.repository.UserRepository;
import com.sparta.week05miniprojectbackend.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private User getUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(IllegalAccessError::new);
        return user;
    }
    // postId를 이용해서 Post 있는지 확인 후 Post 객체 만들기
    private Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(IllegalAccessError::new);
        return comment;
    }



    private User getUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
                );
        return user;
    }

    @Transactional
    public ResponseDto<?> create(Long postId, CommentRequestDto dto, String userId) {
//        Long userId = SecurityUtil.getCurrentUserId();
//        User user = userRepository.findById(userId).orElseThrow(//repository에서 멤버 아이디를 준비합니다..orElseThrow로 해당아이디가 아니면 던져라
//                () -> new IllegalArgumentException("해당 아이디를 가진 멤버의 아이디가 없습니다.")
//        );
        User user = getUser(userId);
        Post post = postRepository.findById(postId).orElseThrow(  //Null이면 던저라
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );
        Comment comment = dto.toEntity(user, post);
        commentRepository.save(comment);
        return ResponseDto.success(
                "아무글"
        );
    }
    //댓글 삭제
    @Transactional
    public ResponseDto<?> delete(Long postId, Long commentId, String userId){

        User user = getUser(userId);
// 받아온 postId로 post 객체 생성
        Comment comment = getComment(commentId);

        if(!user.getId().equals(comment.getUser().getId())){
            return ResponseDto.fail("404", "작성자만 게시글 삭제가 가능 합니다");

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 댓글이 존재하지 않습니다.")
        );
//        Comment comment = dto.toEntity(user,post);
        commentRepository.deleteById(commentId);
        return ResponseDto.success(
                "삭제에 성공했나?"
        );

        }
//        checkOwner(comment, SecurityUtil.getCurrentUserId());
//        checkPostByPostId(comment, postId);
//        commentRepository.deleteById(commentId);



//    private void checkOwner(Comment comment, Long userId){
//        if(!comment.checkOwnerByMemberId(userId)){
//            throw new IllegalArgumentException("회원님이 작성한 댓글이 아닙니다.");
//        }
//    }
//
//    private void checkPostByPostId(Comment comment, Long postId){
//
//        if(!comment.checkPostByPostId(postId)){
//            throw new IllegalArgumentException("해당 게시글의 댓글이 아닙니다.");
//        }
//    }

    // userId를 이용해서 User 있는지 확인 후 User 객체 만들기


    }
}