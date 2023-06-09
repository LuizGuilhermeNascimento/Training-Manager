package com.projeto_mc322.api.models.treino;

public class Treino {
    private String tipo;
    private String nome;
    private String descricao;

    public Treino(String tipo, String nome, String descricao) {
        setTipo(tipo);
        setNome(nome);
        setDescricao(descricao);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
