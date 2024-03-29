package com.projeto_mc322.api.models.acompanhamento;

import com.datapersistence.JsonSerializable;
import com.projeto_mc322.api.models.treino.Treino;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Acompanhamento implements JsonSerializable {
    private final UUID id;
    private Professor professor;
    private Aluno aluno;
    private List<Treino> treinos;
    private Integer treinosRealizados = 0;
    private Integer treinosMeta; // quantas vezes o aluno deve realizar uma seção completa de treinos

    public Acompanhamento(Professor professor, Aluno aluno, Integer treinosMeta) {
        setProfessor(professor);
        setAluno(aluno);
        setTreinosMeta(treinosMeta);
        treinos = new ArrayList<>();
        id = UUID.randomUUID();
    }

    public Acompanhamento(UUID id, Integer treinosRealizados, Integer treinosMeta) {
        setTreinosRealizados(treinosRealizados);
        setTreinosMeta(treinosMeta);
        treinos = new ArrayList<>();
        this.id = id;
    }

    @Override
    public JsonObject writeJson() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        treinos.forEach(treino -> jsonArrayBuilder.add(treino.writeJson()));
        return Json.createObjectBuilder()
                .add("id", getId().toString())
                .add("alunoId", getAluno().getId().toString())
                .add("professorId", getProfessor().getId().toString())
                .add("treinos", jsonArrayBuilder.build())
                .add("treinosMeta", getTreinosMeta())
                .add("treinosRealizados", getTreinosRealizados())
                .build();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public boolean acompanhamentoFinalizado() {
        return treinosRealizados >= treinosMeta * treinos.size();
    }

    public Treino proximoTreino() {
        Integer num = treinos.size();
        return treinos.get(treinosRealizados % num);
    }

    public List<Treino> proximosTreinos() {
        List<Treino> proxs = new ArrayList<>();
        Integer num = treinos.size();
        for (int i = 0; i < num; i++) {
            proxs.add(treinos.get((treinosRealizados + i) % num));
        }
        return proxs;
    }

    public void realizarTreino() {
        treinosRealizados++;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public Integer getTreinosRealizados() {
        return treinosRealizados;
    }

    public void setTreinosRealizados(Integer treinosRealizados) {
        this.treinosRealizados = treinosRealizados;
    }

    public Integer getTreinosMeta() {
        return treinosMeta;
    }

    public void setTreinosMeta(Integer treinosMeta) {
        this.treinosMeta = treinosMeta;
    }
}
