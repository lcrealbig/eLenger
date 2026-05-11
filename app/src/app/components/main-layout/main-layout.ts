import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header';
import { FooterComponent } from '../footer/footer';

interface AppEvent {
  id: number;
  title: string;
  date: string;
  location: string;
  description: string;
}

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  template: `
    <div class="min-h-screen flex flex-col bg-red-600">
      <app-header />

      <main class="flex-1 px-8 py-10">
        <h1 class="text-white text-4xl font-extrabold mb-8 uppercase tracking-widest text-center drop-shadow">
          Nadchodzące Events
        </h1>

        <div class="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 max-w-6xl mx-auto">
          <div
            *ngFor="let event of events"
            class="bg-black text-white rounded-xl p-6 shadow-xl hover:scale-105 transition-transform duration-200"
          >
            <div class="text-red-400 text-xs font-bold uppercase tracking-widest mb-1">
              {{ event.date }}
            </div>
            <h2 class="text-xl font-bold mb-1">{{ event.title }}</h2>
            <p class="text-gray-400 text-sm mb-3">📍 {{ event.location }}</p>
            <p class="text-gray-300 text-sm">{{ event.description }}</p>
          </div>
        </div>
      </main>

      <app-footer />
    </div>
  `,
})
export class MainLayoutComponent {
  events: AppEvent[] = [
    {
      id: 1,
      title: 'Angular Summit 2025',
      date: '2025-06-15',
      location: 'Warszawa',
      description: 'Największa konferencja Angular w Polsce.',
    },
    {
      id: 2,
      title: 'Frontend Meetup',
      date: '2025-07-01',
      location: 'Kraków',
      description: 'Spotkanie dla frontendowców — networking i prelekcje.',
    },
    {
      id: 3,
      title: 'TypeScript Workshop',
      date: '2025-07-20',
      location: 'Online',
      description: 'Intensywny warsztat z zaawansowanego TypeScripta.',
    },
  ];
}