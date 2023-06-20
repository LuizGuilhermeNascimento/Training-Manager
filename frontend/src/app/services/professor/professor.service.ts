import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, take } from 'rxjs';
import { UserLogin } from '../../models/login.models';
import { UserJson } from '../../models/login.models';
import { Professor } from 'src/app/models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  constructor(private http: HttpClient) {}
    
    baseUrl = 'http://localhost:8080';

    getProfessorPorId(id: string): Observable<Professor> {

      const url =  `${this.baseUrl}/professor/${id}`

      return this.http.get<Professor>(url).pipe(take(1));
    }
}