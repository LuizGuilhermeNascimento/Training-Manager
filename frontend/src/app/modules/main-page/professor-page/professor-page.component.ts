import { Component, OnInit } from '@angular/core';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';
import { AcompanhamentoService } from 'src/app/services/acompanhamento/acompanhamento.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css']
})
export class ProfessorPageComponent implements OnInit {
  acompanhamentos: Acompanhamento[];
  idProfessor: string;

  constructor(private acompanhamentoService: AcompanhamentoService, private localStorageService: LocalStorageService) {
    this.acompanhamentos = [];
    this.idProfessor = localStorageService.getItem<string>(keys.idKey) ?? '';
  }

  ngOnInit(): void {
      let response = this.acompanhamentoService.getAcompanhamentosDoProfessor(this.idProfessor)
      response.subscribe(acompanhamentos => this.acompanhamentos = acompanhamentos);
  }


}
