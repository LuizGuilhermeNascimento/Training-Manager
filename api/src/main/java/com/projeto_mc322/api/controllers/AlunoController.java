package com.projeto_mc322.api.controllers;


import com.projeto_mc322.api.dtos.*;
import com.projeto_mc322.api.exceptions.*;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody CreateAlunoDTO createAlunoDTO){
        try{
            Aluno aluno = alunoService.create(createAlunoDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AlunoResponseDTO(aluno));
        }catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id){
        if (alunoService.delete(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao deletado");
    }
}
