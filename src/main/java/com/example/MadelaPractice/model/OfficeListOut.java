package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

public class OfficeListOut {
    private Long id;
    private String name;
    private Boolean isActive;

    public static OfficeListOut toModel(OfficeEntity entity){
        OfficeListOut model = new OfficeListOut();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setActive(entity.getActive());
        return model;
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
