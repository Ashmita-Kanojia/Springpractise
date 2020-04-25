package com.example.demo.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping(value = "/jacksonfilter/user")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService service;

	//Fields by HashSet
	@GetMapping("/getUserById/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		Optional<User> userOptional = null;
		try {
			userOptional = service.getUserById(id);
			User user = userOptional.get();

			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("userName");
			fields.add("ssn");
			fields.add("orders");

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	//Fields by @RequestParam
	@GetMapping("/getUserById2/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,@RequestParam Set<String> fields) {
		Optional<User> userOptional = null;
		try {
			userOptional = service.getUserById(id);
			User user = userOptional.get();

			/*
			 * Set<String> fields = new HashSet<String>(); fields.add("id");
			 * fields.add("userName"); fields.add("ssn"); fields.add("orders");
			 */

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
