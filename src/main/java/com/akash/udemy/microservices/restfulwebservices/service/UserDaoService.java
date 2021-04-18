package com.akash.udemy.microservices.restfulwebservices.service;

import com.akash.udemy.microservices.restfulwebservices.model.User;
import com.akash.udemy.microservices.restfulwebservices.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoService {

    @Autowired
    private UserRepo repo;

    public List<User> findAll(){
        return (List<User>) repo.findAll();
    }


    public User save(User user){
        if(user.getId()==null){
            user.setId((int) (repo.count()+1));
        }
        repo.save(user);
        return user;
    }

    public User findOne(int id){
        return repo.findById(id).orElse(null);

    }

    public User deleteUser(int id){
        User deletedUser = findOne(id);
        repo.deleteById(id);
        return deletedUser;


    }

}
