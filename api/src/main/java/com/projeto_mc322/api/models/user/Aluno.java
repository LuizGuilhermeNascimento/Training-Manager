package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;

public class Aluno extends User{
    private Acompanhamento acompanhamento;
    public Aluno(String nome, String cpf, String email, String senha, Acompanhamento acompanhamento) {
        super(nome, cpf, email, senha);
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
}
