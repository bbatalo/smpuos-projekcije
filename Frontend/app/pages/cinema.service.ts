import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs/observable/of';

@Injectable()
export class CinemaService {
  constructor(private http: HttpClient){}

  private tempCinemas = [];

  setData() {
    const Cinema1 = {
      id : '1',
      name : 'Novi Sad Centar',
      address: 'Kod bazara',
      rate: '10'
    }

    const Cinema2 = {
      id : '2',
      name : 'Novi Sad Big',
      address: 'Kod biga',
      rate: '3'
    }
  console.log('aa');
    this.tempCinemas.push(Cinema1);
    this.tempCinemas.push(Cinema2);
  console.log(this.tempCinemas);
  }


  getCinemas(): Observable<any[]> {
    console.log('krenulloooo');
    return of(this.tempCinemas);
  }




}
