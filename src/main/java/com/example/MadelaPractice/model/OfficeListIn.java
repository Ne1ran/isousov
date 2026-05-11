package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Фильтр списка офисов")
public class OfficeListIn {
    @NotNull
    @Schema(description = "Id организации")
    private Long orgId;
    @Size(max = 255)
    private String name;
    @Size(max = 50)
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
