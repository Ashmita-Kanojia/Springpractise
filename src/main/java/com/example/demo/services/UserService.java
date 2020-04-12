package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		String userName = user.getUserName();
		User userByName = getUserByName(userName);
		if(userByName == null) {
			return userRepo.save(user);	
		}
		else {
			throw new UserExistsException("User already exist");
		}
		
	}

	// getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user repository");
		}
		return user;
	}

	// updateUserById
	// Check if user exist or not but for now we asume user is present
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> newUser = userRepo.findById(id);
		if (!newUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		} else {
			user.setId(id);
			return userRepo.save(user);
		}

	}

	// deleteUserById

	public String deleteUserById(Long id) {  //throws UserNotFoundException{
		// Check if user is present
		if (userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
			return "User of" + id + "deleted";
		} else {
			//return "User is not present";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found");
		}
	}

	// find user by name

	public User getUserByName(String userName) {

		return userRepo.findByUserName(userName);
	}

}
