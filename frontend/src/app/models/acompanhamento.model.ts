import { Treino } from "./treino.model";

export interface Acompanhamento {
    id: string;
    alunoId: string;
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