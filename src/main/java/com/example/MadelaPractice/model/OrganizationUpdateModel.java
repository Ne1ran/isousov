package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrganizationUpdateModel {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String inn;
    @NotEmpty
    private String kpp;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phone;
    @NotNull
    private Boolean isActive;

    public static OrganizationEntity fromModel(OrganizationUpdateModel model){
        OrganizationEntity entity = new OrganizationEntity();
        entity.setId(model.getId());
        entity.setActive(model.getActive());
        entity.setName(model.getName());
        entity.setInn(model.getInn());
        entity.setName(model.getName());
        entity.setPhone(model.getPhone());
        entity.setKpp(model.getKpp());
        entity.setAddress(model.getAddress());
        return entity;
    }

    public OrganizationUpdateModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
