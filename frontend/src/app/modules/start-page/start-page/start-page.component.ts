import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-start-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.css'],
})
export class StartPageComponent implements OnInit {
  constructor(
    private router: Router,
    private validationService: ValidationService
  ) {}

  ngOnInit(): void {
    if (this.validationService.isLoggedIn()) {
      this.router.navigate(['/main']);
    }
  }
}
