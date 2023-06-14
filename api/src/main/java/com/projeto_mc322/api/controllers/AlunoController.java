package com.projeto_mc322.api.controllers;

import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
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

    @GetMapping("/acompanhamento/{alunoId}")
    public ResponseEntity<Object> getAcompanhamento(@PathVariable(name = "alunoId") UUID alunoId){
        try {
            Acompanhamento acompanhamento = alunoService.getAcompanhamento(alunoId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(acompanhamento);
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/proximo-treino/{alunoId}")
    public ResponseEntity<Object> getProximoTreino(@PathVariable(name = "alunoId") UUID alunoId){
        try {
            Treino treino = alunoService.getProximoTreino(alunoId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(treino);
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}