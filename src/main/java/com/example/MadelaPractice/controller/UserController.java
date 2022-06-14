package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

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
            return ResponseEntity.ok().body(userService.getAllUsersList());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getOneUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(userService.getUser(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("User doesn't exist");
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody @Valid UserEntity userEntity){
        try {
            userService.updateUser(userEntity);
            return ResponseEntity.ok().body("Updated user");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Couldn't update user!");
        }
    }
//    @GetMapping("/activation")
//    public ResponseEntity useActivationCode(){
//
//    }
}
