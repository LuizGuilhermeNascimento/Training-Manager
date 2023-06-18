package com.projeto_mc322.api.repositories;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.models.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.UUID;

@Repository
public class UserRepository {
    public User findByEmail(String email) throws HttpException {
        return buscarPorCampo("email", email);
    }

    public User findByCpf(String cpf) throws HttpException{
        return buscarPorCampo("cpf", cpf);
    }

    private User buscarPorCampo(String campo, String valor) throws HttpException {
        try {
            JsonObject jsonObject = JsonManager.buscarPorCampo("dados/Professor", campo, valor);
            return BuildObject.buildProfessor(jsonObject);
        } catch (Exception e){
            try{
                JsonObject jsonObject = JsonManager.buscarPorCampo("dados/Aluno", campo, valor);
                return BuildObject.buildAluno(jsonObject);
            }catch (Exception ignore){
                throw new HttpException("Usuario não encontrado", HttpStatus.NOT_FOUND);
            }
        }
    }
}
