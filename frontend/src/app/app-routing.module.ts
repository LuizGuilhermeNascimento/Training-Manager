import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/login/login/login.component';
import { RegisterComponent } from './modules/login/register/register.component';
import { StartPageComponent } from './modules/start-page/start-page/start-page.component';

const routes: Routes = [
  { path:'', component: StartPageComponent },
  { path:'login', component: LoginComponent },
  { path:'register', component: RegisterComponent }
  //{ path:'main', component: MainPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
