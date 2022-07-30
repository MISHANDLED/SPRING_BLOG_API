package com.example.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.CommentData;
import com.example.blog.payloads.PostData;
import com.example.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/post/{postID}/user/{userID}")
	public ResponseEntity<PostData> createComment(
			@RequestBody CommentData comment, 
			@PathVariable(value="postID") int postID, 
			@PathVariable(value="userID") int userID
			){
		return new ResponseEntity<PostData>(commentService.createComment(comment, postID, userID),HttpStatus.CREATED);

	}
	
	@DeleteMapping("/delete/{commentID}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="commentID") int commentID){
		commentService.deleteComment(commentID);
		return new ResponseEntity<String>(String.format("Comment with ID %d deleted", commentID),HttpStatus.OK);
		
	}

}
