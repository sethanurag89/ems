import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Employee } from 'src/app/employee-details/employee.model';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit{
  employee: Employee;
  role:String;
  id: number;
  createdBy:String;
  newEmployee: Employee;
  profilePic: any;
  employeeEmail: String
  constructor(private employeeService : EmployeeService, 
              private router: Router, 
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService){}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((param)=>{
      this.id=+param['id'];
    });
    this.employeeService.getEmployeeById(this.id).subscribe((emp1)=>{
      this.employee = emp1;
      this.newEmployee = emp1;
      this.newEmployee.role = emp1.role.slice(5);
      this.employee.role = emp1.role;
      this.employeeEmail = emp1.email;
      this.employeeService.viewImage(this.employeeEmail).subscribe((imageData: Blob) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          this.profilePic = reader.result;
        };
        reader.readAsDataURL(imageData);
      });
      this.employeeService.getEmployeeByEmail(localStorage.getItem('user')).subscribe(res => {
        this.role = res.role.slice(5);
      });
      this.employeeService.getEmployeeById(emp1.createdBy).subscribe((emp2)=>{
        this.createdBy = emp2.firstName + " " + emp2.lastName;
      });
    });
  }

  onSubmit(): void {
    console.log(this.newEmployee);
    this.employeeService.updateEmployee(this.newEmployee).subscribe((res) => {
      this.toastr.success('Information updated');
      this.router.navigate(['/home-page/employee-details',this.newEmployee.id]);
    }, error => {
      this.toastr.error('Employee id or email exists already');
    });
  }

}
