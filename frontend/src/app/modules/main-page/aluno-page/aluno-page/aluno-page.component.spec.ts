import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoPageComponent } from './aluno-page.component';

describe('AlunoPageComponent', () => {
  let component: AlunoPageComponent;
  let fixture: ComponentFixture<AlunoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlunoPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlunoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
