package com.example.MadelaPractice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Office {
    private Long id;
    private Long orgId;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
}
