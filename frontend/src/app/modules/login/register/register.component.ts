import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlunoSign, ProfessorSign } from 'src/app/models/sign-up.model';
import { ValidacaoFormulario } from 'src/app/services/validacao-form/validacao-form.service';
import { SignUpService } from '../../../services/sign-up/sign-up.service';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { Observable } from 'rxjs';
import { UserJson } from 'src/app/models/login.models';
import { ValidationService } from 'src/app/services/validation/validation.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})

export class RegisterComponent{

  alunoIsLogin: boolean;

  professorSign: ProfessorSign;
  alunoSign: AlunoSign;
  canSubmit: boolean;
  cpfValido: boolean;
  emailValido: boolean;
  senhaValida: boolean;
  crefValido: boolean;
  mensagemError: string;
  campoVazio: boolean;

  constructor(private router: Router, private SignUpService: SignUpService, private localStorageService: LocalStorageService) {
    this.alunoIsLogin = true;
    this.professorSign = this.generateNewProfessorSign();
    this.alunoSign = this.generateNewAlunoSign();
    this.canSubmit = false;
    this.cpfValido = true;
    this.emailValido = true;
    this.senhaValida = true;
    this.crefValido = true;
    this.mensagemError = '';
    this.campoVazio = false;
  }

  generateNewAlunoSign(): AlunoSign {
    return {
      nome: '',
      cpf: '',
      email: '',
      senha: ''
    }
  }

  generateNewProfessorSign(): ProfessorSign {
    return {
      nome: '',
      cpf: '',
      email: '',
      senha: '',
      cref: ''
    }
  }

  fillProfessorWithAluno(): ProfessorSign {
    return {
      nome: this.alunoSign.nome,
      cpf: this.alunoSign.cpf,
      email: this.alunoSign.email,
      senha: this.alunoSign.senha,
      cref: ''
    }
  }

  fillAlunoWithProfessor(): AlunoSign {
    return {
      nome: this.professorSign.nome,
      cpf: this.professorSign.cpf,
      email: this.professorSign.email,
      senha: this.professorSign.senha,
    }
  }

  changeToProfessorLogin() {
    this.alunoIsLogin = false;
    this.professorSign = this.fillProfessorWithAluno();
  }

  changeToAlunoLogin() {
    this.alunoIsLogin = true;
    this.alunoSign = this.generateNewAlunoSign();
  }

  navigateToStartPage() {
    this.router.navigate([``]);
  }

  navigateToLogin() {
    this.router.navigate(['/', `login`]);
  }

  updateInputForm(element: HTMLInputElement): void {
    let placeholderTag: string = element.placeholder;
    let value: string = element.value;
    if (this.alunoIsLogin) {
      switch(placeholderTag) {
        case "NAME":
          this.alunoSign.nome = value;
          break
        case "CPF":
          this.alunoSign.cpf = value;
          break
        case "E-MAIL":
          this.alunoSign.email = value;
          break
        case "PASSWORD":
          this.alunoSign.senha = value;
          break
      }

    } else {
      switch(placeholderTag) {
        case "NAME":
          this.professorSign.nome = value;
          break
        case "CPF":
          this.professorSign.cpf = value;
          break
        case "E-MAIL":
          this.professorSign.email = value;
          break
        case "PASSWORD":
          this.professorSign.senha = value;
          break
        case "CREF":
          this.professorSign.cref = value;
          break
      }
    }
  }
  validacaoCPF(cpf: HTMLInputElement): void {
    if (ValidacaoFormulario.validarCPF(cpf.value) || cpf.value == '') {
      this.cpfValido = true;

    } else {
      // TODO estilização - victor
      this.cpfValido = false;
      this.mensagemError = 'CPF inválido!'
    }
  }
  validacaoEmail(email: HTMLInputElement): void {
    if (ValidacaoFormulario.validarEmail(email.value) || email.value == '') {
      this.emailValido = true;

    } else {
      // TODO estilização - victor
      this.emailValido = false;
      this.mensagemError = 'Email inválido!'
    }
  }

  validacaoSenha(senha: HTMLInputElement): void {
    let mensagemResposta = ValidacaoFormulario.validarSenha(senha.value);
    if (mensagemResposta == 'A senha é válida') {
      this.senhaValida = true;

    } else {
      // TODO estilização - victor
      this.senhaValida = false;
      this.mensagemError = mensagemResposta;
    }
  }

  validacaoCREF(cref: HTMLInputElement): void {
    if (ValidacaoFormulario.validarCREF(cref.value)  || cref.value == '') {
      this.crefValido = true;

    } else {
      // TODO estilização - victor
      this.crefValido = false;
      this.mensagemError = 'CREF inválido! Insira no formato: UF000000';
    }
  }

  validacaoGeral(): boolean {
    if (this.alunoIsLogin) {
      return (
        ValidacaoFormulario.validarEmail(this.alunoSign.email) &&
        ValidacaoFormulario.validarCPF(this.alunoSign.cpf) &&
        (ValidacaoFormulario.validarSenha(this.alunoSign.senha) == 'A senha é válida')
      )
    }
    return (
      ValidacaoFormulario.validarEmail(this.professorSign.email) &&
      ValidacaoFormulario.validarCPF(this.professorSign.cpf) &&
      (ValidacaoFormulario.validarSenha(this.professorSign.senha) == 'A senha é válida') &&
      ValidacaoFormulario.validarCREF(this.professorSign.cref)
    )
  }

  verificarCamposVazios(): void {
    if (this.alunoIsLogin) {
      this.campoVazio = (
        this.alunoSign.nome == '' &&
        this.alunoSign.email == '' &&
        this.alunoSign.cpf == '' &&
        this.alunoSign.senha == ''
      )
    }
    this.campoVazio = (
      this.professorSign.nome == '' &&
      this.professorSign.email == '' &&
      this.professorSign.cpf == '' &&
      this.professorSign.senha == '' &&
      this.professorSign.cref == ''
    )
  }

  onSubmit() {
    this.canSubmit = this.validacaoGeral();
    if (!this.canSubmit) {
      // exibir erro na tela  - TODO estilização victor
      return;
    }

    let response: Observable<UserJson> = new Observable();

    if (this.alunoIsLogin) {
      response = this.SignUpService.signUpAluno(this.alunoSign);
    } else {
      response = this.SignUpService.signUpProfessor(this.professorSign);
    }
    response.subscribe(user => {
      console.log(user)
      this.localStorageService.setItem(keys.roleKey, user.role);
      this.localStorageService.setItem(keys.idKey, user.id);
      this.router.navigate(['/', 'main']);
    });
  }
}
