package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserSaveModel {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String middleName;
    @NotEmpty
    private String phone;
    @NotNull
    private Long docCode;
    @NotEmpty
    private String docNumber;
    @NotNull
    private Date docDate;
    @NotNull
    private Long citizenshipCode;
    @NotNull
    private Boolean isIdentified;
    @NotEmpty
    private String position;
    @NotNull
    private Long office_id;

    public static UserEntity fromModel(UserSaveModel model){
        UserEntity entity = new UserEntity();
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setIdentified(model.getIdentified());
        entity.setMiddleName(model.getMiddleName());
        entity.setPhone(model.getPhone());
        entity.setPassword(model.getPassword());
        entity.setLogin(model.getLogin());
        entity.setDocNumber(model.getDocNumber());
        entity.setDocDate(model.getDocDate());
        entity.setPosition(model.getPosition());
        return entity;
    }

    public Long getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Long office_id) {
        this.office_id = office_id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public UserSaveModel() {
    }
}
