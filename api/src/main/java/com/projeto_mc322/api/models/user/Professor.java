package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.sala.Sala;
import com.projeto_mc322.api.models.secao.Secao;

import java.util.Date;
import java.util.List;

public class Professor extends User{
    private final String cref;
    private List<Acompanhamento> acompanhamentos;
    private List<Secao> secoes;

    public Professor(String nome, String cpf, String cref, String email, String senha) {
        super(nome, cpf, email, senha);
        this.cref = cref;
    }

    public Secao criarSecao(String titulo, String descricao, Integer capacidade, Sala sala, Date data, Integer duracao){
        Secao secao = new Secao(this, titulo, descricao, capacidade, sala, data, duracao);
        secoes.add(secao);
        return secao;
    }

    public boolean excluirSecao(Secao secao){
        if (secoes.remove(secao)){

        }
    }

    public String getCref() {
        return cref;
    }
}
