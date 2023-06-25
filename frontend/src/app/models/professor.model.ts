import { Acompanhamento } from "./acompanhamento.model";

export interface ProfessorJson {
    cref: string;
    acompanhamentos: Acompanhamento[];
}

export interface Professor {
    nome: string;
    cpf: string;
    email: string;
    cref: string
    acompanhamentos: Acompanhamento[];
}