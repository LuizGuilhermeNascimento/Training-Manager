package com.projeto_mc322.api.services;


import com.projeto_mc322.api.dtos.*;
import com.projeto_mc322.api.exceptions.*;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.models.user.User;
import com.projeto_mc322.api.repositories.AcompanhamentoRepository;
import com.projeto_mc322.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AcompanhamentoRepository acompanhamentoRepository;

    public UserService(UserRepository userRepository, AcompanhamentoRepository acompanhamentoRepository) {
        this.userRepository = userRepository;
        this.acompanhamentoRepository = acompanhamentoRepository;
    }

    public UserResponseDTO login(String email, String senha) throws HttpException {
        try{
            User user = userRepository.findByEmail(email);
            if (user.getSenha().equals(senha)){
                if (user instanceof Professor){
                    return new ProfessorResponseDTO((Professor) user);
                }
                return new AlunoResponseDTO((Aluno) user);
            }
        }catch (HttpException e){
            throw new HttpException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
        }
        throw new HttpException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
    }
}
