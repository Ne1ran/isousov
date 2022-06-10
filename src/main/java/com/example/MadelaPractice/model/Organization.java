package com.example.MadelaPractice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Organization {
    private Long id;
    private String name;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;
}
