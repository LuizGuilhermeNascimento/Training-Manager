package com.projeto_mc322.api.services;

import com.projeto_mc322.api.dtos.CreateAcompanhamentoDTO;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.repositories.AcompanhamentoRepository;
import com.projeto_mc322.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcompanhamentoService {
    private final UserRepository userRepository;
    private final AcompanhamentoRepository acompanhamentoRepository;

    public AcompanhamentoService(UserRepository userRepository, AcompanhamentoRepository acompanhamentoRepository) {
        this.userRepository = userRepository;
        this.acompanhamentoRepository = acompanhamentoRepository;
    }

    public Optional<Acompanhamento> getAlunoAcompanhamento(UUID alunoId) throws HttpException {
        Aluno aluno = (Aluno) userRepository.find(alunoId);
        return aluno.getAcompanhamento();
    }

    public List<Acompanhamento> getAcompanhamentosProfessor(UUID professorId) throws HttpException {
        Professor professor = (Professor) userRepository.find(professorId);
        return professor.getAcompanhamentos();
    }

    public Acompanhamento getAcompanhamento(UUID acompanhamentoId) throws HttpException{
        return acompanhamentoRepository.find(acompanhamentoId);
    }

    public Acompanhamento create(CreateAcompanhamentoDTO createAcompanhamentoDTO) throws HttpException {
        Professor professor = (Professor) userRepository.find(createAcompanhamentoDTO.getProfessorId());
        Aluno aluno = (Aluno) userRepository.find(createAcompanhamentoDTO.getAlunoId());
        if (aluno.getAcompanhamento().isPresent()){
            delete(aluno.getAcompanhamento().get());
            professor.deleteAcompanhamento(aluno.getAcompanhamento().get());
        }
        Acompanhamento acompanhamento = professor.createAcompanhamento(
                aluno,
                createAcompanhamentoDTO.getTreinos(),
                createAcompanhamentoDTO.getTreinosMeta()
        );
        if (acompanhamentoRepository.save(acompanhamento)){
            userRepository.save(professor);
            userRepository.save(aluno);
        }
        return acompanhamento;
    }

    public boolean delete(Acompanhamento acompanhamento){
        return acompanhamentoRepository.remove(acompanhamento.getId());
    }
}
