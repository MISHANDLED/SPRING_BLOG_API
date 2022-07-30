package com.example.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
