import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginguardService {

  constructor(private loginService:LoginService, private router: Router) { }
  canActivate(): boolean{
    const sessionId = localStorage.getItem('sessionId');
    if(sessionId) {
      this.router.navigate(['/home-page']);
      return false;
    }
    return true;
  }
}
