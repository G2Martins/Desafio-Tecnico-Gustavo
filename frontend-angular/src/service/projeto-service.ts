import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cidade } from '@domain/cidade';
import { Observable } from 'rxjs';
import { environment } from 'src/app/environments/environment';

@Injectable()
export class ProjetoService {
  private apiUrl = `${environment.apiUrl}${environment.urlCidades}`;

  constructor(private http: HttpClient) {}

  //------------------------------------------------
  /** Recupera a lista de cidades */
  //------------------------------------------------
  pesquisarCidades(): Observable<Cidade[]> {
    return this.http.get<Cidade[]>(this.apiUrl);
  }

  //------------------------------------------------
  /** Exclui a cidade informada */
  //------------------------------------------------
  excluir(cidade: Cidade): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${cidade.id}`);
  }

  //------------------------------------------------
  /** Salva a cidade informada */
  //------------------------------------------------
  salvar(cidade: Cidade): Observable<any> {
    if (cidade.id) {
      return this.http.put(this.apiUrl, cidade); // Atualiza a cidade existente
    }
    return this.http.post(this.apiUrl, cidade); // Cria uma nova cidade
  }
}
