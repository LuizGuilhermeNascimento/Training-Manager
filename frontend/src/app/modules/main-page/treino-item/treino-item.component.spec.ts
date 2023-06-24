import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreinoItemComponent } from './treino-item.component';

describe('TreinoItemComponent', () => {
  let component: TreinoItemComponent;
  let fixture: ComponentFixture<TreinoItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TreinoItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TreinoItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
