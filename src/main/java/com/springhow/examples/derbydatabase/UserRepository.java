package com.springhow.examples.derbydatabase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByfirstName(String firstName);

	@Query("select u from UserEntity u where u.lastName = ?1")
	List<UserEntity> findByLastName(String lastName);
}