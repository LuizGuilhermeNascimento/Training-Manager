import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcompanhamentoItemComponent } from './acompanhamento-item.component';

describe('AcompanhamentoItemComponent', () => {
  let component: AcompanhamentoItemComponent;
  let fixture: ComponentFixture<AcompanhamentoItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcompanhamentoItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcompanhamentoItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
