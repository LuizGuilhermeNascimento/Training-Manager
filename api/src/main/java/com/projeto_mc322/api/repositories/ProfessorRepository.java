package com.projeto_mc322.api.repositories;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.UUID;

@Repository
public class ProfessorRepository extends RepositoryBase<Professor> {
    public ProfessorRepository() {
        super(Professor.class);
    }

    public void create(Professor professor) throws HttpException {
        if (!JsonManager.crefUnico(professor.getCref())) {
            throw new HttpException("Cref já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.cpfUnico(professor.getCpf())) {
            throw new HttpException("Cpf já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.emailUnico(professor.getEmail())){
            throw new HttpException("Email já utilizado", HttpStatus.CONFLICT);
        }
        if (!JsonManager.writeFile(professor)){
            throw new HttpException("Usuário não criado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
