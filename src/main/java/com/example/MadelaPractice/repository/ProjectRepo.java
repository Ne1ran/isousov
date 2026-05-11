package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findByOrganization_Id(Long organizationId);

    boolean existsByOrganization_IdAndCode(Long organizationId, String code);
}
