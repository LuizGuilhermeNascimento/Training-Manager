package com.projeto_mc322.api.repositories;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.datapersistence.JsonSerializable;
import com.projeto_mc322.api.exceptions.HttpException;
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

    public List<T> list(){
        List<T> list = new ArrayList<>();
        String path = "dados/" + type.getSimpleName();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path))){
            for (Path file: stream){
                String id = file.getFileName().toString();
                list.add(find(UUID.fromString(id.replace(".json",""))));
            }
        }catch (Exception ignored){
        }
        return list;
    }
}
