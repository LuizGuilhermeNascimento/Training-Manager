import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { AlunoPageComponent } from './aluno-page/aluno-page.component';
import { ProfessorPageComponent } from './professor-page/professor-page.component';



@NgModule({
  declarations: [
    MainComponent,
    AlunoPageComponent,
    ProfessorPageComponent
  ],
  imports: [
    CommonModule
  ]
})
export class MainPageModule { }
