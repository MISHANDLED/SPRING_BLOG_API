package com.example.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	private int userID;
	
	@NotEmpty(message = "Name cannot be Empty")
	@Size(min=3, max=20, message = "Name Should be between 3 to 20 characters")
	private String name;
	
	@Email(message = "Invalid Email Address")
	private String email;
	
	@NotEmpty(message = "Password cannot be Empty")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password does not meet the criteria")
	private String password;
	
	@NotEmpty(message = "About Section cannot be empty")
	@Size(max=200, message = "Max Characters Limit is 200")
	private String about;

}
