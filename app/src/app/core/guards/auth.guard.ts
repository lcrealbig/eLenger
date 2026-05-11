import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const user = localStorage.getItem('user');

  if (user) {
    return true; // Zalogowany → zezwól na dostęp
  } else {
    router.navigate(['/login']); // Niezalogowany → przekieruj na /login
    return false;
  }
};