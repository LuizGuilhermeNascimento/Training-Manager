package com.projeto_mc322.api.controllers;


import com.projeto_mc322.api.dtos.*;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody CreateProfessorDTO createProfessorDTO){
        try{
            Professor professor = professorService.create(createProfessorDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ProfessorResponseDTO(professor));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id){
        if (professorService.delete(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao deletado");
    }
}
