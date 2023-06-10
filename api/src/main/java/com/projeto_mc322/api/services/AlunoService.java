package com.projeto_mc322.api.services;

import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlunoService {
    public Acompanhamento getAcompanhamento(UUID id) throws HttpException {
        throw new HttpException("Aluno não encontrado", HttpStatus.NOT_FOUND);
    }

    public Treino getProximoTreino(UUID id) throws HttpException{
        throw new HttpException("Usuario nao encontrado", HttpStatus.NOT_FOUND);
    }
}
