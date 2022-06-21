package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;

import java.util.Date;

public class UserSaveModel {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private Long docCode;
    private String docNumber;
    private Date docDate;
    private Long citizenshipCode;
    private Boolean isIdentified;
    private String position;
    private String citizenshipName;

    public static UserEntity fromModel(UserSaveModel model){
        UserEntity entity = new UserEntity();
        entity.setCitizenshipCode(model.getCitizenshipCode());
        entity.setDocCode(model.getDocCode());
        entity.setDocDate(model.getDocDate());
        entity.setDocNumber(model.getDocNumber());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setIdentified(model.getIdentified());
        entity.setCitizenshipName(model.getCitizenshipName());
        entity.setCitizenshipCode(model.getCitizenshipCode());
        entity.setMiddleName(model.getMiddleName());
        entity.setPhone(model.getPhone());
        entity.setPassword(model.getPassword());
        entity.setLogin(model.getLogin());
        return entity;
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

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
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
