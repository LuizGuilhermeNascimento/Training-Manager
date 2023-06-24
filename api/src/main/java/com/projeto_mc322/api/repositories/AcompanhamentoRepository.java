package com.projeto_mc322.api.repositories;

import com.datapersistence.BuildObject;
import com.datapersistence.JsonManager;
import com.projeto_mc322.api.exceptions.HttpException;
import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.json.JsonObject;
import java.util.UUID;

@Repository
public class AcompanhamentoRepository extends RepositoryBase<Acompanhamento> {
    public AcompanhamentoRepository() {
        super(Acompanhamento.class);
    }
}
