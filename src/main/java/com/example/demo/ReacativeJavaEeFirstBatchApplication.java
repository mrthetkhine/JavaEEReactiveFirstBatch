package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@SpringBootApplication
@EnableMongoRepositories
@EnableReactiveMongoRepositories
public class ReacativeJavaEeFirstBatchApplication {
	
	/*
	@Autowired
	UserService userService;
	
	*/
	public static void main(String[] args) {
		SpringApplication.run(ReacativeJavaEeFirstBatchApplication.class, args);
	}

	public void run(String...args)
	{
		/*
		
	    */
	}
}
