package com.example.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
