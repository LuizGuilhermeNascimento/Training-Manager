import { Treino } from "./treino.model";

export interface Acompanhamento {
    id: string;
    alunoId: string;
    nomeAluno: string;
    professorId: string;
    treinos: Treino[];
    treinosRealizados: number;
    treinosMeta: number;
}

export interface AcompanhamentoJson {
    alunoId: string;
    professorId: string;
    treinos: Treino[];
    treinosMeta: number;
}