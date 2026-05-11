package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "Регистрация пользователя (шаг 1)")
public class UserRegistationModel {
    @NotBlank(message = "No login")
    @Size(max = 64)
    private String login;
    @NotBlank(message = "No password")
    @Size(max = 128)
    private String password;
    @NotBlank(message = "No name")
    @Size(max = 100)
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
