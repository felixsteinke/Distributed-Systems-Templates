import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AboModel} from "./model/abo-model";

const API_ABO_URL = environment.backendUrl + '/abo'
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AboService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllAbos(): Observable<AboModel[]> {
    const url = API_ABO_URL + '/';
    return this.httpClient.get<AboModel[]>(url, HTTP_OPTIONS)
  }

  public cancelAbo(aboId: number | undefined): Observable<void> {
    const url = API_ABO_URL + '/delete/' + aboId;
    return this.httpClient.delete<void>(url, HTTP_OPTIONS)
  }
}
