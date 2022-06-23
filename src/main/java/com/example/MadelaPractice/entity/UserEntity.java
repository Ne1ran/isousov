package com.example.MadelaPractice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    private String phone;
    private Boolean isIdentified;
    private Date docDate;
    private String docNumber;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private OfficeEntity office_id;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocsEntity document_id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country_id;

    public UserEntity() {
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

    public DocsEntity getDocument_id() {
        return document_id;
    }

    public void setDocument_id(DocsEntity document_id) {
        this.document_id = document_id;
    }

    public CountryEntity getCountry_id() {
        return country_id;
    }

    public void setCountry_id(CountryEntity country_id) {
        this.country_id = country_id;
    }

    public OfficeEntity getOffice_id() {
        return office_id;
    }

    public void setOffice_id(OfficeEntity office_id) {
        this.office_id = office_id;
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

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
