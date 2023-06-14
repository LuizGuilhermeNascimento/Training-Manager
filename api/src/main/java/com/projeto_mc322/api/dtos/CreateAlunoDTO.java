package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.User;

public class CreateAlunoDTO extends CreateUserDTO {
    @Override
    public User create() {
        return new Aluno(
                getNome(),
                getCpf(),
                getEmail(),
                getSenha());
    }
}
