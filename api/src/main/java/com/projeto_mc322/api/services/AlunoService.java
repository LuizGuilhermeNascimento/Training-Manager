package com.projeto_mc322.api.services;

import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.repositories.AlunoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

//    public Acompanhamento getAcompanhamento(UUID id) throws HttpException {
//        Aluno aluno = alunoRepository.find(id);
//        return aluno.getAcompanhamento();
//    }

//    public Treino getProximoTreino(UUID id) throws HttpException{
//        Aluno aluno = alunoRepository.find(id);
//        return proximoTreino(aluno);
//    }
//
//    private Treino proximoTreino(Aluno aluno){
//        List<Treino> treinos = aluno.getAcompanhamento().getTreinos();
//        Integer num = treinos.size();
//        return treinos.get(aluno.getAcompanhamento().getTreinosRealizados() % num);
//    }
}