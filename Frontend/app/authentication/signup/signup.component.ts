import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {AuthService} from '../auth.service';
import {User} from '../../model/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['../common/auth.component.css']
})
export class SignupComponent {

  public form: FormGroup;
  public username : AbstractControl;
  public password : AbstractControl;
  public firstName : AbstractControl;
  public lastName: AbstractControl;
  public email: AbstractControl;

  constructor(fb: FormBuilder, private router: Router, private authService: AuthService, private toastr: ToastrService) {
    this.form = fb.group({
      'username': ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      'email': ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      'firstName': ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      'lastName': ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });


    this.username = this.form.controls['username'];
    this.password = this.form.controls['password'];
    this.email = this.form.controls['email'];
    this.firstName = this.form.controls['firstName'];
    this.lastName = this.form.controls['lastName'];
  }

  public onSignUp() {
    this.authService.onSignUp(new User(this.username.value,this.password.value,this.firstName.value,this.lastName.value,this.email.value))
      .subscribe(data => {
        if(data)
          this.toastr.success('You have sucessfully sign up!', 'Congratulations!')
        else
          this.toastr.warning('Your username is already in use', 'Warning!');

      });
  }

  public onLogin() {
    this.router.navigateByUrl('/login');
  }


}
