import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FareDetailsComponent } from './fare-details.component';

describe('FareDetailsComponent', () => {
  let component: FareDetailsComponent;
  let fixture: ComponentFixture<FareDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FareDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FareDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
