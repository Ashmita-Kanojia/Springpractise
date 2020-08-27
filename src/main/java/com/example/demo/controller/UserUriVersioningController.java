package com.example.demo.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDtoV1;
import com.example.demo.dto.UserDtoV2;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
public class UserUriVersioningController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper mapper;
	
	//URI based versioning - V1
	@GetMapping({"/v1.0/{id}","/v1.1/{id}"})
	public UserDtoV1 getUserById(@PathVariable Long id) {
		System.out.println("Id user : "+id);
		Optional<User> optionalUser = service.getUserById(id);
		System.out.println("Optional user : "+optionalUser);
		System.out.println("mapper : "+mapper);
		return mapper.map(optionalUser.get(), UserDtoV1.class);
	}
	
	//URI based versioning - V1
		@GetMapping({"/v2.0/{id}","/v2.1/{id}"})
		public UserDtoV2 getUserByIdV2(@PathVariable Long id) {
			System.out.println("Id user : "+id);
			Optional<User> optionalUser = service.getUserById(id);
			System.out.println("Optional user : "+optionalUser);
			System.out.println("mapper : "+mapper);
			return mapper.map(optionalUser.get(), UserDtoV2.class);
		}
	

}
