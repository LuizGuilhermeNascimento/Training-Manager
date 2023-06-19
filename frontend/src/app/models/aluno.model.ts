import { Acompanhamento } from "./acompanhamento.model";

export interface AlunoJson {
    acompanhamento: Acompanhamento
}

export interface Aluno {
    name: string;
    cpf: string;
    email: string;
    acompanhamento: Acompanhamento;
}