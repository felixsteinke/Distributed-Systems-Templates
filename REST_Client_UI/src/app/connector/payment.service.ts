import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const API_PAYMENT_URL = environment.backendUrl + '/payment'
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private httpClient: HttpClient) {
  }

  public getBank(): Observable<number> {
    const url = API_PAYMENT_URL + '/';
    return this.httpClient.get<number>(url, HTTP_OPTIONS)
  }
}
