package com.projeto_mc322.api.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.projeto_mc322.api.models.user.Aluno;

@Repository
public class AlunoRepository implements IRepository<Aluno>{
    @Override
    public Aluno find(UUID id) {
        return null;
    }
    @Override
    public boolean remove(UUID id) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean save(Aluno t) {
        // TODO Auto-generated method stub
        return false;
    }
}
