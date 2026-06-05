import { Component } from '@angular/core';
import { MainLayoutComponent } from './components/main-layout/main-layout';
import { LoginComponent } from './components/login/login';
import { RouterOutlet } from '@angular/router'; // <-- Import RouterOutlet

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: `./app.html`,
})
export class AppComponent {}
