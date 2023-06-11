package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.sala.Sala;
import com.projeto_mc322.api.models.secao.Secao;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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

    @Override
    public JsonObject writeJson() {
        JsonObject jsonObject = super.writeJson();
        JsonArrayBuilder jsonArrayBuilderSecoes = Json.createArrayBuilder();
        JsonArrayBuilder jsonArrayBuilderAcompanhamentos = Json.createArrayBuilder();
        secoes.forEach(secao -> jsonArrayBuilderSecoes.add(secao.getId().toString()));
        acompanhamentos.forEach(acompanhamento -> jsonArrayBuilderAcompanhamentos.add(acompanhamento.getId().toString()));
        JsonObjectBuilder jsonObjectBuilder =  Json.createObjectBuilder();
        jsonObjectBuilder
                .add("acompanhamentos", jsonArrayBuilderAcompanhamentos.build())
                .add("secoes", jsonArrayBuilderSecoes.build())
                .build();
        jsonObject.keySet().forEach(key -> jsonObjectBuilder.add(key, jsonObject.get(key)));
        return jsonObjectBuilder.build();
    }

    public Secao criarSecao(String titulo, String descricao, Integer capacidade, Sala sala, Date data, Integer duracao){
        Secao secao = new Secao(this, titulo, descricao, capacidade, sala, data, duracao);
        secoes.add(secao);
        return secao;
    }

    public boolean excluirSecao(Secao secao){
        if (secoes.remove(secao)){
            secao.apagarSecao();
            return true;
        }
        return false;
    }

    public String getCref() {
        return cref;
    }
}
