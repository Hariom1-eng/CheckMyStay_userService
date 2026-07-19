package com.userService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// This file is mainly created for the  declaring Beans only

@Configuration
//@EnableWebSecurity  // 32 MicroService security

//@EnableGlobalMethodSecurity(prePostEnabled=true) 32 
public class MyConfig {

//	 lecture  12 Declare Bean for the   restTemplate
	@Bean
	@LoadBalanced
	
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	
	//  32 ✅ Security configuration
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//
//        security
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated()
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2
//                .jwt()
//            );
//
//        return security.build();
//    }
      
	
//	    32  security  MicreService 
}
