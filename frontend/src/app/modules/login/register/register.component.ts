import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private router: Router, private validationService: ValidationService) {}

  ngOnInit(): void {
    if (this.validationService.isLoggedIn()){
      this.router.navigate(["/main"])
    }
  }

  navigateToStartPage() {
    this.router.navigate([``]);
  }

  navigateToLogin() {
    this.router.navigate(['/', `login`]);
  }
}
