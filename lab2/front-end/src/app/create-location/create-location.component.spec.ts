import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLocationComponent } from './create-location.component';

describe('CreateLocationComponent', () => {
  let component: CreateLocationComponent;
  let fixture: ComponentFixture<CreateLocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateLocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
