package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.SupplierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends CrudRepository<SupplierEntity, Long> {

    boolean existsByInn(String inn);
}
