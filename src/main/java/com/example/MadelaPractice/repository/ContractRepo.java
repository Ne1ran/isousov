package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.ContractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepo extends CrudRepository<ContractEntity, Long> {

    List<ContractEntity> findByOrganization_Id(Long organizationId);

    boolean existsByContractNumber(String contractNumber);
}
