package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<DepartmentEntity, Long> {

    List<DepartmentEntity> findByOrganization_Id(Long organizationId);

    boolean existsByOrganization_IdAndCode(Long organizationId, String code);
}
