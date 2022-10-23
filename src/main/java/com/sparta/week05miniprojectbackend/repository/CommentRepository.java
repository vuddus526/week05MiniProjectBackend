package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);

//    @Query("select c from Comment c where c.post.id = :postId")
//    List<Comment> findAllById(@Param("postId") Long postId);
//    @Modifying
//    @Query("delete from Comment c where c.post.id = :postId")
//    void deleteAllByPostId(@Param("postId")Long postId);

}
