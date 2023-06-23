import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  constructor(
    private router: Router,
    private validationService: ValidationService
  ) {}

  ngOnInit(): void {
    if (!this.validationService.isLoggedIn()) {
      this.router.navigate(["/login"])
    }
  }
}
