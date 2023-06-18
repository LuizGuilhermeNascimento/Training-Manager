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
public class UserRepository implements IRepository<User> {
    @Override
    public User find(UUID id) throws HttpException {
        try{
            JsonObject jsonObject = JsonManager.readFile("dados/Aluno/" + id.toString() + ".json");
            return BuildObject.buildAluno(jsonObject);
        }catch (Exception e){
            try{
                JsonObject jsonObject = JsonManager.readFile("dados/Professor/" + id.toString() + ".json");
                return BuildObject.buildProfessor(jsonObject);
            } catch (Exception e1){
                throw new HttpException("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public boolean remove(UUID id) {
        return JsonManager.excluirArquivo("dados/Professor/" + id + ".json")
                || JsonManager.excluirArquivo("dados/Aluno/" + id + ".json");
    }

    @Override
    public boolean save(User user) {
        return JsonManager.writeFile(user);
    }

    public void create(User user) throws HttpException {
        if (user instanceof Professor && !JsonManager.crefUnico((((Professor) user).getCref()))){
            throw new HttpException("Cref já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.cpfUnico(user.getCpf())) {
            throw new HttpException("Cpf já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.emailUnico(user.getEmail())){
            throw new HttpException("Email já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.writeFile(user)){
            throw new HttpException("Usuário não criado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
