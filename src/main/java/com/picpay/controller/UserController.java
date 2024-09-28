package com.picpay.controller;

import com.picpay.domain.user.User;
import com.picpay.domain.user.UserDTO;
import com.picpay.service.UserService;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok(userService.findAll());
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Validated UserDTO user){
        UserDTO userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }
}
