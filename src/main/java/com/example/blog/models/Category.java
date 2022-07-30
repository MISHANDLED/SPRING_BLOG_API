package com.example.blog.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@Data
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id", nullable = false, unique = true)
	private int categoryID;
	
	@Column(name="category_name", nullable = false, unique = true)
	private String categoryName;
	
	@Column(name="category_desc", nullable = false)
	private String categoryDesc;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

}
