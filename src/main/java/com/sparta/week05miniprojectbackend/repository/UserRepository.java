package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
