import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { catchError, map, of } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  
  // Najpierw sprawdź flagę
  if (localStorage.getItem('isLoggedIn') === 'true') {
    return true;
  }
  
  // Potem sprawdź przez API
  return inject(HttpClient).get('/api/me', { withCredentials: true }).pipe(
    map(() => {
      localStorage.setItem('isLoggedIn', 'true');
      return true;
    }),
    catchError(() => {
      router.navigate(['/login']);
      return of(false);
    })
  );
};