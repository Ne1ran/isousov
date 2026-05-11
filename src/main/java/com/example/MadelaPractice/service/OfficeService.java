package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.specification.OfficeFilterSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OfficeService {

    private static final Logger log = LoggerFactory.getLogger(OfficeService.class);

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OfficeFilterSpecification officeFilterSpecification;

    @Autowired
    private UserRepo userRepo;

    public List<OfficeEntity> getAllOffices() {
        log.debug("Loading all offices");
        return StreamSupport.stream(officeRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOfficeById(Long id) {
        log.info("Deleting office id={}", id);
        OfficeEntity office = officeRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Office with this id doesn't exist for searching!"));
        office.replaceSuppliers(new java.util.HashSet<>());
        userRepo.deleteAll(userRepo.findByOfficeId(id));
        officeRepo.delete(office);
        log.info("Office id={} deleted", id);
    }

    public OfficeEntity saveNewOffice(OfficeSaveModel model) {
        log.info("Creating office name={} for organization {}", model.getName(), model.getOrgId());
        if (!organizationRepo.existsById(model.getOrgId())) {
            throw new EntityDoesNotExistException("Organization with this orgId doesn't exist!");
        }
        OfficeEntity entity = OfficeSaveModel.fromModel(model);
        entity.setOrgId(organizationRepo.findById(model.getOrgId()).get());
        OfficeEntity saved = officeRepo.save(entity);
        log.info("Office id={} created", saved.getId());
        return saved;
    }

    public OfficeEntity updateOffice(OfficeUpdateModel model) {
        log.info("Updating office id={}", model.getId());
        if (!officeRepo.existsById(model.getId())) {
            throw new EntityDoesNotExistException("Office with this id doesn't exist for updating!");
        }
        if (!organizationRepo.existsById(model.getOrgId())) {
            throw new EntityDoesNotExistException("Organization with this orgId doesn't exist!");
        }
        OfficeEntity officeInDB = officeRepo.findById(model.getId()).get();
        officeInDB.setActive(model.getActive());
        officeInDB.setOrgId(organizationRepo.findById(model.getOrgId()).get());
        officeInDB.setName(model.getName());
        officeInDB.setAddress(model.getAddress());
        officeInDB.setPhone(model.getPhone());
        if (model.getFloorNumber() != null) {
            officeInDB.setFloorNumber(model.getFloorNumber());
        }
        if (model.getAreaSqm() != null) {
            officeInDB.setAreaSqm(model.getAreaSqm());
        }
        if (model.getOpenedOn() != null) {
            officeInDB.setOpenedOn(model.getOpenedOn());
        }
        if (model.getShiftNote() != null) {
            officeInDB.setShiftNote(model.getShiftNote());
        }
        if (model.getExternalCode() != null) {
            officeInDB.setExternalCode(model.getExternalCode());
        }
        return officeRepo.save(officeInDB);
    }

    public OfficeGetModel getOfficeById(Long id) {
        if (!officeRepo.existsById(id)) {
            throw new EntityDoesNotExistException("Office with this id doesn't exist for searching!");
        }
        return OfficeGetModel.toModel(officeRepo.findById(id).get());
    }

    public List<OfficeEntity> getOfficeList(OfficeListIn officeListIn) {
        if (!organizationRepo.existsById(officeListIn.getOrgId())) {
            throw new EntityDoesNotExistException("Organization for office with this orgId doesn't exist!");
        }
        return officeFilterSpecification.findOfficesFilter(officeListIn.getOrgId(), officeListIn.getName(), officeListIn.getPhone(),
                officeListIn.getActive());
    }

    @Transactional
    public OfficeEntity moveOfficeToOrganization(Long officeId, TargetOrganizationModel body) {
        log.info("Moving office id={} to organization id={}", officeId, body.getTargetOrganizationId());
        OfficeEntity office = officeRepo.findById(officeId)
                .orElseThrow(() -> new EntityDoesNotExistException("Office not found"));
        OrganizationEntity target = organizationRepo.findById(body.getTargetOrganizationId())
                .orElseThrow(() -> new EntityDoesNotExistException("Target organization not found"));
        office.setOrgId(target);
        OfficeEntity saved = officeRepo.save(office);
        log.info("Office id={} now belongs to organization id={}", saved.getId(), target.getId());
        return saved;
    }
}
