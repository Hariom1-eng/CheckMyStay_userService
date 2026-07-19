package com.userService.Services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userService.Entities.Hotel;
import com.userService.Entities.Rating;
import com.userService.Entities.User;
import com.userService.Exception.ResourceNotFoundException;
import com.userService.External.Services.HotelServices;
import com.userService.External.Services.RatingService;
import com.userService.Repositories.UserRepositories;
import com.userService.Services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private  Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private HotelServices hotelService;
	
	
	
	
	@Override
	 public User saveUser(User user) {
		
		String randomUserId=UUID.randomUUID().toString();
		 user.setUserId(randomUserId);
		 return userRepositories.save(user);
	 }
	 
	@Override
	 public List<User>getAllUser(){
		 List<User> users=userRepositories.findAll();
		 
//		 lecture 12  get  all users rating in the user service 
		 for (User user : users) {

//			  12 this is  the restTemplate 
//			 
	        Rating[] ratings = restTemplate.getForObject(
		                "http://MICROSERVICESPROJECTRATING3/ratings/users/" + user.getUserId(),		                Rating[].class
	        );
		        
			
		        user.setRatings(Arrays.asList(ratings));
		    }

		    return users;
	 }
	 
//	 lecture 12 & 13 we communicate the two service Rating and Hotel Service  get their data at  the User service based on  user  id  
	@Override
	 public User getUser(String userId) {
		
		//here it  get  user from  the  database with the help of user repository
		 User user= userRepositories.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given Id is not found on the server : "+userId));
	   
		 // lecture 12 fetch rating of  the above i=user from RATING SERVICE
		 
		Rating[] ratingOfUser= restTemplate.getForObject("http://MICROSERVICESPROJECTRATING3/ratings/users/"+user.getUserId(),Rating[].class);//12
		logger.info("{}",ratingOfUser);//12
		
		 
		 List<Rating>  ratings = Arrays.stream(ratingOfUser).toList();//13 HotelService
		 List<Rating> ratingList = ratings.stream().map(rating->{ //13 HotelService
			
			//api call to hotel service to  get the hotel 
			
			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://MICROSERVICESPROJECTHOTEL/hotels/"+rating.getHotelId(),Hotel.class);// 13 Hotel Service 
			 Hotel hotel=forEntity.getBody();// 13 Hotel Service 
//		Hotel hotel=hotelService.getHotel(rating.getHotelId()); // 15 Feign client
			 logger.info("response status code: {}",forEntity.getStatusCode()); // 13 Hotel Service 
			
			 //set the hotel to  reating 
			
			 rating.setHotel(hotel); // 13 Hotel Service 
			
			 //return the rating 
			
			return rating; // 13 Hotel Service 
			
		}).collect(Collectors.toList()); //  13 Hotel service 
		
		
		
		 
		user.setRatings(ratingList);//12
		
		 return user;  // 12 
	}
}

