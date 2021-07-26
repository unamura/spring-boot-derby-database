package com.springhow.examples.derbydatabase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    private final UserRepository userRepository;

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
    
    /*@GetMapping("/test/find")
    public UserEntity findByfirstNameUser(String firstName) {
    	return userRepository.findByfirstName(firstName);
    }*/
}
