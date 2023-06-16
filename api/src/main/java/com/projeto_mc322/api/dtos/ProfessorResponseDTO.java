package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorResponseDTO extends UserResponseDTO {
    private List<AcompanhamentoResponseDTO> acompanhamentos = new ArrayList<>();
    private String cref;

    public ProfessorResponseDTO(Professor professor) {
        super(professor.getId(), professor.getNome(), professor.getCpf(), professor.getEmail());
        professor.getAcompanhamentos().forEach(
                acompanhamento -> acompanhamentos.add(new AcompanhamentoResponseDTO(acompanhamento))
        );
        this.cref = cref;
    }

    public List<AcompanhamentoResponseDTO> getAcompanhamentos() {
        return acompanhamentos;
    }

    public void setAcompanhamentos(List<AcompanhamentoResponseDTO> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }
}
