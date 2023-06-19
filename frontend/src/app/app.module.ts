import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './modules/login/login.module';
import { StartPageModule } from './modules/start-page/start-page.module';
import { MainPageModule } from './modules/main-page/main-page.module';
import { StartPageComponent } from './modules/start-page/start-page/start-page.component';
import { LoginService } from './services/login/login.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    StartPageModule,
    MainPageModule
  ],
  providers: [
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
