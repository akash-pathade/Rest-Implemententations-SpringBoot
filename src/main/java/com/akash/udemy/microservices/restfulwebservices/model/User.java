package com.akash.udemy.microservices.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    @Size(min = 2,message = "Name should have at least 2 characters")
    private String name;
    @Past
    private Date birthDate;

}
