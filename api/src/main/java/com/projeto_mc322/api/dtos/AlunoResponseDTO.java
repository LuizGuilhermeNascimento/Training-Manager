package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Aluno;

public class AlunoResponseDTO extends UserResponseDTO {
    private AcompanhamentoResponseDTO acompanhamento;
    public AlunoResponseDTO(Aluno aluno) {
        super(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getEmail());
        if (aluno.getAcompanhamento().isPresent()){
            acompanhamento = new AcompanhamentoResponseDTO(aluno.getAcompanhamento().get());
        }
    }

    public AcompanhamentoResponseDTO getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(AcompanhamentoResponseDTO acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
}
