package com.projeto_mc322.api.controllers;

import com.projeto_mc322.api.dtos.AcompanhamentoResponseDTO;
import com.projeto_mc322.api.dtos.CreateAcompanhamentoDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
import com.projeto_mc322.api.services.AcompanhamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/acompanhamento")
public class AcompanhamentoController {
    private final AcompanhamentoService acompanhamentoService;

    public AcompanhamentoController(AcompanhamentoService acompanhamentoService) {
        this.acompanhamentoService = acompanhamentoService;
    }

    @GetMapping("/proximo-treino/{alunoId}")
    public ResponseEntity<Object> getProximoTreino(@PathVariable(name = "alunoId") UUID alunoId){
        try{
            Treino treino = acompanhamentoService.getProximoTreinp(alunoId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(treino);
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @PutMapping("/realizar-treino/{alunoId}")
    public ResponseEntity<Object> realizarTreino(@PathVariable(name = "alunoId") UUID alunoId){
        try {
            if (acompanhamentoService.realizarTreino(alunoId)){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Treino realizado.");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("O aluno não possui acompanhamento");
        } catch (HttpException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }


    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<Object> getAcompanhamentoAluno(@PathVariable(name = "alunoId") UUID alunoId) {
        try {
            Optional<Acompanhamento> optionalAcompanhamento = acompanhamentoService.getAlunoAcompanhamento(alunoId);
            if (optionalAcompanhamento.isPresent()){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AcompanhamentoResponseDTO(optionalAcompanhamento.get()));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } catch (HttpException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<Object> getAcompanhamentosProfessor(@PathVariable(name = "professorId") UUID professorId) {
        try {
            List<Acompanhamento> acompanhamentos = acompanhamentoService.getAcompanhamentosProfessor(professorId);
            List<AcompanhamentoResponseDTO> acompanhamentoResponseDTOS = new ArrayList<>();
            acompanhamentos.forEach(acompanhamento -> acompanhamentoResponseDTOS.add(new AcompanhamentoResponseDTO(acompanhamento)));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(acompanhamentoResponseDTOS);
        } catch (HttpException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CreateAcompanhamentoDTO createAcompanhamentoDTO){
        try{
            Acompanhamento acompanhamento = acompanhamentoService.create(createAcompanhamentoDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AcompanhamentoResponseDTO(acompanhamento));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @DeleteMapping("/{acompanhamentoId}")
    public ResponseEntity<Object> delete(@PathVariable(name = "acompanhamentoId") UUID acompanhamentoId){
        if (acompanhamentoService.delete(acompanhamentoId)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não deletado");
    }
}
