package com.example.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorld {

	// Simple Method
	// URI : /helloWorld
	// HTTP Method - GET
	// Two annotation to do @RequestMapping & @GetMapping
	//@RequestMapping(method = RequestMethod.GET, path="/helloWorld")
	@GetMapping("/helloworld2")
	public String helloWorld() {
		return "HelloWorld";
	}
	
	@GetMapping("/getUserDetails")
	public UserDetails getUserDetails() {
		return new UserDetails("Ashmita", "Kanojia", "Nashik");
	}
}
