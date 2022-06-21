package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.UserGetByIdModel;
import com.example.MadelaPractice.model.UserSaveModel;
import com.example.MadelaPractice.model.UserUpdateInModel;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OfficeRepo officeRepo;

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

   public UserGetByIdModel getUser(Long id) throws EntityDoesNotExistException {
        if (!userRepo.existsById(id)){
            throw new EntityDoesNotExistException("User doesn't exist!");
       }
       return UserGetByIdModel.toModel(userRepo.findById(id).get());
    }

    public UserEntity updateUser(Long officeId, UserUpdateInModel userUpdateInModel) throws EntityDoesNotExistException{
        if (!userRepo.existsById(userUpdateInModel.getId())){
            throw new EntityDoesNotExistException("User with this id doesn't exist!");
        }
        UserEntity userInDB = userRepo.findById(userUpdateInModel.getId()).get();
        userInDB.setCitizenshipCode(userUpdateInModel.getCitizenshipCode());
        userInDB.setDocCode(userUpdateInModel.getDocCode());
        userInDB.setDocDate(userUpdateInModel.getDocDate());
        userInDB.setDocNumber(userUpdateInModel.getDocNumber());
        userInDB.setPhone(userUpdateInModel.getPhone());
        userInDB.setLastName(userUpdateInModel.getLastName());
        userInDB.setMiddleName(userUpdateInModel.getMiddleName());
        userInDB.setFirstName(userUpdateInModel.getFirstName());
        userInDB.setIdentified(true);
        userInDB.setOffice_id(officeRepo.findById(officeId).get());
        userInDB.setCitizenshipName(userUpdateInModel.getCitizenshipName());
        userInDB.setPosition(userUpdateInModel.getPosition());
        userInDB.setLogin(userUpdateInModel.getLogin());
        userInDB.setPassword(userUpdateInModel.getPassword());
        return userRepo.save(userInDB);
    }

    public Iterable<UserEntity> getAllUsersList() {
        return userRepo.findAll();
    }

    public UserEntity saveNewUser(UserSaveModel userSaveModel) {
        return userRepo.save(UserSaveModel.fromModel(userSaveModel));
    }
}