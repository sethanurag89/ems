import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { NewEmployee } from '../employee-details/new-employee.model';
import { Employee } from '../employee-details/employee.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit{
  employees: Employee[];
  role: string;
  createdBy: number;
  showExists: boolean = false;
  showNotExists: boolean = false;
  newEmployee: NewEmployee = {
    empId: 0,
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    role: 'employee',
    address: {
      addressLine1: '',
      addressLine2: '',
      city: '',
      state: '',
      pincode: ''
    },
    contact: {
      contactType: 'personal',
      number: '',
      active: true
    },
    bloodGroup: '',
    gender: '',
    active: true,
    martialStatus: 'Bachelor',
    dob: '',
    createdBy: 0
  };

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService) {}
  ngOnInit(): void {
    this.employeeService.getEmployeeByEmail(localStorage.getItem('user')).subscribe(res => {
      this.role = res.role.slice(5);
      this.createdBy = res.id;
    });
  }

  check(): void{
    console.log(this.newEmployee.email);
    this.showExists = false;
    this.showNotExists = false;
    this.employeeService.searchEmailExists(this.newEmployee.email)
    .subscribe(
      (response: boolean) => {
        if(response==true)
          this.showExists = true;
        else
          this.showNotExists = true;
      }
    );
  }

  showButton(): boolean{
    if(this.newEmployee.firstName==''||this.newEmployee.lastName==''||this.newEmployee.email==''||this.newEmployee.gender==''||this.newEmployee.dob==''||this.newEmployee.address.addressLine1==''||this.newEmployee.address.addressLine2==''||this.newEmployee.address.city==''||this.newEmployee.address.pincode==''||this.newEmployee.address.state==''||this.newEmployee.contact.number==''){
      return false;
    }
    return true;
  }
  onSubmit(): void {
    this.newEmployee.password = "test@123";
    this.newEmployee.createdBy = this.createdBy;
    this.employeeService.addEmployee(this.newEmployee).subscribe((res) => {
      this.toastr.success('Employee added');
      this.router.navigate(['/home-page/employee-list']);
    }, error => {
      this.toastr.error('Employee id or email exists already');
    });
  }
}
