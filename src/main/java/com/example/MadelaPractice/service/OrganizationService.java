package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.exception.NoNameException;
import com.example.MadelaPractice.model.OrganizationListIn;
import com.example.MadelaPractice.model.OrganizationListOut;
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

    public OrganizationEntity getOrgById(Long id) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(id)){
            throw new EntityDoesNotExistException("Entity with this id doesn't exist!");
        }
        return organizationRepo.findById(id).get();
    }

    public OrganizationEntity saveOrganization(OrganizationEntity organizationEntity) {
        return organizationRepo.save(organizationEntity);
    }

    public OrganizationEntity updateOrganization(OrganizationEntity organizationEntity) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(organizationEntity.getId())){
            throw new EntityDoesNotExistException("There is no entity with this id");
        }
        OrganizationEntity organizationInDB = organizationRepo.findById(organizationEntity.getId()).get();
        organizationInDB.setName(organizationEntity.getName());
        organizationInDB.setFullName(organizationEntity.getFullName());
        organizationInDB.setActive(true);
        organizationInDB.setAddress(organizationEntity.getAddress());
        organizationInDB.setPhone(organizationEntity.getPhone());
        organizationInDB.setKpp(organizationEntity.getKpp());
        organizationInDB.setInn(organizationEntity.getInn());
        return organizationRepo.save(organizationInDB);
    }
}
