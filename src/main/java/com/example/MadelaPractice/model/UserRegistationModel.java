package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;

import javax.validation.constraints.NotEmpty;

public class UserRegistationModel {
    @NotEmpty(message = "No login")
    private String login;
    @NotEmpty(message = "No password")
    private String password;
    @NotEmpty(message = "No name")
    private String firstName;

    public static UserEntity fromModel(UserRegistationModel model){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(model.getLogin());
        userEntity.setFirstName(model.getFirstName());
        userEntity.setPassword(model.getPassword());
        return userEntity;
    }

    public UserRegistationModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
