package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.DepartmentEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.DepartmentSaveModel;
import com.example.MadelaPractice.repository.DepartmentRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public List<DepartmentEntity> listByOrganization(Long organizationId) {
        log.debug("Listing departments for organization {}", organizationId);
        return new ArrayList<>(departmentRepo.findByOrganization_Id(organizationId));
    }

    @Transactional
    public DepartmentEntity create(DepartmentSaveModel model) {
        log.info("Creating department code={} for organization {}", model.getCode(), model.getOrganizationId());
        if (departmentRepo.existsByOrganization_IdAndCode(model.getOrganizationId(), model.getCode())) {
            throw new EntityAlreadyExistsException("Department code already exists in this organization");
        }
        OrganizationEntity org = organizationRepo.findById(model.getOrganizationId())
                .orElseThrow(() -> new EntityDoesNotExistException("Organization not found"));
        DepartmentEntity d = new DepartmentEntity();
        d.setCode(model.getCode());
        d.setName(model.getName());
        d.setAnnualBudget(model.getAnnualBudget());
        d.setHeadName(model.getHeadName());
        org.addDepartment(d);
        DepartmentEntity saved = departmentRepo.save(d);
        log.info("Department id={} persisted", saved.getId());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting department id={}", id);
        DepartmentEntity d = departmentRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Department not found"));
        OrganizationEntity org = d.getOrganization();
        if (org != null) {
            org.removeDepartment(d);
            organizationRepo.save(org);
        } else {
            departmentRepo.delete(d);
        }
    }
}
