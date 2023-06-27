import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Acompanhamento } from 'src/app/models/acompanhamento.model';
import { AcompanhamentoService } from 'src/app/services/acompanhamento/acompanhamento.service';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';

@Component({
  selector: 'app-acompanhamento-item',
  templateUrl: './acompanhamento-item.component.html',
  styleUrls: ['./acompanhamento-item.component.css'],
})
export class AcompanhamentoItemComponent implements OnInit {
  @Input() acompanhamento: Acompanhamento;
  @Output() deleteAcomp: EventEmitter<any> = new EventEmitter<any>();
  treinosTipo: string = '';

  constructor(
    private acompanhamentoService: AcompanhamentoService,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit(): void {
    this.acompanhamento.treinos.forEach((treino) =>
      this.treinosTipo.concat(treino.tipo)
    );
  }

  concluirAcompanhamento(): void {
    if (!confirm('Deseja concluir esse acompanhamento?')) return;
    this.acompanhamentoService
      .deleteAcompanhamento(this.acompanhamento.id)
      .subscribe({
        error: () => {
          this.deleteAcomp.emit();
        },
      });
  }
}
