import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserJson } from 'src/app/models/login.models';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  user: UserJson;
  constructor(
    private router: Router,
    private localStorageService: LocalStorageService,
    private validationService: ValidationService
  ) {
    this.user = {
      id: this.localStorageService.getItem<string>(keys.idKey) ?? '',
      role: this.localStorageService.getItem<number>(keys.roleKey) ?? 0,
    };
  }

  ngOnInit(): void {
    if (!this.validationService.isLoggedIn()) {
      this.router.navigate(['/login']);
    } else if (this.user.role == 0) {
      this.router.navigate(['/professor']);
    } else if (this.user.role == 1) {
      this.router.navigate(['/aluno']);
    }
  }
}
