package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public OfficeEntity saveNewOffice(OfficeEntity officeEntity) {
        return officeRepo.save(officeEntity);
    }

    public OfficeEntity updateOffice(OfficeEntity officeEntity) throws EntityDoesNotExistException {
        if (!officeRepo.existsById(officeEntity.getId())){
            throw new EntityDoesNotExistException("Office with this id doesn't exist for updating!");
        }
        OfficeEntity officeInDB = officeRepo.findById(officeEntity.getId()).get();
        officeInDB.setActive(true);
        officeInDB.setOrgId(officeEntity.getOrgId());
        officeInDB.setName(officeEntity.getName());
        officeInDB.setAddress(officeEntity.getAddress());
        officeInDB.setPhone(officeEntity.getPhone());
        return officeRepo.save(officeEntity);
    }

    public OfficeEntity getOfficeById(Long id) throws EntityDoesNotExistException {
        if (!officeRepo.existsById(id)){
            throw new EntityDoesNotExistException("Office with this id doesn't exist for searching!");
        }
        return officeRepo.findById(id).get();
    }

    public List<OfficeEntity> getOfficeList(Long orgId, OfficeEntity officeEntity) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(orgId)){
            throw new EntityDoesNotExistException("Organization for office with this orgId doesn't exist!");
        }
        return officeRepo.findAllByOrgId(orgId);
    }
}
