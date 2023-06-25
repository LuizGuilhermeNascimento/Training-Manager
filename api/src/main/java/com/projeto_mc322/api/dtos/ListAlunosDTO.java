package com.projeto_mc322.api.dtos;

import java.util.List;

public class ListAlunosDTO {
    private List<AlunoResponseDTO> list;

    public ListAlunosDTO(List<AlunoResponseDTO> list) {
        this.list = list;
    }

    public List<AlunoResponseDTO> getList() {
        return this.list;
    }

    public void setList(List<AlunoResponseDTO> list) {
        this.list = list;
    }
}
