import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Challange } from './challange';

describe('Challange', () => {
  let component: Challange;
  let fixture: ComponentFixture<Challange>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Challange],
    }).compileComponents();

    fixture = TestBed.createComponent(Challange);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
