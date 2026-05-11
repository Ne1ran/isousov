package com.example.MadelaPractice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "department_entity",
        uniqueConstraints = @UniqueConstraint(name = "uk_dept_org_code", columnNames = {"organization_id", "code"}))
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 32)
    @Column(name = "code", nullable = false, length = 32)
    private String code;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "opened_at", nullable = false)
    private LocalDateTime openedAt;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 12, fraction = 2)
    @Column(name = "annual_budget", nullable = false, precision = 14, scale = 2)
    private BigDecimal annualBudget;

    @NotBlank
    @Size(max = 200)
    @Column(name = "head_name", nullable = false, length = 200)
    private String headName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_department_organization"))
    @JsonIgnore
    private OrganizationEntity organization;

    public DepartmentEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (openedAt == null) {
            openedAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }
}
