package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.UserSaveModel;
import com.example.MadelaPractice.model.UserUpdateInModel;
import com.example.MadelaPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class UserController {
    public static Integer codeGenerated = 0;
    public static UserEntity userInQueryToGetRegistarted = new UserEntity();

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
    public ResponseEntity registerUser(@RequestBody UserSaveModel userSaveModel){
        try {
            userInQueryToGetRegistarted = UserSaveModel.fromModel(userSaveModel);
            codeGenerated = generateCode();
            return ResponseEntity.ok().body("Registration ok, your code is: " + codeGenerated);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String login, @RequestParam String password){
        try {
            UserEntity user = userService.login(login, password);
            return ResponseEntity.ok().body("Result: success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/user/list")
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
    public ResponseEntity updateUser(@RequestParam Long officeId, @RequestBody @Valid UserUpdateInModel userUpdateInModel){
        try {
            userService.updateUser(officeId, userUpdateInModel);
            return ResponseEntity.ok().body("Result: success");
        } catch (EntityDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Couldn't update user!");
        }
    }

    @GetMapping("/activation")
    public ResponseEntity useActivationCode(@RequestParam("code") String code){
        try {
            if (codeGenerated == Integer.parseInt(code)){
                userService.registation(userInQueryToGetRegistarted);
                codeGenerated = 0;
                userInQueryToGetRegistarted = new UserEntity();
                return ResponseEntity.ok().body("Your code was activated");
            } else return ResponseEntity.badRequest().body("Code is wrong!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error using code");
        }
    }

    @PostMapping("/user/save")
    public ResponseEntity saveUser(@RequestBody @Valid UserSaveModel userSaveModel){
        try {
            userService.saveNewUser(userSaveModel);
            return ResponseEntity.ok().body("Result: success");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Couldn't save new user");
        }
    }

    public Integer generateCode(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            code += Integer.toString(random.nextInt(9));
        }
        return Integer.parseInt(code);
    }
}
