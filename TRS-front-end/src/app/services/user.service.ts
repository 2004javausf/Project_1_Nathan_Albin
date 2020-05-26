import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TRS_User } from '../TRS_User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpclient: HttpClient) { }

  getInfo() : Observable<TRS_User[]> {

    return this.httpclient.get<TRS_User[]>("http://localhost:8080/TRS/info");
  }


}
