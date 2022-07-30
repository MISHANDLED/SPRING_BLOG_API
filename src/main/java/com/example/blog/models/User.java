package com.example.blog.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", nullable = false, unique = true)
	private int userID;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="about_user", nullable = true)
	private String about;
	
	@Autowired
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@Autowired
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;
	
}
