package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.ContractEntity;
import com.example.MadelaPractice.entity.DepartmentEntity;
import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.entity.ProjectEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.exception.NoNameException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.ContractRepo;
import com.example.MadelaPractice.repository.DepartmentRepo;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.ProjectRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.specification.OrganizationFilterSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrganizationService {

    private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OrganizationFilterSpecification organizationFilterSpecification;

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<OrganizationEntity> getAllOrganizations() {
        log.debug("Loading all organizations");
        return StreamSupport.stream(organizationRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrganizationById(Long id) {
        log.info("Deleting organization id={} with cascade cleanup of offices and projects", id);
        if (!organizationRepo.existsById(id)) {
            throw new EntityDoesNotExistException("Entity with this id doesn't exist!");
        }
        for (ProjectEntity project : new ArrayList<>(projectRepo.findByOrganization_Id(id))) {
            project.clearAssignees();
            projectRepo.delete(project);
        }
        for (ContractEntity contract : new ArrayList<>(contractRepo.findByOrganization_Id(id))) {
            contractRepo.delete(contract);
        }
        for (DepartmentEntity department : new ArrayList<>(departmentRepo.findByOrganization_Id(id))) {
            departmentRepo.delete(department);
        }
        List<OfficeEntity> offices = officeRepo.findByOrganizationId(id);
        for (OfficeEntity office : offices) {
            office.replaceSuppliers(new java.util.HashSet<>());
            userRepo.deleteAll(userRepo.findByOfficeId(office.getId()));
            officeRepo.delete(office);
        }
        organizationRepo.deleteById(id);
        log.info("Organization id={} deleted", id);
    }

    public List<OrganizationEntity> getOrganizationsListByName(OrganizationListIn organizationListIn) {
        if (organizationListIn.getName() == null) {
            throw new NoNameException("There is no name to start searching!");
        }
        log.debug("Filtering organizations by name={}", organizationListIn.getName());
        return organizationFilterSpecification.findOrganizationFilter(organizationListIn.getName(), organizationListIn.getInn(),
                organizationListIn.getActive());
    }

    public OrganizationGetByIdModel getOrgById(Long id) {
        if (!organizationRepo.existsById(id)) {
            throw new EntityDoesNotExistException("Entity with this id doesn't exist!");
        }
        return OrganizationGetByIdModel.toModel(organizationRepo.findById(id).get());
    }

    public OrganizationEntity saveOrganization(OrganizationSaveModel model) {
        log.info("Saving new organization name={}", model.getName());
        OrganizationEntity saved = organizationRepo.save(OrganizationSaveModel.fromModel(model));
        log.info("Organization id={} created", saved.getId());
        return saved;
    }

    public OrganizationEntity updateOrganization(OrganizationUpdateModel model) {
        if (!organizationRepo.existsById(model.getId())) {
            throw new EntityDoesNotExistException("There is no entity with this id");
        }
        OrganizationEntity organizationInDB = organizationRepo.findById(model.getId()).get();
        organizationInDB.setName(model.getName());
        organizationInDB.setFullName(model.getFullName());
        organizationInDB.setActive(model.getActive());
        organizationInDB.setAddress(model.getAddress());
        organizationInDB.setPhone(model.getPhone());
        organizationInDB.setKpp(model.getKpp());
        organizationInDB.setInn(model.getInn());
        organizationInDB.setEmail(model.getEmail());
        organizationInDB.setWebsiteUrl(model.getWebsiteUrl());
        organizationInDB.setFoundedAt(model.getFoundedAt());
        organizationInDB.setShortDescription(model.getShortDescription());
        OrganizationEntity saved = organizationRepo.save(organizationInDB);
        log.info("Organization id={} updated", saved.getId());
        return saved;
    }
}
