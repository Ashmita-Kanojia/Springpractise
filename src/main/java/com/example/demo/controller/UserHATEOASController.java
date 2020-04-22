package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHATEOASController {

	@Autowired
	private UserService service;

	// Self link : each user must have their link
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allUsers = service.getAllUser();
		for (User users : allUsers) {
			Long userId = users.getId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			users.add(selfLink);
			// RelationLink
			CollectionModel<Order> orders = ControllerLinkBuilder.methodOn(OrderHATEOASController.class)
					.getAllOrders(userId);
			Link relationLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			users.add(relationLink);
		}
		// Self link to getAllUsers
		Link link = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		return new CollectionModel<User>(allUsers, link);
	}

	// SelfLinking
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		Optional<User> user = null;
		try {
			user = service.getUserById(id);
			User userFromOptionalUser = user.get();
			Long userId = userFromOptionalUser.getId();
			@SuppressWarnings("deprecation")
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			userFromOptionalUser.add(selfLink);
			return new EntityModel<User>(userFromOptionalUser);
		} catch (UserNotFoundException e) {
			// e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		// return user;
	}
}
