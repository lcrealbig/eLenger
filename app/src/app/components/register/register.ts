import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { RegisterRequest } from '../../core/models/register-request.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const registerRequest: RegisterRequest = this.registerForm.value;
      this.authService.register(registerRequest).subscribe({
        next: (user) => {
          this.successMessage = 'Rejestracja udana! Sprawdź swoją skrzynkę mailową (mock).';
          this.errorMessage = '';
          // Na razie nie przekierowujemy, bo mockujemy potwierdzenie
        },
        error: (err) => {
          this.errorMessage = err.error || 'Rejestracja nieudana';
          this.successMessage = '';
        }
      });
    }
  }
}
