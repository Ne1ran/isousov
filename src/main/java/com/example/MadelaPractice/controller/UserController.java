package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.ApplicationException;
import com.example.MadelaPractice.exception.ErrorCodes;
import com.example.MadelaPractice.model.UserGetByIdModel;
import com.example.MadelaPractice.model.UserListInModel;
import com.example.MadelaPractice.model.UserListOut;
import com.example.MadelaPractice.model.UserRegistationModel;
import com.example.MadelaPractice.model.UserSaveModel;
import com.example.MadelaPractice.model.UserUpdateInModel;
import com.example.MadelaPractice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Пользователи и прочее", description = "Пользователи, регистрация, вход")
public class UserController {

    public static Integer codeGenerated = 0;
    public static UserEntity userInQueryToGetRegistered = new UserEntity();

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @Operation(summary = "Проверка доступности API")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(ApiResponse.ok("Test is working"));
    }

    @PostMapping("/registration")
    @Operation(summary = "Регистрация (шаг 1, выдаётся код)")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody @Valid UserRegistationModel userRegistationModel) {
        userInQueryToGetRegistered = UserRegistationModel.fromModel(userRegistationModel);
        codeGenerated = generateCode();
        return ResponseEntity.ok(ApiResponse.ok("Registration ok, your code is: " + codeGenerated));
    }

    @PostMapping("/login")
    @Operation(summary = "Вход по логину и паролю")
    public ResponseEntity<ApiResponse<String>> login(@RequestParam String login, @RequestParam String password) {
        userService.login(login, password);
        return ResponseEntity.ok(ApiResponse.ok("Result: success"));
    }

    @GetMapping("/users")
    @Operation(summary = "Список всех пользователей")
    public ResponseEntity<ApiResponse<List<UserListOut>>> getAllUsersList() {
        List<UserListOut> list = userService.getAllUsers().stream()
                .map(UserListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Удаление пользователя по id")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(ApiResponse.ok("Result: success"));
    }

    @PostMapping("/user/list")
    @Operation(summary = "Фильтрованный список пользователей")
    public ResponseEntity<ApiResponse<List<UserListOut>>> getAllUsers(@RequestBody @Valid UserListInModel model) {
        List<UserListOut> list = userService.getAllUsersList(model).stream()
                .map(UserListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Пользователь по id")
    public ResponseEntity<ApiResponse<UserGetByIdModel>> getOneUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(userService.getUser(id)));
    }

    @PutMapping("/user/update")
    @Operation(summary = "Обновление пользователя")
    public ResponseEntity<ApiResponse<String>> updateUser(@RequestBody @Valid UserUpdateInModel userUpdateInModel) {
        userService.updateUser(userUpdateInModel);
        return ResponseEntity.ok(ApiResponse.ok("Result: success"));
    }

    @PostMapping("/user/save")
    @Operation(summary = "Создание пользователя")
    public ResponseEntity<ApiResponse<String>> saveUser(@RequestBody @Valid UserSaveModel userSaveModel) {
        userService.saveNewUser(userSaveModel);
        return ResponseEntity.ok(ApiResponse.ok("Result: success"));
    }

    @GetMapping("/activation")
    @Operation(summary = "Активация по коду из registration")
    public ResponseEntity<ApiResponse<String>> useActivationCode(@RequestParam("code") String code) {
        try {
            int parsed = Integer.parseInt(code);
            if (codeGenerated == parsed) {
                userService.registation(userInQueryToGetRegistered);
                codeGenerated = 0;
                userInQueryToGetRegistered = new UserEntity();
                return ResponseEntity.ok(ApiResponse.ok("Your code was activated"));
            }
            throw new ApplicationException(ErrorCodes.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Code is wrong!");
        } catch (NumberFormatException e) {
            throw new ApplicationException(ErrorCodes.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Invalid activation code");
        }
    }

    private Integer generateCode() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code += Integer.toString(random.nextInt(9));
        }
        return Integer.parseInt(code);
    }
}
