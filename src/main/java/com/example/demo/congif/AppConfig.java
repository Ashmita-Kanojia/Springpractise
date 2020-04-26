package com.example.demo.congif;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Anything congifuation in spring annotate it with
@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
