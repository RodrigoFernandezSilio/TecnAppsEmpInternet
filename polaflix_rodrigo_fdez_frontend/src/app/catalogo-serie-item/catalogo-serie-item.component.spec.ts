import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoSerieItemComponent } from './catalogo-serie-item.component';

describe('CatalogoSerieItemComponent', () => {
  let component: CatalogoSerieItemComponent;
  let fixture: ComponentFixture<CatalogoSerieItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatalogoSerieItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CatalogoSerieItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
