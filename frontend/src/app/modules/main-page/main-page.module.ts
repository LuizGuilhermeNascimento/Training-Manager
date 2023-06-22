import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './main-page/main-page.component';
import { ProfessorPageModule } from './professor-page/professor-page.module';
import { ProfessorPageComponent } from './professor-page/professor-page/professor-page.component';



@NgModule({
  declarations: [
    ProfessorPageComponent,
    MainPageComponent
  ],
  imports: [
    NgModule,
    CommonModule,
    ProfessorPageModule
  ]
})
export class MainPageModule { }
