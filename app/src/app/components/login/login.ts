import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // <-- Import ReactiveFormsModule
import { CommonModule } from '@angular/common'; // <-- Import CommonModule (dla *ngIf)
import { Router, RouterLink } from '@angular/router'; // <-- Jeśli używasz routerLink
import { AuthService } from '../../core/service/auth.service';
import { LoginRequest } from '../../core/model/login-request.model';

@Component({
  selector: 'app-login',
  standalone: true, 
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
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
    
    const loginRequest: LoginRequest = {
      email: this.loginForm.get('email')?.value, 
      password: this.loginForm.get('password')?.value
    };
    
    this.authService.login(loginRequest);
  }
}
}