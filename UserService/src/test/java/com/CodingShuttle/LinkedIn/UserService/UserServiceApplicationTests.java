package com.CodingShuttle.LinkedIn.UserService;

import com.CodingShuttle.LinkedIn.UserService.Entity.User;
import com.CodingShuttle.LinkedIn.UserService.Services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void generatesAndParsesAccessToken() {
		User user = new User();
		user.setId(42L);
		user.setEmail("ana@example.com");

		String token = jwtService.generateAccessToken(user);

		assertEquals(42L, jwtService.getUserIdFromToken(token));
	}

}
