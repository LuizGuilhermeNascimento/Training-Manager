package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;

import java.util.List;
import java.util.UUID;

public class AcompanhamentoResponseDTO {
    private UUID id;
    private UUID alunoId;
    private UUID professorId;
    private List<Treino> treinos;
    private Integer treinosRealizados;
    private Integer treinosMeta;

    public AcompanhamentoResponseDTO(Acompanhamento acompanhamento) {
        this.id = acompanhamento.getId();
        this.alunoId = acompanhamento.getAluno().getId();
        this.professorId = acompanhamento.getProfessor().getId();
        this.treinos = acompanhamento.getTreinos();
        this.treinosRealizados = acompanhamento.getTreinosRealizados();
        this.treinosMeta = acompanhamento.getTreinosMeta();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public UUID getProfessorId() {
        return professorId;
    }

    public void setProfessorId(UUID professorId) {
        this.professorId = professorId;
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
