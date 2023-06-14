package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;

import com.projeto_mc322.api.models.treino.Treino;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends User{
    private Acompanhamento acompanhamento;

    public Aluno(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    @Override
    public JsonObject writeJson() {
        JsonObject jsonObject = super.writeJson();
        JsonObjectBuilder jsonObjectBuilder =  Json.createObjectBuilder();
        String acompanhamentoString = "";
        if (getAcompanhamento() != null){
            acompanhamentoString = getAcompanhamento().getId().toString();
        }
        jsonObject.keySet().forEach(key -> jsonObjectBuilder.add(key, jsonObject.get(key)));
        jsonObjectBuilder
                .add("acompanhamento", acompanhamentoString)
                .add("secoesAgendadas", jsonArrayBuilderSecoesAgendadas);
        return jsonObjectBuilder.build();
    }

    public Treino proximoTreino(){
        return acompanhamento.exibirProximoTreino();
    }

    public void realizarTreino(){
        if (acompanhamento != null){
            acompanhamento.realizarTreino();
        }
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
