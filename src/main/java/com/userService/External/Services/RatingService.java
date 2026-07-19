package com.userService.External.Services;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.DeleteMapping;
import com.userService.Entities.Rating;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name="MICROSERVICESPROJECTRATING3")
public interface RatingService {

	
	//declares methods 
	
	//get
	   @GetMapping("/ratings/users/{userId}")
	    List<Rating> getRatings(@PathVariable("userId") String userId);

	
	//post 
	@PostMapping("/ratings/create")
	public Rating createRating(@RequestBody Rating values);
	
	//put
	
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, @RequestBody Rating rating);
	
	
	//Delete  rating
	
	@DeleteMapping("/ratings/{ratingId}")
	
	public void delleteRating(@PathVariable String ratingId);
}
