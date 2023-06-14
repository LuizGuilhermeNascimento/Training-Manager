package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.treino.Treino;

import java.util.List;
import java.util.UUID;

public class CreateAcompanhamentoDTO {
    private UUID professorId;
    private UUID alunoId;
    private String nomeAluno;
    private List<Treino> treinos;
    private Integer treinosMeta;

    public String getNomeAluno() {
        return this.nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }


    public UUID getProfessorId() {
        return professorId;
    }

    public void setProfessorId(UUID professorId) {
        this.professorId = professorId;
    }

    public UUID getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public Integer getTreinosMeta() {
        return treinosMeta;
    }

    public void setTreinosMeta(Integer treinosMeta) {
        this.treinosMeta = treinosMeta;
    }
}
