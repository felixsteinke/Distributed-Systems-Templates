import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductModel} from "./model/product-model";

const API_PRODUCT_URL = environment.backendUrl + '/product'
const HTTP_OPTIONS = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllProducts(): Observable<ProductModel[]> {
    const url = API_PRODUCT_URL + '/';
    return this.httpClient.get<ProductModel[]>(url, HTTP_OPTIONS)
  }

  public getProduct(productNr: number): Observable<ProductModel> {
    const url = API_PRODUCT_URL + '/get/' + productNr;
    return this.httpClient.get<ProductModel>(url, HTTP_OPTIONS)
  }

  public addProduct(product: ProductModel): Observable<ProductModel> {
    const url = API_PRODUCT_URL + '/insert';
    return this.httpClient.post<ProductModel>(url, product, HTTP_OPTIONS)
  }

  public deleteProduct(productNr: number | undefined): Observable<ProductModel> {
    const url = API_PRODUCT_URL + '/delete/' + productNr;
    return this.httpClient.delete<ProductModel>(url, HTTP_OPTIONS)
  }
}
