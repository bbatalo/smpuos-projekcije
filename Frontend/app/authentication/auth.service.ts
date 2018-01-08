import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {User} from '../model/user.model';
import {tokenNotExpired} from 'angular2-jwt';

@Injectable()
export class AuthService {
  constructor(private http: HttpClient){}

  private temp: User[] = [];
  /** without serverside **/
  setData() {
    //this.temp = new User[];
    this.temp.push(new User('nemanja','nemanja'));
  }


  /**example for backend side
   * signup(user: User) {
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post('http://localhost:4200/api/itengine/signup', body, {headers: headers})
      .map((response: Response) => response.json())
      });
  }
   **/




  onLogin(user: User): Observable<boolean> {
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': 'application/json'});
    //this.http.post('localhost:8080/api/prvaklasa/login', body , headers: {headers});
    console.log(user.username);
    let temp = this.temp.filter( findUser => {
      return findUser.username == user.username && findUser.password == user.password
      }
    );

    const success = temp.length != 0 ? true : false;

    return of(success);
  }

  onSignUp(user: User): Observable<any> {
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': 'application/json'});
    //this.http.post

    let uniqueUsername = false;

    let temp = this.temp.filter( findUser =>
        findUser.username == user.username
    );

    temp ? uniqueUsername = true :  uniqueUsername = false;
    temp.length == 0 ? this.temp.push(user) : console.log('already exist');

    return of(uniqueUsername);
  }

  logout() {
    localStorage.clear();
    this.http.get('http://localhost:4200/api/itengine/logout');
  }

  isLoggedIn() {
    return tokenNotExpired();
  }

}
