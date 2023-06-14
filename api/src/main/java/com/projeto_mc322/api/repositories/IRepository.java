package com.projeto_mc322.api.repositories;

import java.util.UUID;

import com.projeto_mc322.api.exceptions.HttpException;

public interface IRepository<T> {
    public boolean save(T t);
    public T find(UUID id) throws HttpException;
    public boolean remove(UUID id);
}
