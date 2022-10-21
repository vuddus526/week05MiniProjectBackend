package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
