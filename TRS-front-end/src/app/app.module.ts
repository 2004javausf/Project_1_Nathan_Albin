import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import{ ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { UserService } from './services/user.service';
import { MenuComponent } from './menu/menu.component';
import { LoginService } from './services/login.service';
import { MenubenComponent } from './menuben/menuben.component';
import { MenuheadComponent } from './menuhead/menuhead.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    MenuComponent,
    MenubenComponent,
    MenuheadComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path : '',
        component : HomeComponent 
      },
      {
        path : 'login',
        component : LoginComponent
      },
      {
        path : 'profile',
        component : ProfileComponent
      },
      {
        path : 'menu',
        component : MenuComponent
      },
      {
        path : 'menuben',
        component : MenubenComponent
      },
      {
        path : 'menuhead',
        component : MenuheadComponent
      }
    ])
  ],
  providers: [
    UserService,
    LoginService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
