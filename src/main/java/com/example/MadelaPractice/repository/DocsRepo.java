package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.DocsEntity;
import org.springframework.data.repository.CrudRepository;

public interface DocsRepo extends CrudRepository<DocsEntity, Long> {
    DocsEntity findByCode(Long code);
}
