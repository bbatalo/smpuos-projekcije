import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../auth.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {User} from '../../model/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../common/auth.component.css']
})
export class LoginComponent implements OnInit{

    public form: FormGroup;
    public username: AbstractControl;
    public password: AbstractControl;

    constructor(private fb: FormBuilder, private router: Router, private authService: AuthService, private toastr: ToastrService ){
      this.form = fb.group({
        'username': ['',Validators.compose([Validators.required, Validators.minLength(6)])],
        'password': ['',Validators.compose([Validators.required, Validators.minLength(6)])]
      });

      this.username = this.form.controls['username'];
      this.password = this.form.controls['password'];

    }

    ngOnInit() {
      this.authService.setData();
    }

    public onLogin() {
      this.authService.onLogin(new User(this.username.value,this.password.value))
        .subscribe(data => {
          if (!data)
            this.toastr.warning('You have failed to log in', 'Sorry, ' + this.username.value);
          else {
            console.log('User ' + this.username.value + ' has successfully loged in');
            this.toastr.info('You have succesfully loged in', 'Hello, ' + this.username.value);
            this.router.navigateByUrl('pages');
            /** token u localstorage **/
            //localStorage.setItem('sessionId',data);
            // if(this.username.value == 'nemanja')
            //   localStorage.setItem('role', "ADMIN");
            // else
            //   localStorage.setItem('role', "USER");
          }
        });
    }


    public onSignUp() {
      this.router.navigateByUrl('/signup');
    }



}
