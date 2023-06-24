package com.projeto_mc322.api.services;

import com.projeto_mc322.api.dtos.*;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.repositories.AcompanhamentoRepository;
import com.projeto_mc322.api.repositories.AlunoRepository;
import com.projeto_mc322.api.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final AcompanhamentoRepository acompanhamentoRepository;

    public AlunoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository, AcompanhamentoRepository acompanhamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.acompanhamentoRepository = acompanhamentoRepository;
    }

    public Aluno create(CreateAlunoDTO createAlunoDTO) throws HttpException {
        Aluno aluno = createAlunoDTO.create();
        alunoRepository.create(aluno);
        return aluno;
    }

    public boolean delete(UUID id) {
        try {
            Aluno aluno = alunoRepository.find(id);
            if (aluno.getAcompanhamento().isPresent()) {
                Acompanhamento acompanhamento = aluno.getAcompanhamento().get();
                acompanhamento.getProfessor().deleteAcompanhamento(acompanhamento);
                professorRepository.save(acompanhamento.getProfessor());
                acompanhamentoRepository.remove(acompanhamento.getId());
            }
            return alunoRepository.remove(id);
        } catch (Exception ignored) {
            return false;
        }
    }

    public Aluno find(UUID id) throws HttpException {
        return alunoRepository.find(id);
    }

    public List<Aluno> listar() {
        return alunoRepository.list();
    }
}
