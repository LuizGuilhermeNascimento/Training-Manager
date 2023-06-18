package com.projeto_mc322.api.services;

import com.projeto_mc322.api.dtos.CreateProfessorDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.repositories.AcompanhamentoRepository;
import com.projeto_mc322.api.repositories.AlunoRepository;
import com.projeto_mc322.api.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final AcompanhamentoRepository acompanhamentoRepository;

    public ProfessorService(AlunoRepository alunoRepository, ProfessorRepository professorRepository, AcompanhamentoRepository acompanhamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.acompanhamentoRepository = acompanhamentoRepository;
    }

    public Professor create(CreateProfessorDTO createProfessorDTO) throws HttpException {
        Professor professor = createProfessorDTO.create();
        professorRepository.create(professor);
        return professor;
    }

    public boolean delete(UUID id) {
        try{
            Professor professor = professorRepository.find(id);
            professor.getAcompanhamentos().forEach(acompanhamento -> {
                acompanhamento.getAluno().setAcompanhamento(Optional.empty());
                alunoRepository.save(acompanhamento.getAluno());
                acompanhamentoRepository.remove(acompanhamento.getId());
            });
            return professorRepository.remove(id);
        }catch (Exception ignored){
            return false;
        }
    }
}
