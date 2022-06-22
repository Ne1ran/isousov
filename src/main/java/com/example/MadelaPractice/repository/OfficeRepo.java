package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepo extends CrudRepository<OfficeEntity, Long>, JpaSpecificationExecutor {
//    ResponseEntity findAllById(Long orgId);
//    List<OfficeEntity> findAllByOrgId(Long orgId);

}
