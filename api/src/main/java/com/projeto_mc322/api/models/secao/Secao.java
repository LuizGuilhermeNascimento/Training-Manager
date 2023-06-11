package com.projeto_mc322.api.models.secao;

import com.datapersistence.JsonSerializable;
import com.projeto_mc322.api.models.sala.Sala;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Secao implements JsonSerializable {
    private final UUID id = UUID.randomUUID();
    private Professor professor;
    private List<Aluno> alunos = new ArrayList<>();
    private String titulo;
    private String descricao;
    private Integer capacidade;
    private Sala sala;
    private Date data;
    private Integer duracao;
    private static final Integer DURACAO_MAXIMA = 120;
    private static final Integer DURACAO_MINIMA = 30;

    public Secao(Professor professor, String titulo, String descricao, Integer capacidade, Sala sala, Date data, Integer duracao) {
        this.professor = professor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.sala = sala;
        this.data = data;
        this.duracao = duracao;
    }

    @Override
    public JsonObject writeJson() {
        JsonArrayBuilder jsonArrayBuilderAlunos = Json.createArrayBuilder();
        alunos.forEach(aluno -> jsonArrayBuilderAlunos.add(aluno.getId().toString()));
        return Json.createObjectBuilder()
                .add("id", getId().toString())
                .add("professorId", getProfessor().getId().toString())
                .add("titulo", getTitulo())
                .add("descricao", getDescricao())
                .add("capacidade", getCapacidade())
                .add("duracao", getDuracao())
                .add("data", getData().toString())
                .add("alunos", jsonArrayBuilderAlunos.build())
                .build();
    }

    @Override
    public UUID getId() {
        return id;
    }


    public void apagarSecao(){
        alunos.forEach(aluno -> {
            aluno.getSecoesAgendadas().remove(this);
        });
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Secao secao)) return false;
        return getId().equals(secao.getId());
    }
}
