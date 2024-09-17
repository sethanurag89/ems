import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { HomePageComponent } from './home-page/home-page.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { LoginService } from './services/login.service';
import { SidebarDirective } from './sidebar.directive';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { PersonalDetailsComponent } from './employee-details/personal-details/personal-details.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { UpdateEmployeeComponent } from './add-employee/update-employee/update-employee.component';
import { MainPageComponent } from './home-page/main-page/main-page.component';
import { PaginatorComponent } from './employee-list/paginator/paginator.component';
import { UpdatePasswordComponent } from './employee-details/personal-details/update-password/update-password.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    SidebarDirective,
    HeaderComponent,
    SidebarComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent,
    PersonalDetailsComponent,
    AddEmployeeComponent,
    UpdateEmployeeComponent,
    MainPageComponent,
    PaginatorComponent,
    UpdatePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    LoginService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
