package com.example.demo.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDtoV1;
import com.example.demo.dto.UserDtoV2;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;

@RestController
public class UserRequestParamVersioningController {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper mapper;

	// Request Param based versioning - V1
	@GetMapping(value = "/{id}", params = "version=1")
	public UserDtoV1 getUserById(@PathVariable Long id) {

		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV1.class);
	}

	// Request Param based versioning - V2
	@GetMapping(value = "/{id}", params = "version=2")
	public UserDtoV2 getUserByIdV2(@PathVariable Long id) {
		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV2.class);
	}

	// Custom Header based versioning - V1
	@GetMapping(value = "/{id}", headers = "API-VERSION=1")
	public UserDtoV1 getUserById_byHeaders(@PathVariable Long id) {

		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV1.class);
	}

	// Custom Header based versioning - V2
	@GetMapping(value = "/{id}", headers = "API-VERSION=2")
	public UserDtoV2 getUserById_byheaders_V2(@PathVariable Long id) {
		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV2.class);
	}

	// Accept Header based versioning - V1
	@GetMapping(value = "/{id}", produces = "application/vnd.stacksimplify.app-v1+json")
	public UserDtoV1 getUserById_byAcceptHeaders(@PathVariable Long id) {

		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV1.class);
	}

	// Accept Header based versioning - V2
	@GetMapping(value = "/{id}", produces = "application/vnd.stacksimplify.app-v2+json")
	public UserDtoV2 getUserById_byAcceptheaders_V2(@PathVariable Long id) {
		Optional<User> optionalUser = service.getUserById(id);
		return mapper.map(optionalUser.get(), UserDtoV2.class);
	}

}
