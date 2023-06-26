import { NgModule } from "@angular/core";
import { ModalComponent } from "./modal.component";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";


@NgModule({
    declarations: [
      ModalComponent
    ],
    imports: [
      CommonModule,
      FormsModule
    ]
  })
  export class ModalModule { }
  