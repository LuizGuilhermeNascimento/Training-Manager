package com.projeto_mc322.api.controllers;

import com.projeto_mc322.api.dtos.CreateAcompanhamentoDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/acompanhamento")
    public ResponseEntity<Object> createAcompanhamento(@RequestBody CreateAcompanhamentoDTO createAcompanhamentoDTO){
        try {
            Acompanhamento acompanhamento = professorService.createAcompanhamento(createAcompanhamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(acompanhamento);
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/acompanhamento/{professorId}")
    public ResponseEntity<Object> getAcompanhamentos(@PathVariable(name = "professorId") UUID professorId){
        try{
            List<Acompanhamento> acompanhamentoList = professorService.getAcompanhamentos(professorId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(acompanhamentoList);
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @DeleteMapping("/acompanhamento/{professorId}/{acompanhamentoId}")
    public ResponseEntity<Object> deleteAcompanhamento(@PathVariable(name = "professorId") UUID professorId,
                                                       @PathVariable(name = "acompanhamentoId") UUID acompanhamentoId){
        if (professorService.deleteAcompanhamento(professorId, acompanhamentoId)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Acompanhamento " + acompanhamentoId + " excluído.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acompanhamento " + acompanhamentoId + " não existe");
    }
}
