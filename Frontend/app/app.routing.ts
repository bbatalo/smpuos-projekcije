import {ModuleWithProviders } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './authentication/login/login.component';
import {SignupComponent} from './authentication/signup/signup.component';
import {AuthGuard} from './auth.guard';

export const routes : Routes = [
  //{ path: '', canActivate: [AuthGuard], redirectTo: 'pages', pathMatch: 'full' }, for jwt
  { path: '', redirectTo: 'pages', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: SignupComponent},
  { path: '**', redirectTo: 'pages/dashboard' }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });
