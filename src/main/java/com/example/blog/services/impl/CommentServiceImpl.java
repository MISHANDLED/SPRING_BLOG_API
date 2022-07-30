package com.example.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.config.ModelMapperConfig;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.payloads.CommentData;
import com.example.blog.payloads.PostData;
import com.example.blog.repos.CommentRepo;
import com.example.blog.repos.PostRepo;
import com.example.blog.repos.UserRepo;
import com.example.blog.services.CommentService;
import com.example.blog.services.PostService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapperConfig mapper;

	@Override
	public PostData createComment(CommentData commentData, int postID, int userID) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User", "ID", String.valueOf(userID)));
		Post post = postRepo.findById(postID).orElseThrow(()->new ResourceNotFoundException("Post", "ID", String.valueOf(postID)));
		
		Comment comment = mapper.modelMapper().map(commentData, Comment.class);
		comment.setUser(user);
		comment.setPost(post);
		commentRepo.save(comment);
		
		return postService.getPostByID(postID);
	}

	@Override
	public void deleteComment(int commentId) {
		// TODO Auto-generated method stub
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "ID", String.valueOf(commentId)));
		commentRepo.delete(comment);
	
	}

}
