import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';

import { routes } from './app.routes';

// To jest TWOJA konfiguracja. To tutaj "rejestrujesz" wszystko, co wcześniej było w AppModule.
export const appConfig: ApplicationConfig = {
  providers: [
    // 1. Odpowiednik BrowserModule + Routing
    provideRouter(routes),

    // 2. Odpowiednik HttpClientModule (Z withFetch() dla lepszej wydajności)
    provideHttpClient(
      withFetch(),      // Nowa, lepsza metoda fetch
      // Jeśli masz Interceptory, dodajesz je tutaj:
      // withInterceptors([myInterceptor])
    ),

    // 3. (OPCJONALNIE) Jeśli używasz Animacji, dodajesz:
    // provideAnimations()

    // 4. Tutaj możesz dodawać inne PROVIDERY (np. dla usług globalnych)
  ]
};
