package com.springhow.examples.derbydatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	/*UserEntity findByfirstName(String firstName);*/
}