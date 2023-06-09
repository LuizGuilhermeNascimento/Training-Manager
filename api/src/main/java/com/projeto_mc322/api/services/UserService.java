package com.projeto_mc322.api.services;


import com.projeto_mc322.api.dtos.CreateAlunoDTO;
import com.projeto_mc322.api.dtos.CreateProfessorDTO;
import com.projeto_mc322.api.exceptions.LoginInvalido;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User login(String email, String senha) throws LoginInvalido {
        // TODO: 08/06/2023
        throw new LoginInvalido("Login Inválido");
    }

    public User save(CreateAlunoDTO createAlunoDTO) throws Exception{
        // TODO: 08/06/2023
        return new Aluno(createAlunoDTO.getNome(),
                                createAlunoDTO.getCpf(),
                                createAlunoDTO.getEmail(),
                                createAlunoDTO.getSenha());
    }

    public User save(CreateProfessorDTO createProfessorDTO) throws Exception{
        // TODO: 08/06/2023
        throw  new Exception("Erro");
    }
}
