import {Component} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {AppRoute} from "./app-routing.module";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Demo Shop';
  routes = AppRoute;
  activeRoute: string = AppRoute.PRODUCT;

  constructor(private router: Router) {
    const availableRoutes = Object.values(this.routes);
    router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        availableRoutes.map(route => {
          if (router.url.includes(route)) {
            this.activeRoute = route;
          }
        });
      }
    });
  }

}
