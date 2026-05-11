package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.ProjectEntity;
import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.*;
import com.example.MadelaPractice.repository.CountryRepo;
import com.example.MadelaPractice.repository.DocsRepo;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.repository.ProjectRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.specification.UserFilterSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private DocsRepo docsRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserFilterSpecification userFilterSpecification;

    public List<UserEntity> getAllUsers() {
        log.debug("Loading all users");
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(Long id) {
        log.info("Deleting user id={}", id);
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("User doesn't exist!"));
        user.replaceProjects(new HashSet<>());
        userRepo.deleteById(id);
        log.info("User id={} deleted", id);
    }

    public UserEntity registation(UserEntity userEntity) {
        log.info("Registering user login={}", userEntity.getLogin());
        if (userRepo.findByLogin(userEntity.getLogin()) != null) {
            throw new EntityAlreadyExistsException("User with this login already exists!");
        }
        UserEntity saved = userRepo.save(userEntity);
        log.info("User id={} registered", saved.getId());
        return saved;
    }

    @Transactional
    public UserEntity login(String login, String password) {
        log.info("Login attempt for login={}", login);
        UserEntity user = userRepo.findByLoginAndPassword(login, password);
        if (user == null) {
            throw new EntityDoesNotExistException("User doesn't exist!");
        }
        user.setLastLoginAt(LocalDateTime.now());
        UserEntity saved = userRepo.save(user);
        log.info("User id={} logged in", saved.getId());
        return saved;
    }

    public UserGetByIdModel getUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityDoesNotExistException("User doesn't exist!");
        }
        UserEntity entity = userRepo.findById(id).get();
        UserGetByIdModel model = UserGetByIdModel.toModel(entity);
        model.setCitizenshipCode(entity.getCountry_id().getCode());
        model.setCitizenshipName(entity.getCountry_id().getName());
        model.setDocName(entity.getDocument_id().getName());
        model.setDocCode(entity.getDocument_id().getCode());
        model.setOfficeId(entity.getOffice_id().getId());
        return model;
    }

    public UserEntity updateUser(UserUpdateInModel userUpdateInModel) {
        log.info("Updating user id={}", userUpdateInModel.getId());
        if (!userRepo.existsById(userUpdateInModel.getId())) {
            throw new EntityDoesNotExistException("User with this id doesn't exist!");
        }
        if (!officeRepo.existsById(userUpdateInModel.getOffice_id())) {
            throw new EntityDoesNotExistException("Office with this office_id doesn't exist!");
        }
        if (!docsRepo.existsById(userUpdateInModel.getDocCode())) {
            throw new EntityDoesNotExistException("Documents with this code doesn't exist!");
        }
        if (!countryRepo.existsById(userUpdateInModel.getCitizenshipCode())) {
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
        userInDB.setHireDate(userUpdateInModel.getHireDate());
        userInDB.setBirthDate(userUpdateInModel.getBirthDate());
        UserEntity saved = userRepo.save(userInDB);
        log.info("User id={} updated", saved.getId());
        return saved;
    }

    @Transactional
    public UserEntity replaceUserProjects(Long userId, IdListModel body) {
        log.info("Replacing projects for user id={}, project count={}", userId, body.getIds().size());
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityDoesNotExistException("User not found"));
        Set<ProjectEntity> projects = new HashSet<>();
        for (Long pid : body.getIds()) {
            ProjectEntity p = projectRepo.findById(pid)
                    .orElseThrow(() -> new EntityDoesNotExistException("Project not found: " + pid));
            projects.add(p);
        }
        user.replaceProjects(projects);
        UserEntity saved = userRepo.save(user);
        log.info("User id={} project links updated", saved.getId());
        return saved;
    }

    public List<UserEntity> getAllUsersList(UserListInModel model) {
        log.debug("Filtering users by officeId={}", model.getOfficeId());
        return userFilterSpecification.findUserFilter(model.getOfficeId(), model.getFirstName(),
                model.getLastName(), model.getMiddleName(), model.getPosition(), model.getDocCode(), model.getCitizenshipCode());
    }
    public UserEntity saveNewUser(UserSaveModel userSaveModel) {
        log.info("Creating user login={}", userSaveModel.getLogin());
        if (!docsRepo.existsById(userSaveModel.getDocCode())) {
            throw new EntityDoesNotExistException("Documents with this code doesn't exist!");
        }
        if (!countryRepo.existsById(userSaveModel.getCitizenshipCode())) {
            throw new EntityDoesNotExistException("Country with this code doesn't exist!");
        }
        if (!officeRepo.existsById(userSaveModel.getOffice_id())) {
            throw new EntityDoesNotExistException("Office with this office_id doesn't exist!");
        }
        UserEntity tempUser = UserSaveModel.fromModel(userSaveModel);
        tempUser.setCountry_id(countryRepo.findByCode(userSaveModel.getCitizenshipCode()));
        tempUser.setDocument_id(docsRepo.findByCode(userSaveModel.getDocCode()));
        tempUser.setOffice_id(officeRepo.findById(userSaveModel.getOffice_id()).get());
        UserEntity saved = userRepo.save(tempUser);
        log.info("User id={} created", saved.getId());
        return saved;
    }
}
