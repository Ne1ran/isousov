package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.exception.NoNameException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.specification.OrganizationFilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OrganizationFilterSpecification organizationFilterSpecification;

    public List<OrganizationEntity> getOrganizationsListByName(OrganizationListIn organizationListIn) throws NoNameException {
        if (organizationListIn.getName() == null){
            throw new NoNameException("There is no name to start searching!"); }
        return organizationFilterSpecification.findOrganizationFilter(organizationListIn.getName(), organizationListIn.getInn(),
                organizationListIn.getActive());
    }

    public OrganizationGetByIdModel getOrgById(Long id) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(id)){
            throw new EntityDoesNotExistException("Entity with this id doesn't exist!");
        }
        return OrganizationGetByIdModel.toModel(organizationRepo.findById(id).get());
    }

    public OrganizationEntity saveOrganization(OrganizationSaveModel model) {
        return organizationRepo.save(OrganizationSaveModel.fromModel(model));
    }

    public OrganizationEntity updateOrganization(OrganizationUpdateModel model) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(model.getId())){
            throw new EntityDoesNotExistException("There is no entity with this id");
        }
        OrganizationEntity organizationInDB = organizationRepo.findById(model.getId()).get();
        organizationInDB.setName(model.getName());
        organizationInDB.setFullName(model.getFullName());
        organizationInDB.setActive(true);
        organizationInDB.setAddress(model.getAddress());
        organizationInDB.setPhone(model.getPhone());
        organizationInDB.setKpp(model.getKpp());
        organizationInDB.setInn(model.getInn());
        return organizationRepo.save(organizationInDB);
    }
}
