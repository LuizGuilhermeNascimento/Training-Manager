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
public class AcompanhamentoRepository implements IRepository<Acompanhamento> {
    @Override
    public Acompanhamento find(UUID id) throws HttpException {
        try {
            JsonObject jsonObject = JsonManager.readFile("dados/Acompanhamento/" + id.toString() + ".json");
            return BuildObject.buildAcompanhamento(jsonObject);
        }catch (Exception e){
            throw new HttpException("Acompanhamento não encontrado", HttpStatus.NOT_FOUND);
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
