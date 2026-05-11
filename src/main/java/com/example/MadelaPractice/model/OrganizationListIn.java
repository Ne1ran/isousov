package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OrganizationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "Фильтр списка организаций")
public class OrganizationListIn {
    @NotBlank(message = "There is no name to start searching!")
    @Size(max = 255)
    @Schema(example = "ООО")
    private String name;
    @Size(max = 12)
    private String inn;
    private Boolean isActive;

    public static OrganizationListIn toModel(OrganizationEntity organizationEntity){
        OrganizationListIn organization = new OrganizationListIn();
        organization.setName(organizationEntity.getName());
        organization.setInn(organizationEntity.getInn());
        organization.setActive(organizationEntity.getActive());
        return organization;
    }

    public OrganizationListIn() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
