package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Professor;

public class CreateProfessorDTO extends CreateUserDTO {
    private String cref;

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }

    @Override
    public Professor create() {
        return new Professor(
                getNome(),
                getCpf(),
                getCref(),
                getEmail(),
                getSenha()
        );
    }
}
