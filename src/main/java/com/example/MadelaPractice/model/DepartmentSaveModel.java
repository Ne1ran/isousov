package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "Создание подразделения")
public class DepartmentSaveModel {

    @NotBlank
    @Size(max = 32)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal annualBudget;

    @NotBlank
    @Size(max = 200)
    private String headName;

    @NotNull
    private Long organizationId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAnnualBudget() {
        return annualBudget;
    }

    public void setAnnualBudget(BigDecimal annualBudget) {
        this.annualBudget = annualBudget;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
