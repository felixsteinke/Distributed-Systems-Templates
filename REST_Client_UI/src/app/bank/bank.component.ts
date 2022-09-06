import {Component, OnDestroy, OnInit} from '@angular/core';
import {PaymentService} from "../connector/payment.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {openSnackBar} from "../utility/utils";

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.scss']
})
export class BankComponent implements OnInit, OnDestroy {
  bankValue: number = 0;
  displayedBankValue: number = 0;
  progress: number = 0;

  refreshMs: number = 10000;
  scheduler: any;

  constructor(private paymentService: PaymentService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.paymentService.getBank().subscribe((response) => {
      this.bankValue = response;
      this.displayedBankValue = this.bankValue;
    })
    this.animateProgress();

    this.scheduler = setInterval(() => {
      openSnackBar(this.snackBar, 'Load data.');
      this.progress = 0;
      this.animateProgress();
      this.paymentService.getBank().subscribe((response) => {
        if (response != this.bankValue) {
          this.bankValue = response;
          this.animateBank();
        }
      });
    }, this.refreshMs);
  }

  ngOnDestroy(): void {
    clearInterval(this.scheduler);
  }

  private animateBank(): void {
    const range = this.bankValue - this.displayedBankValue;
    const delay = Math.abs(Math.floor(this.refreshMs / (range * 1.1)));
    this.incrementDisplayedBank(this.bankValue, range, delay);
  }

  private incrementDisplayedBank(end: number, range: number, delay: number): void {
    this.displayedBankValue += 1;
    const timer = setTimeout(() => this.incrementDisplayedBank(end, range, delay), delay);
    if (this.displayedBankValue >= end) {
      this.displayedBankValue = end;
      clearTimeout(timer);
    }
  }

  private animateProgress(): void {
    const end = 100;
    const range = end - this.progress;
    const delay = Math.abs(Math.floor(this.refreshMs / (range * 1.1)));
    this.incrementProgress(end, range, delay);
  }

  private incrementProgress(end: number, range: number, delay: number) {
    this.progress += 1;
    const timer = setTimeout(() => this.incrementProgress(end, range, delay), delay);
    if (this.progress >= end) {
      this.progress = end;
      clearTimeout(timer);
    }
  }
}
