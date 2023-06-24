import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/login/login/login.component';
import { RegisterComponent } from './modules/login/register/register.component';
import { StartPageComponent } from './modules/start-page/start-page/start-page.component';
import { MainComponent } from './modules/main-page/main/main.component';
import { ProfessorPageComponent } from './modules/main-page/professor-page/professor-page.component';
import { AlunoPageComponent } from './modules/main-page/aluno-page/aluno-page.component';

const routes: Routes = [
  { path: '', component: StartPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'main', component: MainComponent },
  { path: 'professor', component: ProfessorPageComponent },
  { path: 'aluno', component: AlunoPageComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
