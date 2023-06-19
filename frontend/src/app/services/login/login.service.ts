import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, take } from 'rxjs';
import { UserLogin } from '../../models/login.models';
import { UserJson } from '../../models/login.models';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}
    
    login(login: UserLogin): Observable<UserJson> {

      const url = 'users/login'

      return this.http.post<UserJson>(url, login).pipe(take(1));
    }
}
