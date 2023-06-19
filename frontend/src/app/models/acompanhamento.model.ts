import { Treino } from "./treino.model";

export interface Acompanhamento {
    id: string;
    alunoId: string;
    professorId: string;
    treinos: Treino[];
    treinosRealizados: number;
    treinosMeta: number;
}