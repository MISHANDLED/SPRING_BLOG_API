package com.example.blog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="comments_data")
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentID;
	
	@Column(name = "content",nullable = false)
	private String content;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private User user;
}
