package com.example.MadelaPractice.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organization_entity")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    @Size(max = 255)
    private String name;

    @Column(name = "full_name", nullable = false, length = 512)
    @Size(max = 512)
    private String fullName;

    @Column(name = "inn", nullable = false, length = 20)
    @Size(max = 20)
    private String inn;

    @Column(name = "kpp", nullable = false, length = 20)
    @Size(max = 20)
    private String kpp;

    @Column(name = "address", nullable = false, length = 500)
    @Size(max = 500)
    private String address;

    @Column(name = "phone", nullable = false, length = 50)
    @Size(max = 50)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Email
    @Size(max = 320)
    @Column(name = "email", length = 320)
    private String email;

    @Size(max = 512)
    @Column(name = "website_url", length = 512)
    private String websiteUrl;

    @Column(name = "founded_at")
    private LocalDate foundedAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Size(max = 2000)
    @Column(name = "short_description", length = 2000)
    private String shortDescription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orgId", orphanRemoval = false)
    private List<OfficeEntity> offices = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractEntity> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentEntity> departments = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectEntity> projects = new ArrayList<>();

    public OrganizationEntity() {
    }

    @PreUpdate
    @PrePersist
    public void touchTimestamp() {
        lastModifiedAt = LocalDateTime.now();
    }

    public void addContract(ContractEntity contract) {
        contracts.add(contract);
        contract.setOrganization(this);
    }

    public void removeContract(ContractEntity contract) {
        contracts.remove(contract);
    }

    public void addDepartment(DepartmentEntity department) {
        departments.add(department);
        department.setOrganization(this);
    }

    public void removeDepartment(DepartmentEntity department) {
        departments.remove(department);
    }

    public void addProject(ProjectEntity project) {
        projects.add(project);
        project.setOrganization(this);
    }

    public void removeProject(ProjectEntity project) {
        projects.remove(project);
    }

    public List<OfficeEntity> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeEntity> offices) {
        this.offices = offices;
    }

    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
