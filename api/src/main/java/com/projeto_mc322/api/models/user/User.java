package com.projeto_mc322.api.models.user;

import java.util.Objects;
import java.util.UUID;

public abstract class User {
    private final UUID id = UUID.randomUUID();
    private String nome;
    private final String cpf;
    private String email;
    private String senha;

    public User(String nome, String cpf, String email, String senha) {
        setNome(nome);
        this.cpf = cpf;
        setEmail(email);
        setSenha(senha);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome.trim();
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.getId()) || Objects.equals(getCpf(), user.getCpf()) || Objects.equals(getEmail(), user.getEmail());
    }
}
