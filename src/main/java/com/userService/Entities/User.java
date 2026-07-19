package com.userService.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Getter
@Setter

	
@Entity
@Table(name="micro_users")
public class User {

	@Id
	@Column(name="ID")
	private String userId;
	@Column(name="Name")
	private String name;
	@Column(name="Email")
	private String email;
	@Column(name="About")
	private String about;
	
	@Transient     // This annotation means  this  is not to save in the database 
	private List<Rating> ratings=new ArrayList<>();
	
	public User(String userId, String name, String email, String about,List<Rating> ratings) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
		this.ratings=ratings;
		
	}
	
	public User() {
		super();
		
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
	
	 public List<Rating> getRatings() {
	        return ratings;
	    }
	public void setRatings(List<Rating> ratings) {
		this.ratings=ratings;
		
	}

	// ================= BUILDER METHOD =================
    public static UserBuilder builder() {
        return new UserBuilder();
    }


    // ================= BUILDER CLASS =================
    public static class UserBuilder {

        private String userId;
        private String name;
        private String email;
        private String about;
        private List<Rating> ratings = new ArrayList<>();


        public UserBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder about(String about) {
            this.about = about;
            return this;
        }

        public UserBuilder ratings(List<Rating> ratings) {
            this.ratings = ratings;
            return this;
        }


        public User build() {
            return new User(userId, name, email, about, ratings);
        }
    }
   
	
	
	
}
