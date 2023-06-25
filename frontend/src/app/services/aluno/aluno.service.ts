import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, take } from 'rxjs';
import { UserLogin } from '../../models/login.models';
import { UserJson } from '../../models/login.models';
import { Aluno, ListAlunos } from 'src/app/models/aluno.model';
import { Treino } from 'src/app/models/treino.model';

@Injectable({
  providedIn: 'root',
})
export class AlunoService {
  constructor(private http: HttpClient) {}

  baseUrl = 'http://localhost:8080';

  getAlunoPorId(id: string): Observable<Aluno> {
    const url = `${this.baseUrl}/aluno/${id}`;

      return this.http.get<Aluno>(url).pipe(take(1));
    }

  getTodosAlunos(): Observable<ListAlunos> {
    
    const url = `${this.baseUrl}/aluno/`
    
    return this.http.get<ListAlunos>(url).pipe(take(1));
  }
}
