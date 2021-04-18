package com.akash.udemy.microservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


    //URI Versioning
    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Akash Pathade");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Akash","Pathade"));
    }

    //Param based versioning
    @GetMapping(value = "person/param/", params = "version=1")
    public PersonV1 personV1Param(){
        return new PersonV1("Akash Pathade");
    }

    @GetMapping(value = "person/param/", params = "version=2")
    public PersonV2 personV2Param(){
        return new PersonV2(new Name("Akash","Pathade"));
    }

    //Header/Content Negotiation based versioning
    @GetMapping(value = "person/header/", headers = "X-API-VERSION=1")
    public PersonV1 personV1Headers(){
        return new PersonV1("Akash Pathade");
    }

    @GetMapping(value = "person/header/", headers = "X-API-VERSION=2")
    public PersonV2 personV2Headers(){
        return new PersonV2(new Name("Akash","Pathade"));
    }

    //Produces/Mine based versioning
    @GetMapping(value = "person/produces/", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1Produces(){
        return new PersonV1("Akash Pathade");
    }

    @GetMapping(value = "person/produces/", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2Produces(){
        return new PersonV2(new Name("Akash","Pathade"));
    }

}
