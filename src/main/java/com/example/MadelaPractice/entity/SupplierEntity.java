package com.example.MadelaPractice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supplier_entity")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "legal_name", nullable = false, length = 255)
    private String legalName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "inn", nullable = false, length = 20)
    private String inn;

    @NotBlank
    @Email
    @Size(max = 320)
    @Column(name = "contact_email", nullable = false, length = 320)
    private String contactEmail;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private Integer rating;

    @Size(max = 500)
    @Column(name = "comment_text", length = 500)
    private String commentText;

    @ManyToMany(mappedBy = "suppliers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<OfficeEntity> offices = new HashSet<>();

    public SupplierEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (registeredAt == null) {
            registeredAt = LocalDateTime.now();
        }
    }

    public void linkOffice(OfficeEntity office) {
        offices.add(office);
        office.getSuppliers().add(this);
    }

    public void unlinkOffice(OfficeEntity office) {
        offices.remove(office);
        office.getSuppliers().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Set<OfficeEntity> getOffices() {
        return offices;
    }

    public void setOffices(Set<OfficeEntity> offices) {
        this.offices = offices;
    }
}
