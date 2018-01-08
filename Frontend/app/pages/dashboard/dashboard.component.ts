import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../authentication/auth.service';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private router: Router,private authService:AuthService, private toastrService: ToastrService) {}


  onLogout(){
    this.authService.logout();
    this.router.navigateByUrl("/login");
  }

  checkRole() {
    let role = localStorage.getItem('role');
    if( role == 'ADMIN')
      return true;
    return false;
  }

}
