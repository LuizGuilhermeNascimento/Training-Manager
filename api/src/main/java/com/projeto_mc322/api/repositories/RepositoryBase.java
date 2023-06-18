package com.projeto_mc322.api.repositories;

import java.util.UUID;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.datapersistence.JsonSerializable;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;
import org.springframework.http.HttpStatus;

import javax.json.JsonObject;

public abstract class RepositoryBase<T extends JsonSerializable> {
    private final Class<T> type;

    public RepositoryBase(Class<T> clazz) {
        this.type = clazz;
    }

    public T find(UUID id) throws HttpException {
        try {
            String path = JsonManager.buildPath(type, id);
            JsonObject jsonObject = JsonManager.readFile(path);
            return BuildObject.build(type, jsonObject);
        }catch (Exception e){
            throw new HttpException(type.getSimpleName() + " não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public boolean remove(UUID id) {
        return JsonManager.excluirArquivo(JsonManager.buildPath(type, id));
    }
    public boolean save(T t) {
        return JsonManager.writeFile(t);
    }
}
