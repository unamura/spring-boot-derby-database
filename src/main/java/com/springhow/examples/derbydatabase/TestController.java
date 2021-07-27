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

    @GetMapping("/test")
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/test/one")
    public String getOneUser(int id) {
    	UserEntity ue = userRepository.getOne(id);
    	return ue.getFirstName();
    }
    
    @GetMapping("/test/find")
    public String findByfirstNameUser(String firstName) {
    	UserEntity ue = userRepository.findByfirstName(firstName);
    	return ue.toString();
    }
    
    @GetMapping("/test/insert")
    public String insertUser( String firstName, String lastName) {
    	UserEntity ue = new UserEntity(firstName, lastName);
    	//ue.setId(4);
    	System.out.println(ue.toString());
    	userRepository.save(ue);
    	return "done";
    }
    
}
