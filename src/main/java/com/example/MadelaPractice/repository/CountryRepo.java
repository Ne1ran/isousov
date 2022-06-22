package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepo extends CrudRepository<CountryEntity, Long> {
    CountryEntity findByCode(Long code);
}
