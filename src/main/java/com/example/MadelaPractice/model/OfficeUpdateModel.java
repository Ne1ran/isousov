package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.repository.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficeUpdateModel {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
    private Long orgId;

    public static OfficeEntity fromModel(OfficeUpdateModel model, OrganizationEntity organizationEntity){
        OfficeEntity office = new OfficeEntity();
        office.setId(model.getId());
        office.setPhone(model.getPhone());
        office.setName(model.getName());
        office.setActive(model.getActive());
        office.setAddress(model.getAddress());
        office.setOrgId(organizationEntity);
        return office;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public OfficeUpdateModel() {
    }
}
