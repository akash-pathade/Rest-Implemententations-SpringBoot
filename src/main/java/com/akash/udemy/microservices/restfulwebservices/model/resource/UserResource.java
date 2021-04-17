package com.akash.udemy.microservices.restfulwebservices.model.resource;

import com.akash.udemy.microservices.restfulwebservices.model.User;
import com.akash.udemy.microservices.restfulwebservices.model.exception.UserNotFoundException;
import com.akash.udemy.microservices.restfulwebservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){

        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("id- %s not found",id));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser( @RequestBody User user){
        User savedUser = service.save(user);

//        We want to send URI of the newly created user
//        Get Current Path and append the useridto it, this will be the URI
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

//        Send ResponseEntity with HTTP Status = created

        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("/users/{id}")
    public User deleteUserById(@PathVariable int id){

        User user = service.deleteUser(id);
        if(user==null){
            throw new UserNotFoundException(String.format("id- %s not found",id));
        }
        return user;
    }

}
