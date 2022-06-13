package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.UserAlreadyExistsException;
import com.example.MadelaPractice.exception.UserDoesNotExistException;
import com.example.MadelaPractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registation(UserEntity userEntity) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(userEntity.getLogin()) != null){
            throw new UserAlreadyExistsException("User with this login already exists!");
        }
        return userRepo.save(userEntity);
    }

    public UserEntity login(String login, String password) throws UserDoesNotExistException {
        UserEntity user = userRepo.findByLoginAndPassword(login, password);
        if (user == null){
            throw new UserDoesNotExistException("User doesn't exist!");
        }
        return user;
    }

//    public UserEntity getUser(Long id) throws UserDoesNotExistException {
//        UserEntity user = userRepo.findById(id);
//        if (user == null){
//            throw new UserDoesNotExistException("User doesn't exist!");
//        }
//        return user;
//    }


}
