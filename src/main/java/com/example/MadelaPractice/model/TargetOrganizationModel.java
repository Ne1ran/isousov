package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Смена организации-владельца")
public class TargetOrganizationModel {

    @NotNull
    private Long targetOrganizationId;

    public Long getTargetOrganizationId() {
        return targetOrganizationId;
    }

    public void setTargetOrganizationId(Long targetOrganizationId) {
        this.targetOrganizationId = targetOrganizationId;
    }
}
