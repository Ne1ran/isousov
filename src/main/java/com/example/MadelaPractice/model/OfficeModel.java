package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

public class OfficeModel {
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;

    public static OfficeModel toModel(OfficeEntity office){
        OfficeModel model = new OfficeModel();
        model.setPhone(office.getPhone());
        model.setName(office.getName());
        model.setActive(office.getActive());
        model.setAddress(office.getAddress());
        return model;
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

    public OfficeModel() {
    }
}
