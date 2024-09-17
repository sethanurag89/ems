import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  rememberMe: boolean = false;
  email: string= '';
  error: string = '';
  isLogIn: boolean=false;
  login: FormGroup = new FormGroup({ email: new FormControl("", Validators.email), password: new FormControl("", Validators.required) });

  constructor(private route: ActivatedRoute, 
              private router: Router, 
              private loginService: LoginService,
              private formBuilder: FormBuilder,
              private toastr: ToastrService) {
    this.login = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const rememberedEmail = localStorage.getItem('rememberedEmail');
    const rememberedPassword = localStorage.getItem('rememberedPassword');
    if (rememberedEmail && rememberedPassword) {
      this.login.patchValue({
        email: rememberedEmail,
        password: rememberedPassword
      });
      this.rememberMe = true;
    }
  }
  
  onSubmit() {
    const email = this.login.value.email;
    const password = this.login.value.password;
    if (this.rememberMe) {
      localStorage.setItem('rememberedEmail', email);
      localStorage.setItem('rememberedPassword', password);
    } else {
      localStorage.removeItem('rememberedEmail');
      localStorage.removeItem('rememberedPassword');
    }
    this.loginService.login(this.login.value).subscribe((res) => {
      this.router.navigate(['/home-page']),() => {
        this.toastr.error('Wrong credentials');
				this.error = 'Either invalid credentials or something went wrong';
			}
    });
    this.login.reset();
  }

}
