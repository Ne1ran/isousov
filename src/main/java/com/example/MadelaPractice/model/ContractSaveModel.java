package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Создание договора")
public class ContractSaveModel {

    @NotBlank
    @Size(max = 64)
    private String contractNumber;

    @NotNull
    private LocalDate signedAt;

    @NotNull
    private LocalDate validUntil;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;

    @NotBlank
    @Size(max = 32)
    private String status;

    @Size(max = 2000)
    private String terms;

    @NotNull
    private Long organizationId;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDate signedAt) {
        this.signedAt = signedAt;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
