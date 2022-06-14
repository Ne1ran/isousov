package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registation(UserEntity userEntity) throws EntityAlreadyExistsException {
        if (userRepo.findByLogin(userEntity.getLogin()) != null){
            throw new EntityAlreadyExistsException("User with this login already exists!");
        }
        return userRepo.save(userEntity);
    }

    public UserEntity login(String login, String password) throws EntityDoesNotExistException {
        UserEntity user = userRepo.findByLoginAndPassword(login, password);
        if (user == null){
            throw new EntityDoesNotExistException("User doesn't exist!");
        }
        return user;
    }

   public UserEntity getUser(Long id) throws EntityDoesNotExistException {
        if (!userRepo.existsById(id)){
            throw new EntityDoesNotExistException("User doesn't exist!");
       }
       return userRepo.findById(id).get();
    }

    public UserEntity updateUser(UserEntity userEntity) throws EntityDoesNotExistException{
        if (!userRepo.existsById(userEntity.getId())){
            throw new EntityDoesNotExistException("User with this id doesn't exist!");
        }
        UserEntity userInDB = userRepo.findById(userEntity.getId()).get();
        userInDB.setCitizenshipCode(userEntity.getCitizenshipCode());
        userInDB.setDocCode(userEntity.getDocCode());
        userInDB.setDocDate(userEntity.getDocDate());
        userInDB.setDocNumber(userEntity.getDocNumber());
        userInDB.setPhone(userEntity.getPhone());
        userInDB.setLastName(userEntity.getLastName());
        userInDB.setMiddleName(userEntity.getMiddleName());
        userInDB.setFirstName(userEntity.getFirstName());
        userInDB.setIdentified(true);
        userInDB.setOfficeId(userEntity.getOfficeId());
        userInDB.setLogin(userEntity.getLogin());
        userInDB.setPassword(userEntity.getPassword());
        return userRepo.save(userInDB);
    }

    public Iterable<UserEntity> getAllUsersList() {
        return userRepo.findAll();
    }
}
