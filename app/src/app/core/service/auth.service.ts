import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { LoginRequest } from '../model/login-request.model';
import { RegisterRequest } from '../model/register-request.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  register(registerRequest: RegisterRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, registerRequest);
  }

  login(credentials: LoginRequest) {
  return this.http.post(this.apiUrl + "/login", credentials, { withCredentials: true });
}

  confirmEmail(token: string): Observable<User> {
    return this.http.get<User>(`http://localhost:8080/api/confirm/${token}`);
  }
}
