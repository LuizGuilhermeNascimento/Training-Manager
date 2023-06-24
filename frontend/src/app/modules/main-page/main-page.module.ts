import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { AlunoPageComponent } from './aluno-page/aluno-page.component';
import { ProfessorPageComponent } from './professor-page/professor-page.component';
import { AcompanhamentoItemComponent } from './acompanhamento-item/acompanhamento-item.component';
import { TreinoItemComponent } from './treino-item/treino-item.component';



@NgModule({
  declarations: [
    MainComponent,
    AlunoPageComponent,
    ProfessorPageComponent,
    AcompanhamentoItemComponent,
    TreinoItemComponent
  ],
  imports: [
    CommonModule
  ]
})
export class MainPageModule { }
