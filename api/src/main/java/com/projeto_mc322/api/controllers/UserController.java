package com.projeto_mc322.api.controllers;


import com.projeto_mc322.api.dtos.CreateAlunoDTO;
import com.projeto_mc322.api.dtos.CreateProfessorDTO;
import com.projeto_mc322.api.dtos.LoginDTO;
import com.projeto_mc322.api.exceptions.CPFIndisponivel;
import com.projeto_mc322.api.exceptions.CREFIndisponivel;
import com.projeto_mc322.api.exceptions.EmailIndisponivel;
import com.projeto_mc322.api.exceptions.LoginInvalido;
import com.projeto_mc322.api.models.user.User;
import com.projeto_mc322.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            User user = userService.login(loginDTO.getEmail(), loginDTO.getSenha());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (LoginInvalido e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/aluno/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody CreateAlunoDTO createAlunoDTO){
        try{
            User aluno = userService.save(createAlunoDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(aluno);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @PostMapping("/professor/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody CreateProfessorDTO createProfessorDTO){
        try{
            User professor = userService.save(createProfessorDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(professor);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id){
        if (userService.delete(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao deletado");
    }
}
