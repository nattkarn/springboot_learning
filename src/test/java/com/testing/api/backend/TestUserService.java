package com.testing.api.backend;

import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;
	@Order(1)
	@Test
	void testCreates() throws BaseException {
		User user = userService.create(TestData.email,
				TestData.password,
				TestData.name);
		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		//check equals
		Assertions.assertEquals(TestData.name, user.getName());
		Assertions.assertEquals(TestData.email, user.getEmail());

		boolean isMatch = userService.matchPassword(TestData.password, user.getPassword());
		Assertions.assertTrue(isMatch);
	}
	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmal(TestData.email);
		//if true is have data
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);
		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmal(TestData.email);
		//if true is have data
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> optdel = userService.findByEmal(TestData.email);
		Assertions.assertTrue(optdel.isEmpty());
	}

	//Demo Data
	interface TestData{
		String email = "nattkarn.p@hotmail.com";
		String password = "syspass";
		String name = "Nattkarn Prajansri";
	}

	interface TestUpdateData{
		String name = "ARM";
	}

}
