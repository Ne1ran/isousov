package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Создание офиса")
public class OfficeSaveModel {
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 500)
    private String address;
    @NotBlank
    @Size(max = 50)
    private String phone;
    @NotNull
    private Boolean isActive;
    @NotNull
    @Schema(description = "Id организации-владельца")
    private Long orgId;

    private Integer floorNumber;

    private BigDecimal areaSqm;

    private LocalDate openedOn;

    @Size(max = 500)
    private String shiftNote;

    @Size(max = 64)
    private String externalCode;

    public static OfficeEntity fromModel(OfficeSaveModel model) {
        OfficeEntity office = new OfficeEntity();
        office.setPhone(model.getPhone());
        office.setName(model.getName());
        office.setActive(model.getActive());
        office.setAddress(model.getAddress());
        office.setFloorNumber(model.getFloorNumber());
        office.setAreaSqm(model.getAreaSqm());
        office.setOpenedOn(model.getOpenedOn());
        office.setShiftNote(model.getShiftNote());
        office.setExternalCode(model.getExternalCode());
        return office;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public OfficeSaveModel() {
    }
}
