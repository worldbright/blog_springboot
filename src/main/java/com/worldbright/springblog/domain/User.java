package com.worldbright.springblog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String auth;
}
