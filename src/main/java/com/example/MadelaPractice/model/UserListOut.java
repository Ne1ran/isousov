package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;

public class UserListOut {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String position;

    public static UserEntity fromModel(UserListOut model){
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setMiddleName(model.getMiddleName());
        entity.setLastName(model.getLastName());
        entity.setPosition(model.getPosition());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UserListOut() {
    }
}
