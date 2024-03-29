import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './modules/login/login.module';
import { StartPageModule } from './modules/start-page/start-page.module';
import { StartPageComponent } from './modules/start-page/start-page/start-page.component';
import { LoginService } from './services/login/login.service';
import { SignUpService } from './services/sign-up/sign-up.service';
import { AcompanhamentoService } from './services/acompanhamento/acompanhamento.service';
import { AlunoService } from './services/aluno/aluno.service';
import { ProfessorService } from './services/professor/professor.service';
import { HttpClientModule } from '@angular/common/http';
import { MainPageModule } from './modules/main-page/main-page.module';
import { ValidationService } from './services/validation/validation.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    StartPageModule,
    HttpClientModule,
    MainPageModule
  ],
  providers: [
    LoginService,
    SignUpService,
    AcompanhamentoService,
    AlunoService,
    ProfessorService,
    ValidationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
