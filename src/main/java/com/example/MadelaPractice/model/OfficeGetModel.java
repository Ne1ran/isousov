package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Карточка офиса")
public class OfficeGetModel {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean isActive;
    private Integer floorNumber;
    private BigDecimal areaSqm;
    private LocalDate openedOn;
    private String shiftNote;
    private String externalCode;

    public static OfficeGetModel toModel(OfficeEntity office) {
        OfficeGetModel model = new OfficeGetModel();
        model.setId(office.getId());
        model.setPhone(office.getPhone());
        model.setName(office.getName());
        model.setActive(office.getActive());
        model.setAddress(office.getAddress());
        model.setFloorNumber(office.getFloorNumber());
        model.setAreaSqm(office.getAreaSqm());
        model.setOpenedOn(office.getOpenedOn());
        model.setShiftNote(office.getShiftNote());
        model.setExternalCode(office.getExternalCode());
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

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public BigDecimal getAreaSqm() {
        return areaSqm;
    }

    public void setAreaSqm(BigDecimal areaSqm) {
        this.areaSqm = areaSqm;
    }

    public LocalDate getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(LocalDate openedOn) {
        this.openedOn = openedOn;
    }

    public String getShiftNote() {
        return shiftNote;
    }

    public void setShiftNote(String shiftNote) {
        this.shiftNote = shiftNote;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public OfficeGetModel() {
    }
}
