package com.projeto_mc322.api.services;

import com.projeto_mc322.api.dtos.CreateAcompanhamentoDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.repositories.ProfessorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Acompanhamento createAcompanhamento(CreateAcompanhamentoDTO createAcompanhamentoDTO) throws HttpException {
        
        throw new HttpException("Professor nao encontrado", HttpStatus.NOT_FOUND);
    }

    public List<Acompanhamento> getAcompanhamentos(UUID professorId) throws HttpException {
        Professor professor = professorRepository.find(professorId);
        return professor.getAcompanhamentos();
    }

    public boolean deleteAcompanhamento(UUID professorId, UUID acompanhamentoId) throws HttpException {
        Professor professor = professorRepository.find(professorId);
        return professor.deleteAcompanhamento(acompanhamentoId);
    }
}
