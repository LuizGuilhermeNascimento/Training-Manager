import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserJson, UserLogin } from 'src/app/models/login.models';
import { AlunoSign, BasicSign } from 'src/app/models/sign-up.model';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user: UserLogin;

  constructor(private router: Router, private loginService: LoginService, private localStorageService: LocalStorageService) {
    this.user = this.generateNewUser();
  }

  generateNewUser(): UserLogin {
    return {
      email: '',
      senha:''
    }
  }

  navigateToStartPage() {
    this.router.navigate([``])
  }
  navigateToRegister() {
    this.router.navigate([`/`, `register`])
  }

  onSubmit() {
    let response = this.loginService.login(this.user);

    response.subscribe(user => {
      console.log(user)
      this.localStorageService.setItem(keys.roleKey, user.role);
      this.localStorageService.setItem(keys.idKey, user.id);
    });

    
  }
}
