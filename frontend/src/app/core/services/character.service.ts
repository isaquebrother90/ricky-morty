import { HttpParams, HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  getPageableAndSorting(pageNumber: number, pageSize: number, nome: any, cpf: any) {
    throw new Error('Method not implemented.');
  }
  protected baseUrl = 'http://localhost:8080/characters';  // Essa Url tem que ser reinstanciada, contato com o backend
   //protected baseUrl = 'https://buscacharacters-java.herokuapp.com';  // Essa Url tem que ser reinstanciada, contato com o backend

  constructor(
    protected api: HttpClient,
  ) { } // Injeção de dependência pelo construtor

  protected httpOptions = { // Configurações de cabeçalho pra envio do corpo/body das requisições
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  public getPageable(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('page', pageNumber.toString());
    params = params.append('size', pageSize.toString());

    return this.api.get(`${this.baseUrl}/pageable`, { params });
  }

  public getAll(): Observable<any> {// O mesmo que Optional do java, mas nesse caso fará o tratamento das excessões. Monitora o retorno para gerar duas saídas. Função de callback para retornar sucesso. A outra saída é para pegar retornos mal sucedidos.
    return this.api.get(this.baseUrl);
  }

}

   //Requisições com cliente Http, trazendo as informações do Back-end
