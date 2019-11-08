import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFareComponent } from './create-fare.component';

describe('CreateFareComponent', () => {
  let component: CreateFareComponent;
  let fixture: ComponentFixture<CreateFareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
