import {MatSnackBar} from "@angular/material/snack-bar";

export function openSnackBar(snackBar: MatSnackBar, message: string): void {
  snackBar.open(message, 'OK', {
    duration: 3000,
    panelClass: ['primary-snack-bar']
  });
}

export function removeItem<T>(list: T[], object: T): T[] {
  const index = list.indexOf(object);
  if (index > -1) {
    list.splice(index, 1);
  }
  return list;
}
