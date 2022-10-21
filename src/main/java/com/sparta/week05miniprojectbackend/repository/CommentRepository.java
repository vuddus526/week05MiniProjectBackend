package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
