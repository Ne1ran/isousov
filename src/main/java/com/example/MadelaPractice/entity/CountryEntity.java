package com.example.MadelaPractice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CountryEntity {
    @Id
    @Column(name = "code", nullable = false)
    @NotNull
    private Long code;

    @NotBlank
    @Size(max = 120)
    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Size(max = 2)
    @Column(name = "iso_alpha2", length = 2)
    private String isoAlpha2;

    @Size(max = 3)
    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "record_created_at")
    private LocalDateTime recordCreatedAt;

    @PositiveOrZero
    @Column(name = "population_estimate")
    private Long populationEstimate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "country_id", orphanRemoval = false)
    private List<UserEntity> users = new ArrayList<>();

    public CountryEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (recordCreatedAt == null) {
            recordCreatedAt = LocalDateTime.now();
        }
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoAlpha2() {
        return isoAlpha2;
    }

    public void setIsoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDateTime getRecordCreatedAt() {
        return recordCreatedAt;
    }

    public void setRecordCreatedAt(LocalDateTime recordCreatedAt) {
        this.recordCreatedAt = recordCreatedAt;
    }

    public Long getPopulationEstimate() {
        return populationEstimate;
    }

    public void setPopulationEstimate(Long populationEstimate) {
        this.populationEstimate = populationEstimate;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
