import { Component, Input } from '@angular/core';
import { Treino } from 'src/app/models/treino.model';

@Component({
  selector: 'app-treino-item',
  templateUrl: './treino-item.component.html',
  styleUrls: ['./treino-item.component.css']
})
export class TreinoItemComponent {
  @Input() treino: Treino;
}
