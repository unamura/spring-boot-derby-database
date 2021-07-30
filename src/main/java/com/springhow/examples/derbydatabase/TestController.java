package com.springhow.examples.derbydatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

	@Autowired
	UserRepository userRepository;

	public TestController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("test/update")
	public List<UserEntity> saveTest(int id, String lastName) {
		UserEntity testUser = new UserEntity();
		List<UserEntity> uList = userRepository.findAll();
		for (UserEntity u : uList) {
			if (u.getId() == id) {
				u.setLastName(lastName);
				testUser = u;
			}
		}

		userRepository.save(testUser);
		return userRepository.findAll();
	}

	@GetMapping("/test/query")
	public String GetUserQueryJpa(String lastName) {
		String result = "";

		List<UserEntity> uList = userRepository.findByLastName(lastName);
		for (UserEntity u : uList) {
			result += u.getFirstName();
		}

		return result;
	}

	@GetMapping("/test/first")
	public String GetUserQueryFirstJpa(String firstName) {
		String result = "";

		List<UserEntity> uList = userRepository.findByFirstName(firstName);
		for (UserEntity u : uList) {
			result += u.getLastName();
		}

		return result;
	}

	@GetMapping("/test")
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/test/one")
	public String getOneUser(int id) {
		UserEntity ue = userRepository.getOne(id);
		return ue.toString();
	}

	@GetMapping("/test/find")
	public String findByfirstNameUser(String firstName) {
		UserEntity ue = userRepository.findByfirstName(firstName);
		return ue.toString();
	}

	@GetMapping("/test/insert")
	public String insertUser(String firstName, String lastName) {
		UserEntity ue = new UserEntity(firstName, lastName);
		// ue.setId(4);
		System.out.println(ue.toString());
		userRepository.save(ue);
		return "done";
	}

}
