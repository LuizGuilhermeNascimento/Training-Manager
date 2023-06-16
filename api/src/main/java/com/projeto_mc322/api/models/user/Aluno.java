package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;

import com.projeto_mc322.api.models.treino.Treino;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Optional;
import java.util.UUID;

public class Aluno extends User{
    private Optional<Acompanhamento> acompanhamento = Optional.empty();

    public Aluno(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    public Aluno(UUID uuid, String nome, String cpf, String email, String senha) {
        super(uuid, nome, cpf, email, senha);
    }

    @Override
    public JsonObject writeJson() {
        JsonObject jsonObject = super.writeJson();
        JsonObjectBuilder jsonObjectBuilder =  Json.createObjectBuilder();
        String acompanhamentoString = "";
        if (getAcompanhamento().isPresent()){
            acompanhamentoString = getAcompanhamento().get().getId().toString();
        }
        jsonObject.keySet().forEach(key -> jsonObjectBuilder.add(key, jsonObject.get(key)));
        return jsonObjectBuilder
                .add("acompanhamento", acompanhamentoString)
                .build();
    }

    public Treino proximoTreino() throws Exception{
        if (acompanhamento.isPresent()){
            return acompanhamento.get().exibirProximoTreino();
        }
        throw new Exception();
    }

    public void realizarTreino(){
        acompanhamento.ifPresent(Acompanhamento::realizarTreino);
    }

    public Optional<Acompanhamento> getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Optional<Acompanhamento> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
