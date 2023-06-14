package com.projeto_mc322.api.repositories;

import com.datapersistence.JsonManager;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.models.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
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
//        try {
//            JsonObject jsonObject = JsonManager.buscarPorCampo("dados/Professor", "email", email);
//
//        } catch (Exception e){
//            try{
//                JsonObject jsonObject = JsonManager.buscarPorCampo("dados/Aluno", "email", email);
//
//            }catch (Exception ignore){
//                throw new HttpException("Usuario não encontrado", HttpStatus.NOT_FOUND);
//            }
//        }
        // TODO: 14/06/2023
        return null;
    }

    public User findByCpf(String cpf) throws HttpException{
        return null;
    }
}
