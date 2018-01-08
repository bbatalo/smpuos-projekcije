import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {PagesComponent} from './pages.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuthGuard} from '../auth.guard';
import {CinemaListComponent} from './cinemas/cinema-list.component';

export const routes: Routes = [
  {
    //path: 'pages', component: PagesComponent, canActivate: [AuthGuard], for jwt
    path: 'pages', component: PagesComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: CinemaListComponent },
      /** nova ruta ** /
       *
       * { path: '', component: ImeKomponente}
       */
    ]
  }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
