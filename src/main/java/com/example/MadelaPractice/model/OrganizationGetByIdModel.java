package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;

public class OrganizationGetByIdModel {
    private Long id;
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private Boolean isActive;

    public OrganizationGetByIdModel() {
    }

    public static OrganizationGetByIdModel toModel(OrganizationEntity organizationEntity){
        OrganizationGetByIdModel organization = new OrganizationGetByIdModel();
        organization.setId(organizationEntity.getId());
        organization.setName(organizationEntity.getName());
        organization.setFullName(organizationEntity.getFullName());
        organization.setInn(organizationEntity.getInn());
        organization.setKpp(organizationEntity.getKpp());
        organization.setActive(organizationEntity.getActive());
        organization.setAddress(organizationEntity.getAddress());
        organization.setPhone(organizationEntity.getPhone());
        return organization;
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
