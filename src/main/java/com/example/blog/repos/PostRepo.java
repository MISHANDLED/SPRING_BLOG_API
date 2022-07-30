package com.example.blog.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blog.models.Category;
import com.example.blog.models.Post;
import com.example.blog.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user, Pageable p);
	Page<Post> findByCategory(Category category, Pageable p);
	
	@Query("select p from Post p where lower(p.postTitle) like concat('%', :keyword,'%')")
	List<Post> searchTitleContaining(@Param("keyword") String keyword);

}
