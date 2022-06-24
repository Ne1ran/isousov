package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.CountryRepo;
import com.example.MadelaPractice.repository.DocsRepo;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.specification.UserFilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private DocsRepo docsRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private UserFilterSpecification userFilterSpecification;

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
        UserGetByIdModel model = UserGetByIdModel.toModel(userRepo.findById(id).get());
        model.setCitizenshipCode(userRepo.findById(id).get().getCountry_id().getCode());
        model.setCitizenshipName(userRepo.findById(id).get().getCountry_id().getName());
        model.setDocName(userRepo.findById(id).get().getDocument_id().getName());
        model.setDocCode(userRepo.findById(id).get().getDocument_id().getCode());
        model.setOfficeId(userRepo.findById(id).get().getOffice_id().getId());
        return model;
    }

    public UserEntity updateUser(UserUpdateInModel userUpdateInModel) throws EntityDoesNotExistException{
        if (!userRepo.existsById(userUpdateInModel.getId())){
            throw new EntityDoesNotExistException("User with this id doesn't exist!");
        }
        if (!officeRepo.existsById(userUpdateInModel.getOffice_id())){
            throw new EntityDoesNotExistException("Office with this office_id doesn't exist!");
        }
        if (!docsRepo.existsById(userUpdateInModel.getDocCode())){
            throw new EntityDoesNotExistException("Documents with this code doesn't exist!");
        }
        if (!countryRepo.existsById(userUpdateInModel.getCitizenshipCode())){
            throw new EntityDoesNotExistException("Country with this code doesn't exist!");
        }
        UserEntity userInDB = userRepo.findById(userUpdateInModel.getId()).get();
        userInDB.setPhone(userUpdateInModel.getPhone());
        userInDB.setLastName(userUpdateInModel.getLastName());
        userInDB.setMiddleName(userUpdateInModel.getMiddleName());
        userInDB.setFirstName(userUpdateInModel.getFirstName());
        userInDB.setIdentified(true);
        userInDB.setDocument_id(docsRepo.findByCode(userUpdateInModel.getDocCode()));
        userInDB.setCountry_id(countryRepo.findByCode(userUpdateInModel.getCitizenshipCode()));
        userInDB.setDocDate(userUpdateInModel.getDocDate());
        userInDB.setOffice_id(officeRepo.findById(userUpdateInModel.getOffice_id()).get());
        userInDB.setPosition(userUpdateInModel.getPosition());
        userInDB.setLogin(userUpdateInModel.getLogin());
        userInDB.setPassword(userUpdateInModel.getPassword());
        return userRepo.save(userInDB);
    }

    public List<UserEntity> getAllUsersList(UserListInModel model) {
        return userFilterSpecification.findUserFilter(model.getOfficeId(), model.getFirstName(),
                model.getLastName(), model.getMiddleName(), model.getPosition(), model.getDocCode(), model.getCitizenshipCode());
    }

    public UserEntity saveNewUser(UserSaveModel userSaveModel) throws EntityDoesNotExistException {
        if (!docsRepo.existsById(userSaveModel.getDocCode())){
            throw new EntityDoesNotExistException("Documents with this code doesn't exist!");
        }
        if (!countryRepo.existsById(userSaveModel.getCitizenshipCode())){
            throw new EntityDoesNotExistException("Country with this code doesn't exist!");
        }
        if (!officeRepo.existsById(userSaveModel.getOffice_id())){
            throw new EntityDoesNotExistException("Office with this office_id doesn't exist!");
        }
        UserEntity tempUser = UserSaveModel.fromModel(userSaveModel);
        tempUser.setCountry_id(countryRepo.findByCode(userSaveModel.getCitizenshipCode()));
        tempUser.setDocument_id(docsRepo.findByCode(userSaveModel.getDocCode()));
        tempUser.setOffice_id(officeRepo.findById(userSaveModel.getOffice_id()).get());
        return userRepo.save(tempUser);
    }
}