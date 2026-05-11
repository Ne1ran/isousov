package com.example.MadelaPractice.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "office_entity")
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    @NotBlank
    @Size(max = 255)
    private String name;

    @Column(name = "address", nullable = false, length = 500)
    @NotBlank
    @Size(max = 500)
    private String address;

    @Column(name = "phone", nullable = false, length = 50)
    @NotBlank
    @Size(max = 50)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Min(0)
    @Max(200)
    @Column(name = "floor_number")
    private Integer floorNumber;

    @DecimalMin("0.0")
    @Digits(integer = 6, fraction = 2)
    @Column(name = "area_sqm", precision = 10, scale = 2)
    private BigDecimal areaSqm;

    @Column(name = "opened_on")
    private LocalDate openedOn;

    @Size(max = 500)
    @Column(name = "shift_note", length = 500)
    private String shiftNote;

    @Size(max = 64)
    @Column(name = "external_code", length = 64)
    private String externalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId", foreignKey = @ForeignKey(name = "fk_office_organization"))
    private OrganizationEntity orgId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "office_id", orphanRemoval = false)
    private List<UserEntity> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "office_supplier",
            joinColumns = @JoinColumn(name = "office_id", foreignKey = @ForeignKey(name = "fk_office_supplier_office")),
            inverseJoinColumns = @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_office_supplier_supplier")))
    private Set<SupplierEntity> suppliers = new HashSet<>();

    public OfficeEntity() {
    }

    public void replaceSuppliers(Set<SupplierEntity> next) {
        for (SupplierEntity s : new HashSet<>(suppliers)) {
            s.unlinkOffice(this);
        }
        suppliers.clear();
        if (next != null) {
            for (SupplierEntity s : next) {
                s.linkOffice(this);
            }
        }
    }

    public OrganizationEntity getOrgId() {
        return orgId;
    }

    public void setOrgId(OrganizationEntity orgId) {
        this.orgId = orgId;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public Set<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
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

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public BigDecimal getAreaSqm() {
        return areaSqm;
    }

    public void setAreaSqm(BigDecimal areaSqm) {
        this.areaSqm = areaSqm;
    }

    public LocalDate getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(LocalDate openedOn) {
        this.openedOn = openedOn;
    }

    public String getShiftNote() {
        return shiftNote;
    }

    public void setShiftNote(String shiftNote) {
        this.shiftNote = shiftNote;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }
}
