package com.example.blog.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="posts_data")
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postID;
	
	@Column(name="post_title", nullable = false, length = 50)
	private String postTitle;
	
	@Column(name="post_content", nullable = false, length = 500)
	private String postContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable = false)
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name="category_info")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_info")
	private User user;
	

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@PrePersist
	private void onCreate() {
		createdOn = new Date();
	}
	
}
