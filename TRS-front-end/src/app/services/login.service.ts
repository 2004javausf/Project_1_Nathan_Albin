import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  user = {
    "username": "samssdora",
    "password": "1234"
  }
  onClick(e : Event): void {
    this.httpClient.post("http://localhost:8080/TRS/login", this.user)
    .subscribe(x => console.log(x));
  }

}
