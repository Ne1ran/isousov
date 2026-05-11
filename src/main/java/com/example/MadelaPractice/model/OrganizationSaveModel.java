package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Тело запроса: создание организации")
public class OrganizationSaveModel {
    @NotBlank
    @Size(max = 255)
    @Schema(example = "ООО Тест")
    private String name;
    @NotBlank
    @Size(max = 512)
    private String fullName;
    @NotBlank
    @Size(max = 20)
    @Schema(description = "ИНН", example = "7707083893")
    private String inn;
    @NotBlank
    @Size(max = 20)
    @Schema(description = "КПП", example = "770701001")
    private String kpp;
    @NotBlank
    @Size(max = 500)
    private String address;
    @NotBlank
    @Size(max = 50)
    private String phone;
    @NotNull
    @Schema(description = "Признак активности")
    private Boolean isActive;

    @Email
    @Size(max = 320)
    private String email;

    @Size(max = 512)
    private String websiteUrl;

    private LocalDate foundedAt;

    @Size(max = 2000)
    private String shortDescription;

    public static OrganizationEntity fromModel(OrganizationSaveModel model) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setActive(model.getActive());
        entity.setName(model.getName());
        entity.setInn(model.getInn());
        entity.setFullName(model.getFullName());
        entity.setPhone(model.getPhone());
        entity.setKpp(model.getKpp());
        entity.setAddress(model.getAddress());
        entity.setEmail(model.getEmail());
        entity.setWebsiteUrl(model.getWebsiteUrl());
        entity.setFoundedAt(model.getFoundedAt());
        entity.setShortDescription(model.getShortDescription());
        return entity;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public LocalDate getFoundedAt() {
        return foundedAt;
    }

    public void setFoundedAt(LocalDate foundedAt) {
        this.foundedAt = foundedAt;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public OrganizationSaveModel() {
    }
}
