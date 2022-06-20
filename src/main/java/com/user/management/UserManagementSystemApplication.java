package com.user.management;

import com.user.management.entities.Role;
import com.user.management.entities.User;
import com.user.management.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UserManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			Stream.of("STUDENT", "USER", "ADMIN").forEach(r->{
				Role role = new Role();
				role.setRoleName(r);
				userService.addRole(role);
			});

			User user1 = new User();
			user1.setUsername("user1");
			user1.setPassword("1234");
			userService.addUser(user1);

			User user2 = new User();
			user2.setUsername("admin");
			user2.setPassword("12345");
			userService.addUser(user2);

			userService.addRoleToUser("user1", "STUDENT");
			userService.addRoleToUser("user1", "USER");
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");
		};
	}
}
