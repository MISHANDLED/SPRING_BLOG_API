package com.example.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PostData {
	
	private int postID;
	private String postTitle;
	private String postContent;
	private Date created_on;
	private CategoryData category;
	private UserData user;
	private List<CommentData> comments = new ArrayList<CommentData>();

}
