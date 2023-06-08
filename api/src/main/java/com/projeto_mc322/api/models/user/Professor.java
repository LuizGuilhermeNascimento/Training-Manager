package com.projeto_mc322.api.models.user;

public class Professor extends User{
    private final String cref;

    public Professor(String nome, String cpf, String cref, String email, String senha) {
        super(nome, cpf, email, senha);
        this.cref = cref;
    }

    public String getCref() {
        return cref;
    }
}
