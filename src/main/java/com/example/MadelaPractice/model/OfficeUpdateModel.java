package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Обновление офиса")
public class OfficeUpdateModel {
    @NotNull
    private Long id;
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
