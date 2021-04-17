package com.akash.udemy.microservices.restfulwebservices.service;

import com.akash.udemy.microservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount=4;

    static {
        users.add(new User(1,"Akash",new Date()));
        users.add(new User(2,"Ashish",new Date()));
        users.add(new User(3,"Ozas",new Date()));
        users.add(new User(4,"Rishav",new Date()));

    }

    public List<User> findAll(){
        return users;
    }


    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        Optional<User> first = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        return first.orElse(null);

    }

    public User deleteUser(int id){
        User userToDelete = findOne(id);
        final boolean b = users.removeIf(user -> user.getId().equals(id));
        return userToDelete;


    }

}
