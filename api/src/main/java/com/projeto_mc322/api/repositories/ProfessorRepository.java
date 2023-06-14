package com.projeto_mc322.api.repositories;

import java.util.UUID;

import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Professor;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorRepository implements IRepository<Professor>{
    @Override
    public Professor find(UUID id) throws HttpException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(UUID id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean save(Professor t) {
        // TODO Auto-generated method stub
        return false;
    }
}
