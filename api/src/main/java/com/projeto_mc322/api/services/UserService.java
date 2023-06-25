package com.projeto_mc322.api.services;

import com.projeto_mc322.api.exceptions.*;
import com.projeto_mc322.api.models.user.User;
import com.projeto_mc322.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String senha) throws HttpException {
        try{
            User user = userRepository.findByEmail(email);
            if (user.getSenha().equals(senha)){
                return user;
            }
        }catch (HttpException e){
            throw new HttpException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
        }
        throw new HttpException("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
    }
}
