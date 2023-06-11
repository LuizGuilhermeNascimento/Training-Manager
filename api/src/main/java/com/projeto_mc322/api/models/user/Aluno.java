package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.secao.Secao;
import com.projeto_mc322.api.models.treino.Treino;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends User{
    private Acompanhamento acompanhamento;
    private List<Secao> secoesAgendadas = new ArrayList<>();
    public Aluno(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    @Override
    public JsonObject writeJson() {
        JsonObject jsonObject = super.writeJson();
        JsonArrayBuilder jsonArrayBuilderSecoesAgendadas = Json.createArrayBuilder();
        secoesAgendadas.forEach(secao -> jsonArrayBuilderSecoesAgendadas.add(secao.getId().toString()));
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

    public List<Secao> getSecoesAgendadas() {
        return secoesAgendadas;
    }

    public void setSecoesAgendadas(List<Secao> secoesAgendadas) {
        this.secoesAgendadas = secoesAgendadas;
    }


}
