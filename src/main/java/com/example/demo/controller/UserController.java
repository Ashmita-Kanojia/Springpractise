package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserMapper;
import com.example.demo.dto.UserMsDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

//Controller
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired 
	private UserRepository repo;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		return service.getAllUser();
	}
	
	@PostMapping("/createNewUser")
	public User createUser(@RequestBody User user) {
		
		return service.createUser(user);
	}
	
	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		Optional<User> user = service.getUserById(id);
		return user;
	}
	
	@PutMapping("/updateUserById/{id}")
	public User updateUserById(@PathVariable("id")Long id, @RequestBody User user) {
		
		return service.updateUserById(id, user);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public String deleteUserById(@PathVariable("id")Long id) {
		
		return service.deleteUserById(id);
		
	}
	
	@GetMapping("/getUserByName/{userName}")
	public User getUserByName(@PathVariable("userName")String userName) {
		
		return service.getUserByName(userName);
	}
	
	//Map Struct operations
	@GetMapping("/getAllUsersMsDto")
	public List<UserMsDto> getAllUsersMsDto(){
		return mapper.usersToUserDtos(service.getAllUser());
	}
	
	@GetMapping("/getUserByMsDtoId/{id}")
	public UserMsDto getUserMsDtoById(@PathVariable Long id) {
		Optional<User> optionaluser = repo.findById(id);
		System.out.println("Optional User : " + optionaluser);
		return mapper.userToUserMsDto(optionaluser.get());
	}
}

