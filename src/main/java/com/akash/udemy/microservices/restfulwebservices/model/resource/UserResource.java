package com.akash.udemy.microservices.restfulwebservices.model.resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.akash.udemy.microservices.restfulwebservices.model.User;
import com.akash.udemy.microservices.restfulwebservices.model.exception.UserNotFoundException;
import com.akash.udemy.microservices.restfulwebservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Resource<User> getUserById(@PathVariable int id){

        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("id- %s not found",id));
        }
//      sending useful links along with the required resource using hateaos
//      add link for allUsers  1. create resource, 2. Add links, 3. Return Resource
        Resource<User> userResource = new Resource<>(user);
        ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        userResource.add(link.withRel("all-users"));

        return userResource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
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
