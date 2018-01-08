import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CinemaService} from '../cinema.service';

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html'
})
export class CinemaListComponent implements OnInit {

  cinemas = [];

  constructor(private cinemaService: CinemaService) {}

  ngOnInit() {
    this.cinemaService.setData();
    /** call this for backend function **/
    this.cinemaService.getCinemas()
      .subscribe(
        data => {
          this.cinemas = data;
        }
      );
  }
}
