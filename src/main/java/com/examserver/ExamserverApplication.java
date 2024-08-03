package com.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examserver.entity.Role;
import com.examserver.entity.User;
import com.examserver.entity.UserRole;
import com.examserver.helper.UserFoundException;
import com.examserver.services.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			User user = new User();
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setUsername("john.doe");
			user.setPassword(this.bCryptPasswordEncoder.encode("12345"));
			user.setEmail("john@gmail.com");
			user.setProfile("https://res.cloudinary.com/dvjzajnpz/image/upload/v1720634146/d57f701d-5a89-43ac-8486-00d2c7151266.jpg");
			user.setPhone("1234567890");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoles = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			userRoles.add(userRole);
			User user1 = userService.createUser(user, userRoles);
			System.out.println(user1.getUsername());
		} catch (UserFoundException e) {
			e.printStackTrace();
		}

	}

}
