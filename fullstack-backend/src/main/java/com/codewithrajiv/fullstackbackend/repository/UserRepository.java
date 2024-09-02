package com.codewithrajiv.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.codewithrajiv.fullstackbackend.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	

}
