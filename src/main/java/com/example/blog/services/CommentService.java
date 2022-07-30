package com.example.blog.services;

import com.example.blog.payloads.CommentData;
import com.example.blog.payloads.PostData;

public interface CommentService {
	
	PostData createComment(CommentData commentData, int postID, int userID);
	
	void deleteComment(int commentId);

}
