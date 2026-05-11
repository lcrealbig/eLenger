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

  // Chroniona trasa (tylko dla zalogowanych)
  {
    path: 'activities', // lub inna nazwa, np. 'dashboard'
    component: MainLayoutComponent,
    canActivate: [authGuard], // Ochrona przez AuthGuard
  },

  // Domyślna trasa: przekieruj na /login
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Przekierowanie dla nieznanych tras
  { path: '**', redirectTo: '/login' },
];