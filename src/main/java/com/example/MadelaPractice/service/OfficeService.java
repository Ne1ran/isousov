package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.OfficeGetModel;
import com.example.MadelaPractice.model.OfficeSaveModel;
import com.example.MadelaPractice.model.OfficeUpdateModel;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public OfficeEntity saveNewOffice(OfficeSaveModel model) {
        return officeRepo.save(OfficeSaveModel.fromModel(model));
    }

    public OfficeEntity updateOffice(OfficeUpdateModel model) throws EntityDoesNotExistException {
        if (!officeRepo.existsById(model.getId())){
            throw new EntityDoesNotExistException("Office with this id doesn't exist for updating!");
        }
        if (!organizationRepo.existsById(model.getOrgId())) {
            throw new EntityDoesNotExistException("Organization with this orgId doesn't exist!");
        }
        OfficeEntity officeInDB = officeRepo.findById(model.getId()).get();
        officeInDB.setActive(true);
        officeInDB.setOrgId(organizationRepo.findById(model.getOrgId()).get());
        officeInDB.setName(model.getName());
        officeInDB.setAddress(model.getAddress());
        officeInDB.setPhone(model.getPhone());
        return officeRepo.save(officeInDB);
    }

    public OfficeGetModel getOfficeById(Long id) throws EntityDoesNotExistException {
        if (!officeRepo.existsById(id)){
            throw new EntityDoesNotExistException("Office with this id doesn't exist for searching!");
        }
        return OfficeGetModel.toModel(officeRepo.findById(id).get());
    }

    public List<OfficeEntity> getOfficeList(Long orgId, OfficeEntity officeEntity) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(orgId)){
            throw new EntityDoesNotExistException("Organization for office with this orgId doesn't exist!");
        }
        return (List<OfficeEntity>) officeRepo.findAll();
    }
}
