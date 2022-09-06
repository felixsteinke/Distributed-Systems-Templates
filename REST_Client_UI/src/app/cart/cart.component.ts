import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {CartService} from "../connector/cart.service";
import {AppRoute} from "../app-routing.module";
import {CartItemModel} from "../connector/model/cart-item-model";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {openSnackBar} from "../utility/utils";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit, AfterViewInit {

  cart: CartItemModel[] = [];
  table = {
    columns: TABLE_CART_COLUMN,
    displayedColumns: [
      TABLE_CART_COLUMN.NR,
      TABLE_CART_COLUMN.NAME,
      TABLE_CART_COLUMN.PRICE,
      TABLE_CART_COLUMN.COUNT
    ],
    dataSource: new MatTableDataSource<CartItemModel>(),
  }

  @ViewChild(MatSort) sort: MatSort | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(private cartService: CartService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.cartService.getCart().subscribe((response) => {
      this.cart = response;
      this.table.dataSource.data = this.cart;
    });
  }

  ngAfterViewInit(): void {
    if (this.sort)
      this.table.dataSource.sort = this.sort;
    if (this.paginator)
      this.table.dataSource.paginator = this.paginator;
  }

  public resetCart(): void {
    this.cartService.resetCart().subscribe(() => {
      openSnackBar(this.snackBar, 'Successfully reset cart.');
      this.router.navigate(['/' + AppRoute.PRODUCT]);
    });
  }

  public checkoutCart(): void {
    this.cartService.checkoutCart().subscribe(() => {
      openSnackBar(this.snackBar, 'Successfully checked out cart.');
      this.router.navigate(['/' + AppRoute.ABO]);
    });
  }
}

export enum TABLE_CART_COLUMN {
  NR = 'nr',
  NAME = 'name',
  PRICE = 'price',
  COUNT = 'count'
}
