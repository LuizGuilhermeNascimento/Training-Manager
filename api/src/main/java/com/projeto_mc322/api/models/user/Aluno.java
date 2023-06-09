package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.secao.Secao;
import com.projeto_mc322.api.models.treino.Treino;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends User{
    private Acompanhamento acompanhamento;
    private List<Secao> secoesAgendadas = new ArrayList<>();
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

    public List<Secao> getSecoesAgendadas() {
        return secoesAgendadas;
    }

    public void setSecoesAgendadas(List<Secao> secoesAgendadas) {
        this.secoesAgendadas = secoesAgendadas;
    }


}
