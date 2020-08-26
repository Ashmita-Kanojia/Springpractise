package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserMsDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value="/mapstrut/users")
public class UserMapStructController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserMapper mapper;

	@GetMapping("/getAllUsers")
	public List<UserMsDto> getAllUsers() {
		return mapper.usersToUserDtos(service.getAllUser());
	}

	/*@GetMapping("/getUserById/{id}")
	public UserMsDto getUserById(@PathVariable("id") @Min(1)Long id) {
		User user = null;
		try {
			user = service.getUserById(id);
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return mapper.userToUserMsDto(user);
	}*/

}
