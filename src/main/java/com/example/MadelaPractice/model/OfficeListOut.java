package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

public class OfficeListOut {
    private Long id;
    private String name;
    private Boolean isActive;

    public static OfficeEntity fromModel(OfficeListOut officeListOut){
        OfficeEntity entity = new OfficeEntity();
        entity.setId(officeListOut.getId());
        entity.setName(officeListOut.getName());
        entity.setActive(officeListOut.getActive());
        return entity;
    }

    public OfficeListOut() {
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
