package com.userService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import com.userService.Entities.User;
import com.userService.Services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class); // 25 Circuit Breaker
	
	@Autowired
	private  UserServices userServices;
//	create 
	
	
	@PostMapping("/setUser")
	public ResponseEntity<User>createUser(@RequestBody User user){
		
		User user1=userServices.saveUser(user);
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	int retryCount=1;
//	single user get 
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback") // Lecture 25 Circuit Breaker
//	@Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback") // lecture 26  Retry  Fallback
	@RateLimiter(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallback")   // Lecture 27 Rate Limiter 
	public ResponseEntity<User> getSingleUser(@PathVariable String  userId){
		
		logger.info("Get Single User Handler : UseController");
		logger.info("Retry count:{}",retryCount);// 26 Retry Fallback
		retryCount++;   // Retry Fallback
			User user=userServices.getUser(userId);
		
		return ResponseEntity.ok(user);
		
	}
	
	
	//Lecture  25  create method for the CircuitBreaker same name as given to the annotation
	// Its return  type is same as the method at which we use it 
	public  ResponseEntity<User>  ratingHotelFallback(String userId,Exception e){
		
		logger.info("FallBack is Executed because service is down :" , e.getMessage());
	    
		User user=User.builder()
				.email("hari@gmail.com")
				.name("Dummy")
				.about("This user is  created ")
				.userId("This user is created dummy beacause some service is  down")
				.build();
		
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
//	25  End ----------------------------
	
	
	
	
//	all user get 
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
	
	List<User> allUser=userServices.getAllUser();
	
	return ResponseEntity.ok(allUser);
	}
}
