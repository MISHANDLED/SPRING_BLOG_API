package com.example.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.UserData;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//GET Request - All Users
	@GetMapping("/all")
	public ResponseEntity<List<UserData>> findAllUsers(){
		return new ResponseEntity<List<UserData>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	//GET Request - Single User
	@GetMapping("/{id}")
	public ResponseEntity<UserData> findUserById(@PathVariable(value="id") int id){
		return new ResponseEntity<UserData>(userService.getUserById(id),HttpStatus.OK);
	}
	//POST Request
	@PostMapping("/")
	public ResponseEntity<UserData> createUser(@Valid @RequestBody UserData userDetails){
		UserData createdUser = userService.createUser(userDetails);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	//DELETE Request
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value="id") int id){
		userService.deleteUser(id);
		return new ResponseEntity<String>(String.format("User with Id - %d successfully deleted", id),HttpStatus.OK);
	}
	
	//PUT Request
	@PutMapping("/{id}")
	public ResponseEntity<UserData> updateUser(@Valid @RequestBody UserData userDetails, @PathVariable(value="id") int id){
		UserData updatedUser = userService.updateUser(userDetails, id);
		return new ResponseEntity<UserData>(updatedUser, HttpStatus.ACCEPTED);
	}

}
