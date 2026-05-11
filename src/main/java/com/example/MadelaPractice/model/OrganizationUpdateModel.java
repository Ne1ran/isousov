package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Обновление организации")
public class OrganizationUpdateModel {
    @NotNull
    @Schema(description = "Идентификатор организации")
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 512)
    private String fullName;
    @NotBlank
    @Size(max = 20)
    private String inn;
    @NotBlank
    @Size(max = 20)
    private String kpp;
    @NotBlank
    @Size(max = 500)
    private String address;
    @NotBlank
    @Size(max = 50)
    private String phone;
    @NotNull
    private Boolean isActive;

    public static OrganizationEntity fromModel(OrganizationUpdateModel model){
        OrganizationEntity entity = new OrganizationEntity();
        entity.setId(model.getId());
        entity.setActive(model.getActive());
        entity.setName(model.getName());
        entity.setInn(model.getInn());
        entity.setName(model.getName());
        entity.setPhone(model.getPhone());
        entity.setKpp(model.getKpp());
        entity.setAddress(model.getAddress());
        return entity;
    }

    public OrganizationUpdateModel() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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
}
