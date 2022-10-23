package com.sparta.week05miniprojectbackend.service;

import com.sparta.week05miniprojectbackend.dto.requestDto.CommentRequestDto;
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

    @Transactional
    public void create(Long postId, CommentRequestDto dto) {

        Long userId = SecurityUtil.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(//repository에서 멤버 아이디를 준비합니다..orElseThrow로 해당아이디가 아니면 던져라
                () -> new IllegalArgumentException("해당 아이디를 가진 멤버의 아이디가 없습니다.")
        );
        Post post = postRepository.findById(postId).orElseThrow(  //Null이면 던저라
                () -> new IllegalArgumentException("해당 아이디를 가진 게시글이 존재하지 않습니다.")
        );

        Comment comment = dto.toEntity(user, post);

        commentRepository.save(comment);
    }
    //댓글 삭제
    @Transactional
    public void delete(Long postId, Long commentId){

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디를 가진 댓글이 존재하지 않습니다.")
        );

        checkOwner(comment, SecurityUtil.getCurrentUserId());

        checkPostByPostId(comment, postId);

        commentRepository.deleteById(commentId);
    }



    private void checkOwner(Comment comment, Long userId){
        if(!comment.checkOwnerByMemberId(userId)){
            throw new IllegalArgumentException("회원님이 작성한 댓글이 아닙니다.");
        }
    }

    private void checkPostByPostId(Comment comment, Long postId){

        if(!comment.checkPostByPostId(postId)){
            throw new IllegalArgumentException("해당 게시글의 댓글이 아닙니다.");
        }
    }

}