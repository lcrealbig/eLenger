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
  templateUrl: "./main-layout.html",
})
export class MainLayoutComponent {
  events: AppEvent[] = [
    {
      id: 1,
      title: 'Personal Challange',
      date: '2025-06-15',
      location: 'Warszawa',
      description: 'Piotr M. has challanged you!',
    },
    {
      id: 2,
      title: 'Group Challange',
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