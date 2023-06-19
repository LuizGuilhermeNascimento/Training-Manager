package com.projeto_mc322.api.controllers;


import com.projeto_mc322.api.dtos.LoginDTO;
import com.projeto_mc322.api.dtos.UserResponseDTO;
import com.projeto_mc322.api.exceptions.*;
import com.projeto_mc322.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        try{
            UserResponseDTO user = userService.login(loginDTO.getEmail(), loginDTO.getSenha());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (HttpException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
