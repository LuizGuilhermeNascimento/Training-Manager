package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.treino.Treino;

import java.util.List;

public class ListTreinoDTO {
    private List<Treino> list;

    public ListTreinoDTO(List<Treino> list) {
        this.list = list;
    }

    public List<Treino> getList() {
        return list;
    }

    public void setList(List<Treino> list) {
        this.list = list;
    }
}
