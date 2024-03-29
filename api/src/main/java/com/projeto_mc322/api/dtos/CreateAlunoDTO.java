package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Aluno;
public class CreateAlunoDTO extends CreateUserDTO {
    @Override
    public Aluno create() {
        return new Aluno(
                getNome(),
                getCpf(),
                getEmail(),
                getSenha());
    }
}
