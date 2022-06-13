package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityDoesNowExistException;
import com.example.MadelaPractice.exception.NoNameException;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    public List<OrganizationEntity> getOrganizationsListByName(OrganizationEntity organizationEntity) throws NoNameException {
        if (organizationEntity.getName() == null){
            throw new NoNameException("There is no name to start searching!"); }
        return organizationRepo.findByNameAndInnAndIsActive(organizationEntity.getName(),
                organizationEntity.getInn(), organizationEntity.getActive());
    }

    public Optional<OrganizationEntity> getOrgById(Long id) throws EntityDoesNowExistException {
        if (!organizationRepo.existsById(id)){
            throw new EntityDoesNowExistException("Entity with this id doesn't exist!");
        }
        return organizationRepo.findById(id);
    }

    public OrganizationEntity saveOrganization(OrganizationEntity organizationEntity) {
        return organizationRepo.save(organizationEntity);
    }

    public OrganizationEntity updateOrganization(OrganizationEntity organizationEntity) throws EntityDoesNowExistException {
        if (!organizationRepo.existsById(organizationEntity.getId())){
            throw new EntityDoesNowExistException("There is no entity with this id");
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
