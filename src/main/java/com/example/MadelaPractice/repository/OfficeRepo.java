package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.OfficeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends CrudRepository<OfficeEntity, Long> {
    ResponseEntity findAllById(Long orgId);
}
