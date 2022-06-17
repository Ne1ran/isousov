package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;

public class OrganizationListOut {
    private Long id;
    private String name;
    private Boolean isActive;

    public OrganizationListOut() {
    }

    public static OrganizationListOut toModel(OrganizationEntity organizationEntity){
        OrganizationListOut organization = new OrganizationListOut();
        organization.setId(organizationEntity.getId());
        organization.setName(organizationEntity.getName());
        organization.setActive(organizationEntity.getActive());
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
