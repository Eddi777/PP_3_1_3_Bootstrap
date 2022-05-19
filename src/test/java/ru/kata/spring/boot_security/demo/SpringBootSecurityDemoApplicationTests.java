package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void grantAdminPermissions() {
		User user = userService.findById(1L);
		Set<Role> roles = new HashSet<>();
		roles.add(new Role("ADMIN");
		roles.add(new Role("USER");
		user.setRoles(roles);
		userService.saveUser(user);
	}

}
