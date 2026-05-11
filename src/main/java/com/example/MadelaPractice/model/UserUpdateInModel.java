package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Schema(description = "Обновление пользователя")
public class UserUpdateInModel {
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 64)
    private String login;
    @NotBlank
    @Size(max = 128)
    private String password;
    @NotBlank
    @Size(max = 100)
    private String firstName;
    @NotBlank
    @Size(max = 100)
    private String lastName;
    @NotBlank
    @Size(max = 100)
    private String middleName;
    @NotBlank
    @Size(max = 32)
    private String phone;
    @NotNull
    private Long docCode;
    @NotBlank
    @Size(max = 64)
    private String docNumber;
    @NotNull
    private Date docDate;
    @NotNull
    private Long citizenshipCode;
    @NotNull
    private Boolean isIdentified;
    @NotBlank
    @Size(max = 200)
    private String position;
    @NotNull
    private Long office_id;

    private LocalDate hireDate;

    private LocalDate birthDate;

    public static UserGetByIdModel toModel(UserEntity entity){
        UserGetByIdModel model = new UserGetByIdModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setIdentified(entity.getIdentified());
        model.setMiddleName(entity.getMiddleName());
        model.setPhone(entity.getPhone());
        model.setPassword(entity.getPassword());
        model.setLogin(entity.getLogin());
        return model;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserUpdateInModel() {
    }
}
