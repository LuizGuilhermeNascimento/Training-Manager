import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, take } from 'rxjs';
import { Acompanhamento, AcompanhamentoJson } from 'src/app/models/acompanhamento.model';

@Injectable({
  providedIn: 'root'
})
export class AcompanhamentoService {

  constructor(private http: HttpClient) {}
    
    baseUrl = 'http://localhost:8080';

    getAcompanhamentoDoAluno(idAluno: string): Observable<Acompanhamento> {

      const url =  `${this.baseUrl}/acompanhamento/aluno/${idAluno}`

      return this.http.get<Acompanhamento>(url).pipe(take(1));
    }

    getAcompanhamentosDoProfessor(idProfessor: string): Observable<Acompanhamento[]> {

        const url =  `${this.baseUrl}/acompanhamento/professor/${idProfessor}`
  
        return this.http.get<Acompanhamento[]>(url).pipe(take(1));
    }

    deleteAcompanhamento(idAcompanhamento: string): Observable<any> {

        const url =  `${this.baseUrl}/acompanhamento/${idAcompanhamento}`
    
        return this.http.delete(url).pipe(take(1));
    }

    createAcompanhamento(acompanhamento: AcompanhamentoJson): Observable<any> {

        const url =  `${this.baseUrl}/acompanhamento`
    
        return this.http.post(url, acompanhamento).pipe(take(1));
      }
}