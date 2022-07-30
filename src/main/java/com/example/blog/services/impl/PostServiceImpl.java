package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.blog.config.ModelMapperConfig;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.models.Category;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.payloads.GetAllResponse;
import com.example.blog.payloads.PostData;
import com.example.blog.repos.CategoryRepo;
import com.example.blog.repos.PostRepo;
import com.example.blog.repos.UserRepo;
import com.example.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapperConfig mapper;
	
	@Override
	public PostData createPost(PostData postData, int userID, int categoryID) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User", "ID", String.valueOf(userID)));
		Category ct = categoryRepo.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("Category","ID",String.valueOf(categoryID)));
		Post post = mapper.modelMapper().map(postData, Post.class);
		
		post.setUser(user);
		post.setCategory(ct);
		
		Post setPost = postRepo.save(post);
		
		return mapper.modelMapper().map(setPost, PostData.class);
	}

	@Override
	public GetAllResponse getAllPost(int pageSize, int pageNumb, String sortBy) {
		// TODO Auto-generated method stub
		
		Pageable p = PageRequest.of(pageNumb, pageSize, Sort.by(Direction.DESC,sortBy));
		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> postList = pagePost.getContent();
		
		GetAllResponse obj = new GetAllResponse();
		obj.setPostList(postList.stream().map(post -> mapper.modelMapper().map(post, PostData.class)).collect(Collectors.toList()));
		obj.setPageNumber(pagePost.getNumber());
		obj.setPageSize(pagePost.getSize());
		obj.setTotalNumber(pagePost.getTotalPages());
		obj.setTotalSize(pagePost.getTotalElements());
		obj.setLastPage(pagePost.isLast());
		
		return obj;
	}

	@Override
	public PostData getPostByID(int id) {
		// TODO Auto-generated method stub
		
		Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", String.valueOf(id)));
		
		return mapper.modelMapper().map(post, PostData.class);
	}

	@Override
	public GetAllResponse getPostsByCategory(int categoryID, int pageSize, int pageNumb, String sortBy) {
		// TODO Auto-generated method stub
		
		Category cat = categoryRepo.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("category", "ID", String.valueOf(categoryID)));
		
		Pageable p = PageRequest.of(pageNumb, pageSize, Sort.by(Direction.DESC,sortBy));
		
		Page<Post> pagePost = postRepo.findByCategory(cat, p);
		List<Post> postList = pagePost.getContent();
		
		GetAllResponse obj = new GetAllResponse();
		obj.setPostList(postList.stream().map(post -> mapper.modelMapper().map(post, PostData.class)).collect(Collectors.toList()));
		obj.setPageNumber(pagePost.getNumber());
		obj.setPageSize(pagePost.getSize());
		obj.setTotalNumber(pagePost.getTotalPages());
		obj.setTotalSize(pagePost.getTotalElements());
		obj.setLastPage(pagePost.isLast());
		
		return obj;
	}

	@Override
	public GetAllResponse getPostsByUser(int userId, int pageSize, int pageNumb, String sortBy) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "ID", String.valueOf(userId)));
		
		Pageable p = PageRequest.of(pageNumb, pageSize, Sort.by(Direction.DESC,sortBy));
		Page<Post> pagePost = postRepo.findByUser(user, p);
		List<Post> postList = pagePost.getContent();
		
		GetAllResponse obj = new GetAllResponse();
		obj.setPostList(postList.stream().map(post -> mapper.modelMapper().map(post, PostData.class)).collect(Collectors.toList()));
		obj.setPageNumber(pagePost.getNumber());
		obj.setPageSize(pagePost.getSize());
		obj.setTotalNumber(pagePost.getTotalPages());
		obj.setTotalSize(pagePost.getTotalElements());
		obj.setLastPage(pagePost.isLast());
		
		return obj;
	}

	@Override
	public List<PostData> searchPost(String keyword) {
		// TODO Auto-generated method stub
		
		List<Post> postList = postRepo.searchTitleContaining(keyword);
		
		return postList.stream().map(post -> mapper.modelMapper().map(post, PostData.class)).collect(Collectors.toList());
	}

	@Override
	public PostData updatePost(PostData postData, int postId) {
		// TODO Auto-generated method stub
		
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "ID", String.valueOf(postId)));
		
		post.setPostTitle(postData.getPostTitle());
		post.setPostContent(postData.getPostContent());
		
		return mapper.modelMapper().map(postRepo.save(post),PostData.class);
	}

	@Override
	public void deletePost(int id) {
		// TODO Auto-generated method stub
		
		Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "ID", String.valueOf(id)));
		postRepo.delete(post);
		
	}

}
