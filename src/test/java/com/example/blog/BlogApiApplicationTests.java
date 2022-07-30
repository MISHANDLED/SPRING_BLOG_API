package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blog.repos.UserRepo;

@SpringBootTest
class BlogApiApplicationTests {

	@Autowired
	private UserRepo obj;
	
	@Test
	void contextLoads() {
		System.out.println(obj.getClass().getName());
		System.out.println();
	}

}
