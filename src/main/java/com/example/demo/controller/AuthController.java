package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin		
public class AuthController {
	  @Autowired
	  private UserService userService;

	

	  @PostMapping("/login")
	  public String login(@Valid @RequestBody User user) {
	    return userService.login(user.getUsername(), user.getPassword());
	  }

	  @PostMapping("/register")
	  public String register(@Valid @RequestBody User user) {
	    return userService.register(user);
	  }
}
