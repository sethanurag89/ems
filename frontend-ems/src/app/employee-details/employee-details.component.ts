import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Employee } from './employee.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit{
  employee: Employee;
  createdBy: String;
  id: number;
  active: String;
  showDelete: boolean;
  profilePic: any;
  employeeEmail: String
  constructor(private employeeService : EmployeeService, private router: Router, private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((param)=>{
      this.id=+param['id'];
    });
    let email = localStorage.getItem('user');
    this.employeeService.getEmployeeById(this.id).subscribe((emp)=>{
      this.employee = emp;
      this.active = emp.active==true?'Active':'Not Active';
      this.employeeEmail = emp.email;
      this.employeeService.viewImage(this.employeeEmail).subscribe((imageData: Blob) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          this.profilePic = reader.result;
        };
        reader.readAsDataURL(imageData);
      });
      this.employeeService.getEmployeeById(emp.createdBy).subscribe((emp1)=>{
        this.createdBy = emp1.firstName + " " + emp1.lastName;
        if(this.employee.email==emp1.email){
          this.showDelete = false;
        }
      });
      if(this.employeeEmail==email){
        this.showDelete = false;
      }
    });
  }
  updateEmployee(id: number){
    this.router.navigate(['/home-page/update-employee', id]);
  }
  deleteEmployee(){
   this.employee.active=false;
      this.employeeService.updateEmployee(this.employee).subscribe(res=>{
        this.router.navigate(['/home-page/employee-list']);
      })
  }
}
