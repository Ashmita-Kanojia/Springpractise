package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// getAllUsers Method
	public List<User> getAllUser() {

		return userRepo.findAll();

	}

	// Create user method
	public User createUser(User user) {

		return userRepo.save(user);
	}

	// getUserById
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

	// updateUserById
	// Check if user exist or not but for now we asume user is present
	public User updateUserById(Long id, User user) {

		user.setId(id);
		return userRepo.save(user);

	}

	// deleteUserById

	public String deleteUserById(Long id) {
		// Check if user is present
		if (userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
			return "User of" + id + "deleted";
		} else {
			return "User is not present";
		}
	}

	// find user by name

	public User getUserByName(String userName) {

		return userRepo.findByUserName(userName);
	}

}
