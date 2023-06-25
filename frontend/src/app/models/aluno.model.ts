import { Acompanhamento } from './acompanhamento.model';

export interface AlunoJson {
  acompanhamento: Acompanhamento;
}

export interface Aluno {
  id: string;
  nome: string;
  cpf: string;
  email: string;
  acompanhamento: Acompanhamento;
}

export interface ListAlunos {
  list: Aluno[];
}
