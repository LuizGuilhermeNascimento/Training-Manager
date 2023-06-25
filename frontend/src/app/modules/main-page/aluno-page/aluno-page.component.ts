import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';
import { Aluno } from 'src/app/models/aluno.model';
import { Treino } from 'src/app/models/treino.model';
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
  proximoTreino: Treino | null;

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
        next: () => {
          this.acompanhamentoService.getAcompanhamentoDoAluno(id).subscribe({
            next: (acompanhamento: Acompanhamento) => {
              this.acompanhamento = acompanhamento;
            },
          });
        },
        error: (error) => {},
      });
    }
  }

  logOut(): void {
    this.localStorageService.clear();
    this.router.navigate(['']);
  }

  private buildAluno(id: string | null): void {
    if (id) {
      const response = this.alunoService.getAlunoPorId(id);

      response.subscribe({
        next: (aluno: Aluno) => {
          this.nome = aluno.nome;
          this.email = aluno.email;
          this.cpf = aluno.cpf;
          this.acompanhamento = aluno.acompanhamento;
          if (aluno.acompanhamento) {
            this.getProximoTreino(id);
          }
        },
      });
    }
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
}
