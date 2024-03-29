package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Professor extends User {
    private final String cref;
    private List<Acompanhamento> acompanhamentos;

    public Professor(String nome, String cpf, String cref, String email, String senha) {
        super(nome, cpf, email, senha);
        this.cref = cref;
        acompanhamentos = new ArrayList<>();
    }

    public Professor(UUID id, String nome, String cpf, String email, String senha, String cref) {
        super(id, nome, cpf, email, senha);
        this.cref = cref;
        acompanhamentos = new ArrayList<>();
    }


    public Professor(UUID uuid, String nome, String cpf, String email, String senha, String cref, List<Acompanhamento> acompanhamentos) {
        super(uuid, nome, cpf, email, senha);
        this.cref = cref;
        this.acompanhamentos = acompanhamentos;
    }

    @Override
    public JsonObject writeJson() {
        JsonObject jsonObject = super.writeJson();
        JsonArrayBuilder jsonArrayBuilderAcompanhamentos = Json.createArrayBuilder();
        acompanhamentos.forEach(acompanhamento -> jsonArrayBuilderAcompanhamentos.add(acompanhamento.getId().toString()));
        JsonObjectBuilder jsonObjectBuilder =  Json.createObjectBuilder();
        jsonObject.keySet().forEach(key -> jsonObjectBuilder.add(key, jsonObject.get(key)));
        return jsonObjectBuilder
                .add("cref", getCref())
                .add("acompanhamentos", jsonArrayBuilderAcompanhamentos.build())
                .build();
    }

    public Acompanhamento createAcompanhamento(Aluno aluno, List<Treino> treinos, Integer treinosMeta){
        Acompanhamento acompanhamento = new Acompanhamento(this, aluno, treinosMeta);
        acompanhamento.setTreinos(treinos);
        acompanhamentos.add(acompanhamento);
        aluno.setAcompanhamento(Optional.of(acompanhamento));
        return acompanhamento;
    }


    public boolean deleteAcompanhamento(Acompanhamento acompanhamento) {
        if (acompanhamentos.remove(acompanhamento)){
            acompanhamento.getAluno().setAcompanhamento(Optional.empty());
            return true;
        }
        return false;
    }

    public boolean deleteAcompanhamento(UUID acompanhamentoId){
        try{
            Acompanhamento acompanhamento = findAcompanhamentoById(acompanhamentoId);
            return deleteAcompanhamento(acompanhamento);
        }catch(Exception e){
            return false;
        }
    }

    private Acompanhamento findAcompanhamentoById(UUID id) throws Exception{
        for (Acompanhamento acompanhamento : getAcompanhamentos()){
            if (acompanhamento.getId().equals(id)){
                return acompanhamento;
            }
        }
        throw new Exception();
    }

    public String getCref() {
        return cref;
    }

    public List<Acompanhamento> getAcompanhamentos() {
        return this.acompanhamentos;
    }

    public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }
}
