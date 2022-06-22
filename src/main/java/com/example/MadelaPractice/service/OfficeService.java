package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.specification.OfficeFilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OfficeFilterSpecification officeFilterSpecification;

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

    public List<OfficeEntity> getOfficeList(OfficeListIn officeListIn) throws EntityDoesNotExistException {
        if (!organizationRepo.existsById(officeListIn.getOrgId())){
            throw new EntityDoesNotExistException("Organization for office with this orgId doesn't exist!");
        }
        return officeFilterSpecification.findOfficesFilter(officeListIn.getOrgId(), officeListIn.getName(), officeListIn.getPhone(),
                officeListIn.getActive());
    }
}
