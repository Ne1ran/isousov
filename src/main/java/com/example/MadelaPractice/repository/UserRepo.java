package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor {

    UserEntity findByLoginAndPassword(String login, String password);

    UserEntity findByLogin(String login);

    @Query("SELECT u FROM UserEntity u JOIN u.office_id o WHERE o.id = :officeId")
    List<UserEntity> findByOfficeId(@Param("officeId") Long officeId);
}
