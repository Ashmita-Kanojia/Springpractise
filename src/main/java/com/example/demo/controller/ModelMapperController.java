package com.example.demo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserMmDto;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/modelMapper/user")
@Validated
public class ModelMapperController {
	
	@Autowired
	private UserService service;
	//Model mapper will convert existing User object to UserDTO
	//Step 1 map our model mapper
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/getUserById/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1)Long id) throws UserNotFoundException {
		Optional<User> userOptional = service.getUserById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User is not present");
		}
		
		User user = userOptional.get();
		UserMmDto userDto = mapper.map(user, UserMmDto.class);
		return userDto;
		
	}

}
