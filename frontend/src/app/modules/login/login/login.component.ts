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
export class LoginComponent implements OnInit {

  constructor(private router: Router) {
    
  }
  ngOnInit(): void {
      
  }
  navigateToRegister() {
    this.router.navigate(['/', 'register'])
  }
  navigateToStartPage() {
    this.router.navigate(['/'])
  }
}
