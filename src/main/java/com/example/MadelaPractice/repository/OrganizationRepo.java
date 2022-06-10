package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.OrganizationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends CrudRepository<OrganizationEntity, Long> {
}
