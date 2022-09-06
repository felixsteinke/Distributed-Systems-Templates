import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CartItemModel} from "./model/cart-item-model";

const API_CART_URL = environment.backendUrl + '/cart'
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient: HttpClient) {
  }

  public getCart(): Observable<CartItemModel[]> {
    const url = API_CART_URL + '/';
    return this.httpClient.get<CartItemModel[]>(url, HTTP_OPTIONS)
  }

  public addToCart(productNr: number | undefined): Observable<void> {
    const url = API_CART_URL + '/add/' + productNr;
    return this.httpClient.put <void>(url, HTTP_OPTIONS)
  }

  public checkoutCart(): Observable<void> {
    const url = API_CART_URL + '/checkout';
    return this.httpClient.post<void>(url, HTTP_OPTIONS)
  }

  public resetCart(): Observable<void> {
    const url = API_CART_URL + '/reset';
    return this.httpClient.delete<void>(url, HTTP_OPTIONS)
  }
}
