import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StartPageComponent } from './start-page/start-page.component';
import { StartMenuComponent } from './start-menu/start-menu.component';



@NgModule({
  declarations: [
    StartPageComponent,
    StartMenuComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    StartPageComponent
  ]
})
export class StartPageModule { }
