package com.projeto_mc322.api.repositories;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import org.springframework.stereotype.Repository;

@Repository
public class AcompanhamentoRepository extends RepositoryBase<Acompanhamento> {
    public AcompanhamentoRepository() {
        super(Acompanhamento.class);
    }
}
