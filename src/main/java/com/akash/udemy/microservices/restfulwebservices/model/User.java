package com.akash.udemy.microservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("UserFilter")
@Entity
public class User {

    @Id
    private Integer id;
    @Size(min = 2,message = "Name should have at least 2 characters")
    private String name;
//    @Past
    private Date birthDate;

}
