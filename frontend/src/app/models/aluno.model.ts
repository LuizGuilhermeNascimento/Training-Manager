import { Acompanhamento } from "./acompanhamento.model";

export interface AlunoJson {
    acompanhamento: Acompanhamento
}

export interface Aluno {
    nome: string;
    cpf: string;
    email: string;
    acompanhamento: Acompanhamento;
}