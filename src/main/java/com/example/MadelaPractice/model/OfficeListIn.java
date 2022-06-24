package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

import javax.validation.constraints.NotNull;

public class OfficeListIn {
    @NotNull
    private Long orgId;
    private String name;
    private String phone;
    private Boolean isActive;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public OfficeListIn() {
    }
}
