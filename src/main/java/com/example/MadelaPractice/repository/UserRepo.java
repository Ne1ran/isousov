package com.example.MadelaPractice.repository;

import com.example.MadelaPractice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor {

    UserEntity findByLoginAndPassword(String login, String password);

    UserEntity findByLogin(String login);
}
