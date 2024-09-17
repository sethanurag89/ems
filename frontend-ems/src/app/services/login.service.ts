import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import {  Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  base_url="http://localhost:8080";
  private isAuthen: boolean = false;

  constructor(private http: HttpClient,
              private router:Router) {}

  login(payLoad:{email:string, password:string}): Observable<any>{
    return this.http.post<any>(this.base_url + "/ems/login",payLoad).pipe(map((res)=>{
      localStorage.setItem('user', payLoad.email);
      localStorage.setItem('sessionId', res.sessionId);
      this.isAuthen = true;
    }));
  }

  logout(){
    localStorage.clear();
    this.isAuthen = false;
    this.router.navigateByUrl('/login');

  }

  isAuthenticated(): boolean{
    return this.isAuthen;
  }

  isUserLogIn(){
    return localStorage.getItem('sessionId')!==null;
  }
  
  geSessionId(){
    let id = localStorage.getItem('sessionId') as string;
    return id;
  }
}
