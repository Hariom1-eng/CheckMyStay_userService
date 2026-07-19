package com.userService.External.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.userService.Entities.Hotel;
@FeignClient(name="MICROSERVICESPROJECTHOTEL")
public interface HotelServices {

	@GetMapping("/hotels/{hotelId}")
	 Hotel getHotel(@PathVariable("hotelId") String hotelId );
}
