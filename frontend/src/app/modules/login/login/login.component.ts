import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserLogin } from 'src/app/models/login.models';
import { AlunoSign, BasicSign } from 'src/app/models/sign-up.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router) {}

  navigateToStartPage() {
    this.router.navigate([``])
  }
  navigateToRegister() {
    this.router.navigate([`/`, `register`])
  }
}
