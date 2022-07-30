package com.example.blog.payloads;

import java.util.List;

import lombok.Data;

@Data
public class GetAllResponse {
	
	private List<PostData> postList;
	private long pageNumber;
	private long pageSize;
	private long totalNumber;
	private long totalSize;
	private boolean lastPage;

}
