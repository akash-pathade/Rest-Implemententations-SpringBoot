package com.akash.udemy.microservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    public PersonV1 personV1(){
        return new PersonV1("Akash Pathade");
    }
    public PersonV2 personV2(){
        return new PersonV2(new Name("Akash","Pathade"));
    }
}
