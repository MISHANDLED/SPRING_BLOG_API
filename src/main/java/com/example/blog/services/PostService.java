package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.GetAllResponse;
import com.example.blog.payloads.PostData;

public interface PostService {
	
	//Create
	PostData createPost(PostData postData, int userID, int categoryID);
	
	//Read
	GetAllResponse getAllPost(int pageSize, int pageNumb, String sortBy);
	PostData getPostByID(int id);
	GetAllResponse getPostsByCategory(int categoryID, int pageSize, int pageNumb, String sortBy);
	GetAllResponse getPostsByUser(int userId, int pageSize, int pageNumb, String sortBy);
	List<PostData> searchPost(String keyword);
	
	//Update
	PostData updatePost(PostData postData, int postId);
	
	//Delete
	void deletePost(int id);

}
