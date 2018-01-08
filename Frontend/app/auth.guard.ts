import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {AuthService} from './authentication/auth.service';
import {tokenNotExpired} from 'angular2-jwt';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private toastrService: ToastrService, private authService: AuthService){}

  canActivate() {
    if(localStorage.getItem('token') && localStorage.getItem('user') && tokenNotExpired)
      return true;

    if(localStorage.getItem('token') && localStorage.getItem('user') && !tokenNotExpired()){
      this.toastrService.warning('Your token expired','Token expired');
      this.authService.logout();
      this.router.navigate(['/login']);
      return false;
    }

    this.toastrService.info('Make sure that you are logged in','Hint');
    this.router.navigate(['/login']);
    return false;
  }
}
