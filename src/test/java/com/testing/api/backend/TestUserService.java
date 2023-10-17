package com.testing.api.backend;

import com.testing.api.backend.entity.Address;
import com.testing.api.backend.entity.Social;
import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.service.AddressService;
import com.testing.api.backend.service.SocialService;
import com.testing.api.backend.service.UserService;
import com.testing.api.backend.util.SecurityUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;

	@Order(1)
	@Test
	void testCreates() throws BaseException {
		String token = SecurityUtil.generateToken();
		User user = userService.create(TestData.email,
				TestData.password,
				TestData.name, token,
				new Date());
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
	void testcreatesocial() throws BaseException {
		Optional<User> opt = userService.findByEmal(TestData.email);
		//if true is have data
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.create(user,
				SocialTestCreateData.facebook,
				SocialTestCreateData.line,
				SocialTestCreateData.instagram,
				SocialTestCreateData.tiktok) ;
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());


	}

	@Order(4)
	@Test
	void testcreateaddress(){
		Optional<User> opt = userService.findByEmal(TestData.email);
		//if true is have data
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		List<Address> addresses = user.getAddresses();
		Assertions.assertTrue(addresses.isEmpty());

		createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
		createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);



	}

	private void createAddress(User user,String line1, String line2, String zipcode){

		Address address = addressService.create(user, line1, line2,	zipcode);

		Assertions.assertNotNull(address);
		Assertions.assertEquals(line1,address.getLine1());

	}


	@Order(5)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmal(TestData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook,social.getFacebook());


		List<Address> addresses = user.getAddresses();
		Assertions.assertFalse(addresses.isEmpty());
		Assertions.assertEquals(2, addresses.size());



		userService.deleteById(user.getId());

		Optional<User> optdel = userService.findByEmal(TestData.email);
		Assertions.assertTrue(optdel.isEmpty());
	}

	//Demo Data
	interface TestData{
		String email = "nattkarn.p@hotmail.com";
		String password = "syspass";
		String name = "Nattkarn Prajansri";

		String token = "dawkld;awjk@WADwadwdkk";
	}

	interface TestUpdateData{
		String name = "ARM";
	}

	interface  SocialTestCreateData{
		String facebook = "Nattkarn";
		String line = "";
		String instagram = "";
		String tiktok = "";
	}

	interface  AddressTestCreateData{
		String  line1 = "94 m.5 nawanghin";
		String line2 = "phanatnikom chonburi";
		String zipcode = "20240";
	}

	interface  AddressTestCreateData2{
		String  line1 = "23 non";
		String line2 = "non";
		String zipcode = "11000";
	}
}
