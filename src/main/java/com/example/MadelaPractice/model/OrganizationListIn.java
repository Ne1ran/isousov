package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;

public class OrganizationListIn {
    private String name;
    private String inn;
    private Boolean isActive;

    public static OrganizationListIn toModel(OrganizationEntity organizationEntity){
        OrganizationListIn organization = new OrganizationListIn();
        organization.setName(organizationEntity.getName());
        organization.setInn(organizationEntity.getInn());
        organization.setActive(organizationEntity.getActive());
        return organization;
    }

    public OrganizationListIn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
