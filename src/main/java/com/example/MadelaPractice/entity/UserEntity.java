package com.example.MadelaPractice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "login", nullable = false, length = 64)
    private String login;

    @NotBlank
    @Size(max = 128)
    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "middle_name", nullable = false, length = 100)
    private String middleName;

    @NotBlank
    @Size(max = 200)
    @Column(name = "position", nullable = false, length = 200)
    private String position;

    @NotBlank
    @Size(max = 32)
    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @Column(name = "doc_date")
    private Date docDate;

    @NotBlank
    @Size(max = 64)
    @Column(name = "doc_number", nullable = false, length = 64)
    private String docNumber;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", foreignKey = @ForeignKey(name = "fk_user_office"))
    private OfficeEntity office_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", foreignKey = @ForeignKey(name = "fk_user_docs"))
    private DocsEntity document_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_user_country"))
    private CountryEntity country_id;

    @ManyToMany(mappedBy = "assignees", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<ProjectEntity> projects = new HashSet<>();

    public UserEntity() {
    }

    public void replaceProjects(Set<ProjectEntity> next) {
        for (ProjectEntity p : new HashSet<>(projects)) {
            p.removeAssignee(this);
        }
        projects.clear();
        if (next != null) {
            for (ProjectEntity p : next) {
                p.addAssignee(this);
            }
        }
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public DocsEntity getDocument_id() {
        return document_id;
    }

    public void setDocument_id(DocsEntity document_id) {
        this.document_id = document_id;
    }

    public CountryEntity getCountry_id() {
        return country_id;
    }

    public void setCountry_id(CountryEntity country_id) {
        this.country_id = country_id;
    }

    public OfficeEntity getOffice_id() {
        return office_id;
    }

    public void setOffice_id(OfficeEntity office_id) {
        this.office_id = office_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }
}
