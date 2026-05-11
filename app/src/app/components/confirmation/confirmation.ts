// src/app/auth/confirm/confirm.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // <-- Dodaj CommonModule
import { ActivatedRoute, Router, RouterLink } from '@angular/router'; // <-- Jeśli używasz routerLink
import { AuthService } from '../../core/service/auth.service';

@Component({
  selector: 'app-confirm',
  standalone: true, // <-- Musi być true
  imports: [
    CommonModule, // <-- Potrzebny dla ngClass, *ngIf itp.
    RouterLink, // <-- Potrzebny, jeśli używasz routerLink (np. w button)
  ],
  templateUrl: './confirmation.html',
  styleUrls: ['./confirmation.css']
})
export class ConfirmComponent implements OnInit {
  message: string = '';
  isSuccess: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    const token = this.route.snapshot.paramMap.get('token');
    if (token) {
      this.authService.confirmEmail(token).subscribe({
        next: () => {
          this.message = 'Email został potwierdzony! Możesz się teraz zalogować.';
          this.isSuccess = true;
        },
        error: (err) => {
          this.message = err.error || 'Potwierdzenie nieudane. Token może być nieprawidłowy lub wygasł.';
          this.isSuccess = false;
        }
      });
    } else {
      this.message = 'Brak tokenu potwierdzenia.';
      this.isSuccess = false;
    }
  }
}