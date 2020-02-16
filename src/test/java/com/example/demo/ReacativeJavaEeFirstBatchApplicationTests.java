package com.example.demo;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@SpringBootTest
class ReacativeJavaEeFirstBatchApplicationTests {

	@Autowired
	UserService userService;
	
	@Test
	void addData() {
		try
		{
			User admin = new User();
		    admin.setUsername("admin");
		    admin.setPassword("admin");
		    admin.setEmail("admin@email.com");
		    Role roleAdmin = new Role();
		    roleAdmin.setRole("ADMIN");
		    
		    admin.setRoles((Arrays.asList(roleAdmin)));

		    userService.register(admin);

		    User client = new User();
		    client.setUsername("client");
		    client.setPassword("client");
		    client.setEmail("client@email.com");
		    
		    Role roleClient = new Role();
		    roleClient.setRole("CLIENT");
		    
		    client.setRoles((Arrays.asList(roleClient)));

		    userService.register(client);
		    System.out.println("Done adding user");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
