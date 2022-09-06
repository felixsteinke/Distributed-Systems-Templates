import {Component, OnInit} from '@angular/core';
import {ProductModel} from "../../connector/model/product-model";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-product-mod-dialog',
  templateUrl: './product-mod-dialog.component.html',
  styleUrls: ['./product-mod-dialog.component.scss']
})
export class ProductModDialogComponent implements OnInit {

  product: ProductModel = {};

  constructor(private dialogRef: MatDialogRef<ProductModDialogComponent>) {
  }

  ngOnInit(): void {
  }

  public cancel() {
    this.dialogRef.close({event: ProductDialogEvent.CANCEL, product: {}})
  }

  public send() {
    this.dialogRef.close({event: ProductDialogEvent.CREATE, product: this.product})
  }
}

export enum ProductDialogEvent {
  CANCEL,
  CREATE
}
