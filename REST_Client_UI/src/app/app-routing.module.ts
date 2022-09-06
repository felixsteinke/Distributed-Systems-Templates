import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {CartComponent} from "./cart/cart.component";
import {BankComponent} from "./bank/bank.component";
import {AboComponent} from "./abo/abo.component";

export enum AppRoute {
  ABO = 'abo',
  CART = 'cart',
  BANK = 'bank',
  PRODUCT = 'product'
}

const routes: Routes = [
  {path: '', redirectTo: AppRoute.PRODUCT, pathMatch: 'full'},
  {path: AppRoute.ABO, component: AboComponent},
  {path: AppRoute.BANK, component: BankComponent},
  {path: AppRoute.CART, component: CartComponent},
  {path: AppRoute.PRODUCT, component: ProductComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

