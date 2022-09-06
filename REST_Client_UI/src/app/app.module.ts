import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MaterialModule} from "../material.module";
import {ProductComponent} from './product/product.component';
import {CartComponent} from './cart/cart.component';
import {BankComponent} from './bank/bank.component';
import {AboComponent} from './abo/abo.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProductModDialogComponent} from './product/product-mod-dialog/product-mod-dialog.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    CartComponent,
    BankComponent,
    AboComponent,
    ProductModDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FlexLayoutModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
