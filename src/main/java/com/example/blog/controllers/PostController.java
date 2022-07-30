package com.example.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.GetAllResponse;
import com.example.blog.payloads.PostData;
import com.example.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//POST - Add Post
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostData> createPost(@RequestBody PostData postData, 
			@PathVariable(value="userId") int userId, 
			@PathVariable(value="categoryId") int categoryId){
		
		return new ResponseEntity<PostData>(postService.createPost(postData, userId, categoryId),HttpStatus.CREATED);
	}
	
	//GET - All Posts
	@GetMapping("/all")
	public ResponseEntity<GetAllResponse> getAllPosts(
			@RequestParam(value="pageNumber", defaultValue = "0",required = false) int pageNumber, 
			@RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = "createdOn", required = false) String sortBy
			){
		return new ResponseEntity<GetAllResponse>(postService.getAllPost(pageSize, pageNumber,sortBy),HttpStatus.OK);
	}
	
	//GET - Posts by Category
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<GetAllResponse> getAllPostsByCategory(
			@PathVariable(value="categoryId") int id,
			@RequestParam(value="pageNumber", defaultValue = "0",required = false) int pageNumber, 
			@RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = "createdOn", required = false) String sortBy){
		return new ResponseEntity<GetAllResponse>(postService.getPostsByCategory(id, pageSize, pageNumber, sortBy),HttpStatus.OK);
	}
	
	//GET - Posts by User
	@GetMapping("/users/{userId}")
	public ResponseEntity<GetAllResponse> getAllPostsByUser(
			@PathVariable(value="userId") int id,
			@RequestParam(value="pageNumber", defaultValue = "0",required = false) int pageNumber, 
			@RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = "createdOn", required = false) String sortBy){
		return new ResponseEntity<GetAllResponse>(postService.getPostsByUser(id, pageSize, pageNumber, sortBy),HttpStatus.OK);
	}
	
	//GET - Post by ID
	@GetMapping("{id}")
	public ResponseEntity<PostData> getPostById(@PathVariable(value="id") int id){
		return new ResponseEntity<PostData>(postService.getPostByID(id),HttpStatus.OK);
	}
	
	//GET - Post by Keyword
	@GetMapping("/search")
	public ResponseEntity<List<PostData>> getSearchedPost(@RequestParam(value="searchBy", required = true) String key){
		return new ResponseEntity<List<PostData>>(postService.searchPost(key),HttpStatus.OK);
	}
	
	//DELETE - Post by ID
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(value="id") int id){
		postService.deletePost(id);
		return new ResponseEntity<String>(String.format("Post with Id - %d successfully deleted", id),HttpStatus.OK);
	}
	
	//UPDATE - Post by ID
	@PutMapping("{id}")
	public ResponseEntity<PostData> updatePostById(@PathVariable(value="id") int id, @RequestBody PostData postData){
		return new ResponseEntity<PostData>(postService.updatePost(postData, id), HttpStatus.ACCEPTED);
	}

}
