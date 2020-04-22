package com.example.demo.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorld {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

	// Simple Method
	// URI : /helloWorld
	// HTTP Method - GET
	// Two annotation to do @RequestMapping & @GetMapping
	// @RequestMapping(method = RequestMethod.GET, path="/helloWorld")
	@GetMapping("/helloworld2")
	public String helloWorld() {
		return "HelloWorld";
	}

	@GetMapping("/getUserDetails")
	public UserDetails getUserDetails() {
		return new UserDetails("Ashmita", "Kanojia", "Nashik");
	}

	@GetMapping("/I18NDemo")
	public String getMessagesInI18NFormat(@RequestHeader("Locale") String locale) {

		return messageSource.getMessage("label.hello",null, new Locale(locale));
	}
}
