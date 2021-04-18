package com.akash.udemy.microservices.restfulwebservices.repository;

import com.akash.udemy.microservices.restfulwebservices.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {
}
