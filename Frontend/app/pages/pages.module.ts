import {PagesComponent} from './pages.component';
import {NgModule} from '@angular/core';
import {routing} from './pages.routing';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CommonModule} from '@angular/common';
import {CinemaListComponent} from './cinemas/cinema-list.component';
import {DataTableModule} from 'angular2-datatable';
import {CinemaService} from './cinema.service';

@NgModule({
  imports: [CommonModule, routing, DataTableModule],
  declarations: [PagesComponent,
    DashboardComponent,
    CinemaListComponent],
  providers:[ CinemaService]
})
export class PagesModule {}
