package com.projeto_mc322.api.services;


import com.projeto_mc322.api.dtos.CreateAlunoDTO;
import com.projeto_mc322.api.dtos.CreateProfessorDTO;
import com.projeto_mc322.api.dtos.CreateUserDTO;
import com.projeto_mc322.api.exceptions.*;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.User;
import com.projeto_mc322.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String senha) throws HttpException {
        User user = userRepository.findByEmail(email);
        if (user.getSenha().equals(senha)){
            return user;
        }
        throw new HttpException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
    }

    public User create(CreateUserDTO createUserDTO) throws HttpException {
        User user = createUserDTO.create();
        userRepository.create(user);
        return user;
    }

    public boolean delete(UUID id) {
        return true;
    }
}
