package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.ContractEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.ContractSaveModel;
import com.example.MadelaPractice.repository.ContractRepo;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {

    private static final Logger log = LoggerFactory.getLogger(ContractService.class);

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public List<ContractEntity> listByOrganization(Long organizationId) {
        log.debug("Listing contracts for organization {}", organizationId);
        return new ArrayList<>(contractRepo.findByOrganization_Id(organizationId));
    }

    @Transactional
    public ContractEntity create(ContractSaveModel model) {
        log.info("Creating contract number={} for organization {}", model.getContractNumber(), model.getOrganizationId());
        if (contractRepo.existsByContractNumber(model.getContractNumber())) {
            throw new EntityAlreadyExistsException("Contract with this number already exists");
        }
        OrganizationEntity org = organizationRepo.findById(model.getOrganizationId())
                .orElseThrow(() -> new EntityDoesNotExistException("Organization not found"));
        ContractEntity c = new ContractEntity();
        c.setContractNumber(model.getContractNumber());
        c.setSignedAt(model.getSignedAt());
        c.setValidUntil(model.getValidUntil());
        c.setAmount(model.getAmount());
        c.setStatus(model.getStatus());
        c.setTerms(model.getTerms());
        org.addContract(c);
        ContractEntity saved = contractRepo.save(c);
        log.info("Contract id={} persisted", saved.getId());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting contract id={}", id);
        ContractEntity c = contractRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Contract not found"));
        OrganizationEntity org = c.getOrganization();
        if (org != null) {
            org.removeContract(c);
            organizationRepo.save(org);
        } else {
            contractRepo.delete(c);
        }
    }
}
