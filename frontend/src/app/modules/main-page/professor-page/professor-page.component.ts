import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {
  Acompanhamento,
  AcompanhamentoJson,
} from 'src/app/models/acompanhamento.model';
import { Aluno, ListAlunos } from 'src/app/models/aluno.model';
import { Professor } from 'src/app/models/professor.model';
import { Treino } from 'src/app/models/treino.model';
import { AcompanhamentoService } from 'src/app/services/acompanhamento/acompanhamento.service';
import { AlunoService } from 'src/app/services/aluno/aluno.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { ProfessorService } from 'src/app/services/professor/professor.service';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css'],
})
export class ProfessorPageComponent implements OnInit {
  acompanhamentos: Acompanhamento[];
  alunosAssociados: Aluno[] = [];
  novoAcompanhamento: AcompanhamentoJson;
  idProfessor: string;
  tiposTreino: string[] = [];
  numTipos: number = 0;
  campoVazio: boolean = false;
  treinos: Treino[];
  nomeAlunoNovoAcomp: string;
  alunoEncontrado: boolean = true;
  numTreinosCorreto: boolean = true;
  @ViewChild('meta') meta: ElementRef<HTMLInputElement>;

  nome = '';
  email = '';

  constructor(
    private acompanhamentoService: AcompanhamentoService,
    private localStorageService: LocalStorageService,
    private alunoService: AlunoService,
    private professorService: ProfessorService,
    private validationService: ValidationService,
    private router: Router
  ) {
    this.acompanhamentos = [];
    this.idProfessor = localStorageService.getItem<string>(keys.idKey) ?? '';
  }

  ngOnInit(): void {
    if (
      this.validationService.isLoggedIn() &&
      this.localStorageService.getItem<number>(keys.roleKey) == 0
    ) {
      let responseAcomp =
        this.acompanhamentoService.getAcompanhamentosDoProfessor(
          this.idProfessor
        );
      responseAcomp.subscribe(
        (acompanhamentos) => (this.acompanhamentos = acompanhamentos)
      );
      this.buscarDadosProfessor(
        this.localStorageService.getItem<string>(keys.idKey)
      );
      this.buscarAlunos();
      this.gerarAcompanhamentoVazio();
    } else {
      this.router.navigate(['/main']);
    }
  }

  private buscarAlunos(): void {
    const responseAlunos = this.alunoService.getTodosAlunos();
    responseAlunos.subscribe({
      next: (alunos: ListAlunos) => {
        this.alunosAssociados = alunos.list;
      },
    });
  }

  private buscarDadosProfessor(id: string | null): void {
    if (id) {
      const response = this.professorService.getProfessorPorId(id);
      response.subscribe({
        next: (professor: Professor) => {
          this.nome = professor.nome;
          this.email = professor.email;
        },
      });
    }
  }

  gerarAcompanhamentoVazio() {
    this.novoAcompanhamento = {
      alunoId: '',
      professorId: this.idProfessor,
      treinos: [],
      treinosMeta: 0,
    };
  }

  possuiTreinosVazios(): boolean {
    let treinoVazio: boolean = false;
    this.novoAcompanhamento.treinos.forEach((treino) => {
      if (treino.nome == '' || treino.descricao == '') {
        treinoVazio = true;
      }
    });
    return treinoVazio;
  }

  adicionarTreino() {
    if (this.possuiTreinosVazios()) {
      this.campoVazio = true;
      return;
    }
    this.campoVazio = false;
    switch (this.numTipos) {
      case 1:
        this.tiposTreino.push('A');
        this.novoAcompanhamento.treinos.push({
          tipo: 'A',
          nome: '',
          descricao: '',
        });
        break;
      case 2:
        this.tiposTreino.push('B');
        this.novoAcompanhamento.treinos.push({
          tipo: 'B',
          nome: '',
          descricao: '',
        });
        break;
      case 3:
        this.tiposTreino.push('C');
        this.novoAcompanhamento.treinos.push({
          tipo: 'C',
          nome: '',
          descricao: '',
        });
        break;
      case 4:
        this.tiposTreino.push('D');
        this.novoAcompanhamento.treinos.push({
          tipo: 'D',
          nome: '',
          descricao: '',
        });
        break;
      case 5:
        this.tiposTreino.push('E');
        this.novoAcompanhamento.treinos.push({
          tipo: 'E',
          nome: '',
          descricao: '',
        });
        break;
    }
    this.numTipos++;
  }
  verificarCamposVazios() {
    this.campoVazio =
      this.nomeAlunoNovoAcomp == '' || this.novoAcompanhamento.treinosMeta == 0;
  }
  changeMetaTreinos() {
    let metaAtual: number = parseInt(this.meta.nativeElement.value);
    if (!(metaAtual >= 10 && metaAtual <= 30)) {
      this.numTreinosCorreto = false;
    } else {
      this.novoAcompanhamento.treinosMeta = metaAtual;
      this.numTreinosCorreto = true;
    }
  }
  changeNomeAluno(nomeAluno: HTMLInputElement) {
    if (!this.alunoExiste() || nomeAluno.value == '') {
      this.alunoEncontrado = false;
      return;
    }
    this.alunoEncontrado = true;
  }

  alunoExiste(): boolean {
    let existe: boolean = false;
    this.alunosAssociados.forEach((aluno) => {
      if (aluno.nome == this.nomeAlunoNovoAcomp) {
        existe = true;
      }
    });
    return existe;
  }

  setAlunoId() {
    this.alunosAssociados.forEach((aluno) => {
      if (aluno.nome == this.nomeAlunoNovoAcomp) {
        this.novoAcompanhamento.alunoId = aluno.id;
      }
    });
  }

  restoreAcompanhamentosProfessor() {
    if (
      this.validationService.isLoggedIn() &&
      this.localStorageService.getItem<number>(keys.roleKey) == 0
    ) {
      let responseAcomp =
        this.acompanhamentoService.getAcompanhamentosDoProfessor(
          this.idProfessor
        );
      responseAcomp.subscribe(
        (acompanhamentos) => (this.acompanhamentos = acompanhamentos)
      );
      console.log(this.acompanhamentos);
    }
  }

  resetForm() {
    this.gerarAcompanhamentoVazio();
    this.nomeAlunoNovoAcomp = '';
    this.tiposTreino = [];
    this.meta.nativeElement.value = '';
  }

  onSubmit() {
    this.verificarCamposVazios();
    this.setAlunoId();
    console.log(this.novoAcompanhamento);
    this.acompanhamentoService
      .createAcompanhamento(this.novoAcompanhamento)
      .subscribe({
        next: (value) => {
          this.acompanhamentos.push(value);
        },
        error: (error: HttpErrorResponse) => {
          // todo error message
          alert(error.error);
          // console.log(error);
        },
      });
    this.restoreAcompanhamentosProfessor();
    this.resetForm();
  }

  logOut(): void {
    this.localStorageService.clear();
    this.router.navigate(['']);
  }
}
