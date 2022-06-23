package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;

public class UserListOut {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String position;

    public static UserListOut toModel(UserEntity entity){
        UserListOut model = new UserListOut();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setMiddleName(entity.getMiddleName());
        model.setLastName(entity.getLastName());
        model.setPosition(entity.getPosition());
        return model;
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
