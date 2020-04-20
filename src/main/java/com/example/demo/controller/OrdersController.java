package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.OrderIdNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrdersController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{id}/getAllOrders")
	public List<Order> getAllOrders(@PathVariable("id") Long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User is not present");
		}

		return user.get().getOrders();
	}

	@PostMapping("/{id}/createOrder")
	public Order createOrder(@PathVariable("id") Long id, @RequestBody Order order) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User is not present");
		}
		User userForthatOrder = user.get();
		order.setUser(userForthatOrder);
		return orderRepository.save(order);

	}
	
	@GetMapping("/{id}/getOrderByOrderId/{orderId}")
	public Optional<Order> getOrderByOrderId(@PathVariable("id") Long id,@PathVariable("orderId") Long orderId) throws UserNotFoundException, OrderIdNotFoundException {
		
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("User is not present");
		}
		if(!orderRepository.findById(orderId).isPresent()) {
			throw new OrderIdNotFoundException("Order Id is not valid");  
		}
		return orderRepository.findById(orderId);
	}
}
