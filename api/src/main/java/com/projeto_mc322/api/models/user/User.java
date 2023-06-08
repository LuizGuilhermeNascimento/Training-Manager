package com.projeto_mc322.api.models.user;

import java.util.UUID;

public abstract class User {
    private final UUID id = new UUID();
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public User(String nome, String cpf, String email, String senha) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
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

    public void setCpf(String cpf) {
        this.cpf = cpf.trim();
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
}
