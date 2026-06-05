import { Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register';
import { LoginComponent } from './components/login/login';
import { MainLayoutComponent } from './components/main-layout/main-layout';
import { authGuard } from './core/guards/auth.guard';
import { ConfirmComponent } from './components/confirmation/confirmation';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'confirm/:token', component: ConfirmComponent },

  {
  path: 'activities',
  component: MainLayoutComponent,
  canActivate: [authGuard]
},

  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: '**', redirectTo: '/login' },
];