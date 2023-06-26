import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';
import { Aluno } from 'src/app/models/aluno.model';
import { ListTreino, Treino } from 'src/app/models/treino.model';
import { AcompanhamentoService } from 'src/app/services/acompanhamento/acompanhamento.service';
import { AlunoService } from 'src/app/services/aluno/aluno.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-aluno-page',
  templateUrl: './aluno-page.component.html',
  styleUrls: ['./aluno-page.component.css'],
})
export class AlunoPageComponent implements OnInit {
  nome = '';
  email = '';
  cpf = '';
  acompanhamento: Acompanhamento | null;
  proximosTreinos: Treino[];
  proximoTreino: Treino | null;
  desejarExcluirConta: boolean = false;
  metaAtingida: boolean = false;

  constructor(
    private router: Router,
    private localStorageService: LocalStorageService,
    private validationService: ValidationService,
    private alunoService: AlunoService,
    private acompanhamentoService: AcompanhamentoService
  ) {}

  ngOnInit(): void {
    if (
      this.validationService.isLoggedIn() &&
      this.localStorageService.getItem<number>(keys.roleKey) == 1
    ) {
      this.buildAluno(this.localStorageService.getItem<string>(keys.idKey));
    } else {
      this.router.navigate(['/main']);
    }
  }

  finalizarTreino(): void {
    const id = this.localStorageService.getItem<string>(keys.idKey);
    if (id) {
      const response = this.acompanhamentoService.finalizarTreino(id);
      response.subscribe({
        next: (proximoTreino: Treino) => {
          this.proximoTreino = proximoTreino;
          this.getAcompanhamento(id);
          this.getProximosTreinos(id);
        },
      });
    }
  }

  private getAcompanhamento(id: string): void {
    this.acompanhamentoService.getAcompanhamentoDoAluno(id).subscribe({
      next: (acompanhamento: Acompanhamento) => {
        this.acompanhamento = acompanhamento;
        this.verificarMetaAtingida(acompanhamento);
      },
    });
  }

  private verificarMetaAtingida(acompanhamento: Acompanhamento): void {
    this.metaAtingida =
      acompanhamento.treinosRealizados >=
      acompanhamento.treinosMeta * acompanhamento.treinos.length;
  }

  private getProximosTreinos(id: string): void {
    this.acompanhamentoService.getProximosTreinos(id).subscribe({
      next: (treinos: ListTreino) => {
        this.proximosTreinos = treinos.list;
      },
    });
  }

  logOut(): void {
    this.localStorageService.clear();
    this.router.navigate(['/']);
  }

  private buildAluno(id: string | null): void {
    if (id) {
      const response = this.alunoService.getAlunoPorId(id);
      response.subscribe({
        next: (aluno: Aluno) => {
          this.nome = aluno.nome;
          this.email = aluno.email.toLowerCase();
          this.cpf = this.formatarCPF(aluno.cpf);
          this.acompanhamento = aluno.acompanhamento;
          if (aluno.acompanhamento) {
            this.getProximoTreino(id);
            this.getProximosTreinos(id);
            this.verificarMetaAtingida(aluno.acompanhamento);
          }
        },
      });
    }
  }

  private formatarCPF(cpf: string): string {
    let cpfFormatado: string = '';
    for (let i = 0; i < 9; i += 4) {
      cpfFormatado = cpfFormatado.concat(cpf.substring(i, i + 3) + '.');
    }
    return cpfFormatado.concat('-' + cpf.substring(9));
  }

  private getProximoTreino(id: string | null): void {
    if (id) {
      const response = this.acompanhamentoService.getProximoTreino(id);

      response.subscribe({
        next: (treino: Treino) => {
          this.proximoTreino = treino;
        },
      });
    }
  }

  public deletarConta(): void {
    if (!confirm('Tem certeza que deseja excluir sua conta?')) return;
    const id = this.localStorageService.getItem<string>(keys.idKey);
    if (id) {
      this.alunoService.deletarContaPorId(id).subscribe({ error: () => {} });
      this.logOut();
    }
  }
}
