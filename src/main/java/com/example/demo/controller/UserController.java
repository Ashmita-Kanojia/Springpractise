package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNameNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

//Controller
@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return service.getAllUser();
	}

	@PostMapping("/createNewUser")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {

		try {
			service.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/getUserById/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			//e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1)Long id) {
		Optional<User> user = null;
		try {
			user = service.getUserById(id);
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}

	@PutMapping("/updateUserById/{id}")
	public User updateUserById(@PathVariable("id")Long id, @RequestBody User user) {
		try {
			return service.updateUserById(id, user);
		} catch (UserNotFoundException e) {
		
			//e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage()); 
		}
		//return user;
	}

	@DeleteMapping("/deleteUserById/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {

		return service.deleteUserById(id);

	}

	@GetMapping("/getUserByName/{userName}")
	public User getUserByName(@PathVariable("userName") String userName) throws UserNameNotFoundException {

		User user = service.getUserByName(userName);
		if(user==null) {
			throw new UserNameNotFoundException("User not found");
		}
		return user;
	}

}
