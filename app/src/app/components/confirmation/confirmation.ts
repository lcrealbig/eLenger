import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
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
