import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() title: string;
  @Input() isVisible: boolean;
  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();

  onClose(): void {
    this.closeModal.emit();
  }
}