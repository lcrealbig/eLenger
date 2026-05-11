import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="bg-black text-white px-8 py-4 text-center text-sm tracking-wide">
      © 2025 EventApp. Wszelkie prawa zastrzeżone.
    </footer>
  `,
})
export class FooterComponent {}