package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.config.ModelMapperConfig;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.models.User;
import com.example.blog.payloads.UserData;
import com.example.blog.repos.UserRepo;
import com.example.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
		
	@Autowired
	private ModelMapperConfig mapper;
	
	@Override
	public UserData createUser(UserData userData) {
		// TODO Auto-generated method stub
		User user = DtoU(userData);
		
		return UtoD(userRepo.save(user));
	}

	@Override
	public UserData updateUser(UserData user, int id) {
		// TODO Auto-generated method stub
		User userById = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
		userById.setAbout(user.getAbout());
		userById.setEmail(user.getEmail());
		userById.setName(user.getName());
		userById.setPassword(user.getPassword());
		
		return UtoD(userRepo.save(userById));
	}

	@Override
	public UserData getUserById(int id) {
		// TODO Auto-generated method stub
		User userById = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
		return UtoD(userById);
	}

	@Override
	public List<UserData> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> userList = userRepo.findAll();
		return userList.stream().map(user -> UtoD(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		User userById = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", String.valueOf(id)));
		userRepo.delete(userById);
	}
	
	private User DtoU(UserData ud) {
		
		/*
		 * User user = new User();
		 * user.setName(ud.getName());
		 * user.setEmail(ud.getEmail());
		 * user.setPassword(ud.getPassword());
		 * user.setUserID(ud.getUserID());
		 * user.setAbout(ud.getAbout());
		 */
		User user = mapper.modelMapper().map(ud, User.class);
		return user;
	}
	
	private UserData UtoD(User user) {
		
		/*
		 * UserData ud = new UserData();
		 * ud.setName(user.getName());
		 * ud.setEmail(user.getEmail());
		 * ud.setPassword(user.getPassword());
		 * ud.setUserID(user.getUserID());
		 * ud.setAbout(user.getAbout());
		 */
		UserData ud = mapper.modelMapper().map(user, UserData.class);
		return ud;
	}

}
