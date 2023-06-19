package com.projeto_mc322.api.models.user;

import com.datapersistence.JsonSerializable;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Objects;
import java.util.UUID;

public abstract class User implements JsonSerializable {
    private final UUID id;
    private String nome;
    private final String cpf;
    private String email;
    private String senha;

    public User(String nome, String cpf, String email, String senha) {
        this.id = UUID.randomUUID();
        setNome(nome);
        this.cpf = cpf;
        setEmail(email);
        setSenha(senha);
    }

    public User(UUID uuid, String nome, String cpf, String email, String senha) {
        this.id = uuid;
        setNome(nome);
        this.cpf = cpf;
        setEmail(email);
        setSenha(senha);
    }

    @Override
    public JsonObject writeJson() {
        return Json.createObjectBuilder()
                .add("nome", getNome())
                .add("cpf", getCpf())
                .add("id", getId().toString())
                .add("email", getEmail())
                .add("senha", getSenha())
                .build();
    }

    @Override
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
