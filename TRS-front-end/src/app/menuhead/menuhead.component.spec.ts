import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuheadComponent } from './menuhead.component';

describe('MenuheadComponent', () => {
  let component: MenuheadComponent;
  let fixture: ComponentFixture<MenuheadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuheadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuheadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
