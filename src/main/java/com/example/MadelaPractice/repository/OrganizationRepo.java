package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.OrganizationEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrganizationRepo extends CrudRepository<OrganizationEntity, Long>, JpaSpecificationExecutor {

    List<OrganizationEntity> findByNameAndInnAndIsActive(String name, String inn, Boolean isActive);
}
