import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenubenComponent } from './menuben.component';

describe('MenubenComponent', () => {
  let component: MenubenComponent;
  let fixture: ComponentFixture<MenubenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenubenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenubenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
