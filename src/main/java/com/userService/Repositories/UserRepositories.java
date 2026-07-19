package com.userService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.Entities.User;

public interface UserRepositories extends JpaRepository<User,String> {

	
	//if you want to implement any custom method or query then we can write  here 
}
