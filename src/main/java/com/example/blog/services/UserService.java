package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.UserData;

public interface UserService {
	
	//Create
	UserData createUser(UserData user);
	
	//Read
	UserData getUserById(int id);
	List<UserData> getAllUsers();
	
	//Update
	UserData updateUser(UserData user, int id);
	
	//Delete
	void deleteUser(int id);

}
