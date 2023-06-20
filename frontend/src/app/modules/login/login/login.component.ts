import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserLogin } from 'src/app/models/login.models';
import { AlunoSign, BasicSign } from 'src/app/models/sign-up.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLogin: boolean;
  formLogin: FormGroup;
  formRegister: FormGroup;
  initialFormRegister: BasicSign;
  initialFormLogin: UserLogin;
  alunoSigning: boolean;

  constructor(fb: FormBuilder) {
    this.isLogin = true;
    this.alunoSigning = true;
  }
  
  ngOnInit(): void {
    this.getBlankFormLogin();
    this.buildFormLogin();
  }

  /*************** REGISTER *****************/
  getBlankFormRegister(): void {
    
    this.initialFormRegister = {
      nome: '',
      cpf: '',
      email: '',
      senha: '',
      cref: ''
    }
    
  }

  buildFormRegister(): void {

    this.formRegister = this.fb.group({
      nome: [this.initialFormRegister.nome, Validators.required],
      cpf: [this.initialFormRegister.cpf, [Validators.maxLength(8), Validators.minLength(8)]],
      email: [this.initialFormRegister.email, [Validators.required, Validators.email]],
      senha: [this.initialFormRegister.senha, [Validators.required, Validators.minLength(8)]],
      cref: [this.initialFormRegister.cref, Validators.required],
    });
  }

  /*************** LOGIN *****************/
  getBlankFormLogin(): void {
    
    this.initialFormLogin = {
      email: '',
      senha: '',
    }
  }

  buildFormLogin(): void {

    this.formLogin =  this.fb.group({
      email: [this.initialFormRegister.email, [Validators.required, Validators.email]],
      senha: [this.initialFormRegister.senha, Validators.required]
    });
  }


  changeToLogin() {
    this.isLogin = true;
    this.buildFormLogin();
    this.getBlankFormLogin();
  }

  changeToRegister() {
    this.isLogin = false;
    this.buildFormRegister();
    this.getBlankFormRegister();
  }

  // TODO
  onSubmit() {

  }
}
