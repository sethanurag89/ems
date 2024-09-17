import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService{

  constructor(private loginService:LoginService, private router: Router) { }
  canActivate(): boolean{
    const isAuthenticated = localStorage.getItem("sessionId");
    
    if (!isAuthenticated) {
      // User is not authenticated, redirect to the login page
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
