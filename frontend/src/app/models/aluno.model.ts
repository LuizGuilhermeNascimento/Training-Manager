import { Acompanhamento } from "./acompanhamento.model";

export interface AlunoJson {
    acompanhamento: Acompanhamento
}

export interface Aluno {
    id: string;
    name: string;
    cpf: string;
    email: string;
    acompanhamento: Acompanhamento;
}