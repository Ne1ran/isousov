package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepo extends CrudRepository<OfficeEntity, Long>, JpaSpecificationExecutor {

    @Query("SELECT o FROM OfficeEntity o WHERE o.orgId.id = :orgId")
    List<OfficeEntity> findByOrganizationId(@Param("orgId") Long orgId);
}
