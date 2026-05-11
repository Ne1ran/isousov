package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.SupplierEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.IdListModel;
import com.example.MadelaPractice.model.SupplierSaveModel;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.SupplierRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SupplierService {

    private static final Logger log = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private OfficeRepo officeRepo;

    public Iterable<SupplierEntity> findAll() {
        log.debug("Listing all suppliers");
        return supplierRepo.findAll();
    }

    @Transactional
    public SupplierEntity create(SupplierSaveModel model) {
        log.info("Creating supplier inn={}", model.getInn());
        if (supplierRepo.existsByInn(model.getInn())) {
            throw new EntityAlreadyExistsException("Supplier with this INN already exists");
        }
        SupplierEntity s = new SupplierEntity();
        s.setLegalName(model.getLegalName());
        s.setInn(model.getInn());
        s.setContactEmail(model.getContactEmail());
        s.setRating(model.getRating());
        s.setCommentText(model.getCommentText());
        SupplierEntity saved = supplierRepo.save(s);
        log.info("Supplier id={} persisted", saved.getId());
        return saved;
    }

    @Transactional
    public SupplierEntity replaceOffices(Long supplierId, IdListModel body) {
        log.info("Replacing offices for supplier id={}, office count={}", supplierId, body.getIds().size());
        SupplierEntity supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new EntityDoesNotExistException("Supplier not found"));
        for (OfficeEntity o : new HashSet<>(supplier.getOffices())) {
            supplier.unlinkOffice(o);
        }
        for (Long officeId : body.getIds()) {
            OfficeEntity office = officeRepo.findById(officeId)
                    .orElseThrow(() -> new EntityDoesNotExistException("Office not found: " + officeId));
            supplier.linkOffice(office);
        }
        SupplierEntity saved = supplierRepo.save(supplier);
        log.info("Supplier id={} offices updated", saved.getId());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting supplier id={}", id);
        SupplierEntity s = supplierRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Supplier not found"));
        for (OfficeEntity o : new HashSet<>(s.getOffices())) {
            s.unlinkOffice(o);
        }
        supplierRepo.delete(s);
    }
}
