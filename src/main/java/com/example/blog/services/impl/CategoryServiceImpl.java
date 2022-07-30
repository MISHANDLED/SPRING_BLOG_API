package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.config.ModelMapperConfig;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.models.Category;
import com.example.blog.payloads.CategoryData;
import com.example.blog.repos.CategoryRepo;
import com.example.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapperConfig mapper;

	@Override
	public CategoryData createCategory(CategoryData category) {
		Category ct = dataToCategory(category);
		return categoryToData(categoryRepo.save(ct));
	}

	@Override
	public List<CategoryData> getAllCategories() {
		List<Category> liOfCt = categoryRepo.findAll();
		return liOfCt.stream().map(cate -> categoryToData(cate)).collect(Collectors.toList());
	}

	@Override
	public CategoryData getCategoryById(int id) {
		// TODO Auto-generated method stub
		Category ct = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", String.valueOf(id)));
		return categoryToData(ct);
	}

	@Override
	public CategoryData updateCategory(CategoryData category, int id) {
		// TODO Auto-generated method stub
		Category ct = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", String.valueOf(id)));
		ct.setCategoryName(category.getCategoryName());
		ct.setCategoryDesc(category.getCategoryDesc());
		return categoryToData(categoryRepo.save(ct));
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		Category ct = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", String.valueOf(id)));
		categoryRepo.delete(ct);
	}
	
	public Category dataToCategory(CategoryData categoryData) {
		Category category = mapper.modelMapper().map(categoryData, Category.class);
		return category;
	}
	
	public CategoryData categoryToData(Category category) {
		CategoryData data = mapper.modelMapper().map(category, CategoryData.class);
		return data;
	}

}
