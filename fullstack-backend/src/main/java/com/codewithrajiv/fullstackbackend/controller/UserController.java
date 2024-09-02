package com.codewithrajiv.fullstackbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithrajiv.fullstackbackend.exception.UserNotFoundException;
import com.codewithrajiv.fullstackbackend.model.User;
import com.codewithrajiv.fullstackbackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	
	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userrepository.findAll();
		}

	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userrepository.save(newUser);
	}
	
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Integer id) {
		return userrepository.findById(id)
					.orElseThrow(()->new UserNotFoundException(id));
		}
	// on the below line we are adding @PutMapping 
		// annotation and passing end point to it. 
		@PutMapping("/user/{id}") 
		User updateUser(@RequestBody User newUser,@PathVariable Integer id) { 
			// inside this method we have to update the user record 
			return userrepository.findById(id)
					.map(user->{

			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setUsername(newUser.getUsername());
			user.setMobileno(newUser.getMobileno());

			return userrepository.save(user);
					}).orElseThrow(()->new UserNotFoundException(id));
		}
	
	
	// on the below line we are adding @DeleteMapping 
	// annotation and passing end point to it. 
	@DeleteMapping("/user/{id}") 
	 String deleteUserId(@PathVariable Integer id) { 
	if(!userrepository.existsById(id)){;
			throw new UserNotFoundException(id); 
	}
	userrepository.deleteById(id);
	return "User with id"+id+"has been deleted";
	
	}

	   
	
	 

	

}
