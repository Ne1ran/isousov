package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity test(){
        try {
            return ResponseEntity.ok().body("Test is working");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@RequestBody UserEntity userEntity){
        try {
            userService.registation(userEntity);
            return ResponseEntity.ok().body("Registration ok");
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Unknown Error");
        }
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String login, @RequestParam String password){
        try {
            UserEntity user = userService.login(login, password);
            return ResponseEntity.ok().body(user.getFirstName());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/list")
    public ResponseEntity getAllUsers(){
        try {
            return ResponseEntity.ok().body(userRepo.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getOneUserById(@PathVariable Long id){
        try {
//            UserEntity user = userService.getUser(id);
            return ResponseEntity.ok().body(userRepo.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User doesn't exist");
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody UserEntity userEntity){
        try {
            //update
        } catch (Exception e){

        }
        return ResponseEntity.ok().body("123");
    }
//    @GetMapping("/activation")
//    public ResponseEntity useActivationCode(){
//
//    }
}
