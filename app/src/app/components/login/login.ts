// src/app/auth/login/login.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // <-- Import ReactiveFormsModule
import { CommonModule } from '@angular/common'; // <-- Import CommonModule (dla *ngIf)
import { Router, RouterLink } from '@angular/router'; // <-- Jeśli używasz routerLink

@Component({
  selector: 'app-login',
  standalone: true, // <-- Musi być true
  imports: [
    CommonModule, // <-- Potrzebny dla *ngIf, *ngFor
    ReactiveFormsModule, // <-- Potrzebny dla formGroup, formControlName
    RouterLink, // <-- Potrzebny, jeśli używasz routerLink (np. w linku do rejestracji)
  ],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    console.log("LOGIN works");
    
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log('Logowanie:', this.loginForm.value);
      // Tutaj dodaj logikę logowania (np. wywołanie AuthService)
    }
  }
}