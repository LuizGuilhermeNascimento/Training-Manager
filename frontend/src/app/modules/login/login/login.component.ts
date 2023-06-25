import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, first, of, throwError } from 'rxjs';
import { UserJson, UserLogin } from 'src/app/models/login.models';
import { AlunoSign, BasicSign } from 'src/app/models/sign-up.model';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { LoginService } from 'src/app/services/login/login.service';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: UserLogin;
  errorMessage = ""

  constructor(
    private router: Router,
    private loginService: LoginService,
    private localStorageService: LocalStorageService,
    private validationService: ValidationService
  ) {
    this.user = this.generateNewUser();
  }

  ngOnInit(): void {
    if (this.validationService.isLoggedIn()) {
      this.router.navigate(['/main']);
    }
  }

  generateNewUser(): UserLogin {
    return {
      email: '',
      senha: '',
    };
  }

  navigateToStartPage() {
    this.router.navigate(['']);
  }
  navigateToRegister() {
    this.router.navigate(['/register']);
  }

  onSubmit() {
    const response: Observable<UserJson> = this.loginService.login(this.user);

    response.subscribe({
      next: (user) => {
        console.log(user);
        
        this.localStorageService.setItem(keys.roleKey, user.role);
        this.localStorageService.setItem(keys.idKey, user.id);
        this.router.navigate(['/', 'main']);
      },
      error: (error) => {
        this.errorMessage = "Credenciais Inválidas"
      },
    });
  }
}
