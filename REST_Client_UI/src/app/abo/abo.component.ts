import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {AboModel} from "../connector/model/abo-model";
import {AboService} from "../connector/abo.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {openSnackBar, removeItem} from "../utility/utils";

@Component({
  selector: 'app-abo',
  templateUrl: './abo.component.html',
  styleUrls: ['./abo.component.scss']
})
export class AboComponent implements OnInit, AfterViewInit {

  abos: AboModel[] = [];
  table = {
    columns: TABLE_ABO_COLUMN,
    displayedColumns: [
      TABLE_ABO_COLUMN.ID,
      TABLE_ABO_COLUMN.PRODUCT_NR,
      TABLE_ABO_COLUMN.PRODUCT_NAME,
      TABLE_ABO_COLUMN.PRICE,
      TABLE_ABO_COLUMN.PAYED,
      TABLE_ABO_COLUMN.ACTIONS
    ],
    dataSource: new MatTableDataSource<AboModel>(),
  }

  @ViewChild(MatSort) sort: MatSort | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(private aboService: AboService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.aboService.getAllAbos().subscribe((response) => {
      this.abos = response;
      this.table.dataSource.data = this.abos;
    });
  }

  ngAfterViewInit(): void {
    if (this.sort)
      this.table.dataSource.sort = this.sort;
    if (this.paginator)
      this.table.dataSource.paginator = this.paginator;
  }

  public cancelAbo(abo: AboModel): void {
    this.aboService.cancelAbo(abo.id).subscribe(() => {
      openSnackBar(this.snackBar, 'Successfully canceled abo.');
      this.abos = removeItem(this.abos, abo);
      this.table.dataSource.data = this.abos;
    });
  }
}

export enum TABLE_ABO_COLUMN {
  ID = 'id',
  PRODUCT_NR = 'nr',
  PRODUCT_NAME = 'name',
  PRICE = 'price',
  PAYED = 'cart',
  ACTIONS = 'actions'
}
