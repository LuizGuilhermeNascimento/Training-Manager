import { Component, Input, OnInit } from '@angular/core';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';

@Component({
  selector: 'app-acompanhamento-item',
  templateUrl: './acompanhamento-item.component.html',
  styleUrls: ['./acompanhamento-item.component.css']
})
export class AcompanhamentoItemComponent implements OnInit{
  @Input() acompanhamento: Acompanhamento;
  treinosTipo: string = '';

  constructor() {}

  ngOnInit(): void {
    this.acompanhamento.treinos.forEach(treino => this.treinosTipo.concat(treino.tipo));
  }
}
