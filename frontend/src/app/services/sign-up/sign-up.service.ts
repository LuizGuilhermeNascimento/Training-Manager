import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { UserJson } from 'src/app/models/login.models';
import { AlunoSign, ProfessorSign } from 'src/app/models/sign-up.model';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080';

  signUpProfessor(professor: ProfessorSign): Observable<UserJson> {

    const url =  `${this.baseUrl}/professor/sign-up`

    return this.http.post<UserJson>(url, professor).pipe(take(1));
  }

  signUpAluno(aluno: AlunoSign): Observable<UserJson> {

    const url =  `${this.baseUrl}/aluno/sign-up`

    return this.http.post<UserJson>(url, aluno).pipe(take(1));
  }

  deleteProfessor(id: string): Observable<any> {

    const url =  `${this.baseUrl}/professor/${id}`

    return this.http.delete(url).pipe(take(1));
  }

  deleteAluno(id: string): Observable<any> {

    const url =  `${this.baseUrl}/aluno/${id}`

    return this.http.delete(url).pipe(take(1));
  }
}
