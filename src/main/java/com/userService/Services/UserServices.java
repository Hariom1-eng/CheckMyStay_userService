package com.userService.Services;

import java.util.List;

import com.userService.Entities.User;

public interface UserServices {

	
//	User Operations
	
	
//	Create
	
	User saveUser(User user);
	
//	get  all user 
	
	List<User>getAllUser();
	
	
	//get single user of given userId
	
	User getUser(String userId);
	
//	TODO :delete
//	TODO :update
}
