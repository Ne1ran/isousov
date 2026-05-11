package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public static OfficeEntity fromModel(OfficeSaveModel model){
        OfficeEntity office = new OfficeEntity();
        office.setPhone(model.getPhone());
        office.setName(model.getName());
        office.setActive(model.getActive());
        office.setAddress(model.getAddress());
        return office;
    }

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
