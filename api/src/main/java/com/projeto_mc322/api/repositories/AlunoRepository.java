package com.projeto_mc322.api.repositories;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.UUID;

@Repository
public class AlunoRepository extends RepositoryBase<Aluno> {
    public AlunoRepository() {
        super(Aluno.class);
    }

    public void create(Aluno aluno) throws HttpException {
        if (!JsonManager.cpfUnico(aluno.getCpf())) {
            throw new HttpException("Cpf já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.emailUnico(aluno.getEmail())){
            throw new HttpException("Email já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.writeFile(aluno)){
            throw new HttpException("Usuário não criado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
