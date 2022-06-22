package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

public class OfficeGetModel {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;

    public static OfficeGetModel toModel(OfficeEntity office){
        OfficeGetModel model = new OfficeGetModel();
        model.setId(office.getId());
        model.setPhone(office.getPhone());
        model.setName(office.getName());
        model.setActive(office.getActive());
        model.setAddress(office.getAddress());
        return model;
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

    public OfficeGetModel() {
    }
}
