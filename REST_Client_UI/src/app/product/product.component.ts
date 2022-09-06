import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ProductService} from "../connector/product.service";
import {ProductModel} from "../connector/model/product-model";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {ProductDialogEvent, ProductModDialogComponent} from "./product-mod-dialog/product-mod-dialog.component";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {CartService} from "../connector/cart.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {openSnackBar, removeItem} from "../utility/utils";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit, AfterViewInit {

  products: ProductModel[] = [];
  table = {
    columns: TABLE_PRODUCT_COLUMN,
    displayedColumns: [
      TABLE_PRODUCT_COLUMN.NR,
      TABLE_PRODUCT_COLUMN.NAME,
      TABLE_PRODUCT_COLUMN.PRICE,
      TABLE_PRODUCT_COLUMN.CART,
      TABLE_PRODUCT_COLUMN.ACTIONS
    ],
    dataSource: new MatTableDataSource<ProductModel>(),
  }
  dialogConfig: MatDialogConfig = {
    width: '400px'
  }

  @ViewChild(MatSort) sort: MatSort | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(private productService: ProductService,
              private cartService: CartService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((response) => {
      this.products = response;
      this.table.dataSource.data = this.products;
    });
  }

  ngAfterViewInit(): void {
    if (this.sort)
      this.table.dataSource.sort = this.sort;
    if (this.paginator)
      this.table.dataSource.paginator = this.paginator;
  }

  public addToCart(product: ProductModel): void {
    this.cartService.addToCart(product.nr).subscribe(() => {
      openSnackBar(this.snackBar, 'Successfully added to cart.');
    });
  }

  public addProduct(): void {
    const dialogRef = this.dialog.open(ProductModDialogComponent, this.dialogConfig);
    dialogRef.afterClosed().subscribe((data) => {
      if (data.event === ProductDialogEvent.CREATE) {
        const product: ProductModel = data.product;
        this.productService.addProduct(product).subscribe(() => {
          openSnackBar(this.snackBar, 'Successfully added product.');
          this.products.push(product);
          this.table.dataSource.data = this.products;
        });
      }
    });
  }

  public deleteProduct(product: ProductModel): void {
    this.productService.deleteProduct(product.nr).subscribe(() => {
      openSnackBar(this.snackBar, 'Successfully deleted product.');
      this.products = removeItem(this.products, product);
      this.table.dataSource.data = this.products;
    });
  }
}

export enum TABLE_PRODUCT_COLUMN {
  NR = 'nr',
  NAME = 'name',
  PRICE = 'price',
  CART = 'cart',
  ACTIONS = 'actions'
}
