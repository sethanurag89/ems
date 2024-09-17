import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit{
  name: String;

  constructor(private employeeService: EmployeeService){}
  
  ngOnInit(): void {
    this.employeeService.getEmployeeByEmail(localStorage.getItem('user')).subscribe(res=>{
      this.name = res.firstName + " " + res.lastName;
    });
  }


}
