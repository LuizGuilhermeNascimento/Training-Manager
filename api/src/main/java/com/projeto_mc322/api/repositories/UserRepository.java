package com.projeto_mc322.api.repositories;

import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository implements IRepository<User> {
    @Override
    public User find(UUID id) throws HttpException {
        return null;
    }

    @Override
    public boolean remove(UUID id) {
        return false;
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    public void create(User user) throws HttpException {

    }

    public User findByEmail(String email) throws HttpException {
        return null;
    }

    public User findByCpf(String cpf) throws HttpException{
        return null;
    }
}
