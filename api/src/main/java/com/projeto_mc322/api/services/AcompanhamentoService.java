package com.projeto_mc322.api.services;

import com.projeto_mc322.api.dtos.CreateAcompanhamentoDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.repositories.AcompanhamentoRepository;
import com.projeto_mc322.api.repositories.AlunoRepository;
import com.projeto_mc322.api.repositories.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcompanhamentoService {
    private final AcompanhamentoRepository acompanhamentoRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public AcompanhamentoService(AcompanhamentoRepository acompanhamentoRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.acompanhamentoRepository = acompanhamentoRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public Optional<Acompanhamento> getAlunoAcompanhamento(UUID alunoId) throws HttpException {
        Aluno aluno = alunoRepository.find(alunoId);
        return aluno.getAcompanhamento();
    }

    public List<Acompanhamento> getAcompanhamentosProfessor(UUID professorId) throws HttpException {
        Professor professor = professorRepository.find(professorId);
        return professor.getAcompanhamentos();
    }

    public Acompanhamento getAcompanhamento(UUID acompanhamentoId) throws HttpException {
        return acompanhamentoRepository.find(acompanhamentoId);
    }

    public Acompanhamento create(CreateAcompanhamentoDTO createAcompanhamentoDTO) throws HttpException {
        Professor professor = professorRepository.find(createAcompanhamentoDTO.getProfessorId());
        Aluno aluno = alunoRepository.find(createAcompanhamentoDTO.getAlunoId());
        if (aluno.getAcompanhamento().isPresent()) {
            if (aluno.getAcompanhamento().get().acompanhamentoFinalizado()) {
                delete(aluno.getAcompanhamento().get());
            } else {
                throw new HttpException("O aluno ainda não atingiu a meta", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        Acompanhamento acompanhamento = professor.createAcompanhamento(
                aluno,
                createAcompanhamentoDTO.getTreinos(),
                createAcompanhamentoDTO.getTreinosMeta()
        );
        if (acompanhamentoRepository.save(acompanhamento)) {
            professorRepository.save(professor);
            alunoRepository.save(aluno);
        }
        return acompanhamento;
    }

    public boolean delete(UUID acompanhamentoId) {
        try {
            Acompanhamento acompanhamento = acompanhamentoRepository.find(acompanhamentoId);
            return delete(acompanhamento);
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean delete(Acompanhamento acompanhamento) {
        if (acompanhamentoRepository.remove(acompanhamento.getId())) {
            Professor professor = acompanhamento.getProfessor();
            Aluno aluno = acompanhamento.getAluno();
            acompanhamento.getProfessor().deleteAcompanhamento(acompanhamento);
            professorRepository.save(professor);
            alunoRepository.save(aluno);
            return true;
        }
        return false;
    }

    public Treino getProximoTreino(UUID alunoId) throws HttpException {
        Aluno aluno = alunoRepository.find(alunoId);
        if (aluno.getAcompanhamento().isPresent()) {
            return aluno.getAcompanhamento().get().exibirProximoTreino();
        }
        throw new HttpException("O aluno não possui acompanhamento", HttpStatus.NOT_FOUND);
    }

    public boolean realizarTreino(UUID alunoId) throws HttpException {
        Aluno aluno = alunoRepository.find(alunoId);
        if (aluno.getAcompanhamento().isPresent()) {
            aluno.getAcompanhamento().get().realizarTreino();
            acompanhamentoRepository.save(aluno.getAcompanhamento().get());
            return true;
        }
        return false;
    }
}
