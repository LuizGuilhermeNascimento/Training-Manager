import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';
import { AcompanhamentoService } from 'src/app/services/acompanhamento/acompanhamento.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';
import { ValidationService } from 'src/app/services/validation/validation.service';

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css'],
})
export class ProfessorPageComponent implements OnInit {
  acompanhamentos: Acompanhamento[];
  idProfessor: string;

  constructor(
    private acompanhamentoService: AcompanhamentoService,
    private localStorageService: LocalStorageService,
    private validationService: ValidationService,
    private router: Router
  ) {
    this.acompanhamentos = [];
    this.idProfessor = localStorageService.getItem<string>(keys.idKey) ?? '';
  }

  ngOnInit(): void {
    if (
      this.validationService.isLoggedIn() &&
      this.localStorageService.getItem<number>(keys.roleKey) === 0
    ) {
      let response = this.acompanhamentoService.getAcompanhamentosDoProfessor(
        this.idProfessor
      );
      response.subscribe(
        (acompanhamentos) => (this.acompanhamentos = acompanhamentos)
      );
    } else {
      this.router.navigate(['/main']);
    }
  }
}
