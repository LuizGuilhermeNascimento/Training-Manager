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

    @Override
    public Acompanhamento find(UUID id) throws HttpException {
        try {
            String path = JsonManager.buildPath(Acompanhamento.class, id);
            JsonObject jsonObject = JsonManager.readFile(path);
            return BuildObject.buildAcompanhamento(jsonObject);
        }catch (Exception e){
            throw new HttpException("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public boolean save(Acompanhamento acompanhamento) {
        return JsonManager.writeFile(acompanhamento);
    }

    @Override
    public boolean remove(UUID id) {
        return JsonManager.excluirArquivo("dados/Acompanhamento/" + id + ".json");
    }
}
