package com.example.demo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.entities.Views;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value="/jsonView/User")
@Validated
public class UserJsonViewController {
	
	@Autowired
	private UserService service;

	@GetMapping("/getUserByIdExternal/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserByIdExternalView(@PathVariable("id") @Min(1) Long id) {
		Optional<User> user = null;
		try {
			user = service.getUserById(id);
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}
	
	@GetMapping("/getUserByIdInternal/{id}")
	@JsonView(Views.Internal.class)
	public Optional<User> getUserByIdInternalView(@PathVariable("id") @Min(1) Long id) {
		Optional<User> user = null;
		try {
			user = service.getUserById(id);
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}
}
