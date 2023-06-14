package com.projeto_mc322.api.models.user;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Professor extends User {
    private final String cref;
    private List<Acompanhamento> acompanhamentos = new ArrayList<>();

    public Professor(String nome, String cpf, String cref, String email, String senha) {
        super(nome, cpf, email, senha);
        this.cref = cref;
    }

    public boolean deleteAcompanhamento(Acompanhamento acompanhamento){
        return acompanhamentos.remove(acompanhamento);
    }

    public boolean deleteAcompanhamento(UUID acompanhamentoId){
        try{
            Acompanhamento acompanhamento = findAcompanhamentoById(acompanhamentoId);
            return deleteAcompanhamento(acompanhamento);
        }catch(Exception e){
            return false;
        }
    }

    private Acompanhamento findAcompanhamentoById(UUID id) throws Exception{
        for (Acompanhamento acompanhamento : getAcompanhamentos()){
            if (acompanhamento.getId().equals(id)){
                return acompanhamento;
            }
        }
        throw new Exception();
    }

    public String getCref() {
        return cref;
    }

    public List<Acompanhamento> getAcompanhamentos() {
        return this.acompanhamentos;
    }

    public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }
}
