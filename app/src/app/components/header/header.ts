import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  template: `
    <header class="bg-black text-white px-8 py-4 flex items-center justify-between shadow-lg">
      <span class="text-2xl font-bold tracking-widest uppercase">EventApp</span>
      <nav class="flex gap-6 text-sm font-medium">
        <a href="#" class="hover:text-red-400 transition-colors">Home</a>
        <a href="#" class="hover:text-red-400 transition-colors">Events</a>
        <a href="#" class="hover:text-red-400 transition-colors">About</a>
      </nav>
    </header>
  `,
})
export class HeaderComponent {}