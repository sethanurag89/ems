import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Employee } from '../employee-details/employee.model';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[];
  role: string;
  search: string = '';
  @ViewChild('fileInput') fileInput: ElementRef;
  profilePics: Map<String, String> = new Map<String, String>();
  pageSize=5;
  totalSize=0;
  index = 0;
  sliceEmployeeList;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.employeeService.getAll().subscribe((result: Employee[]) => {
      this.employees = result;
      this.totalSize = result.length;
      this.employeeImages();
      this.sliceEmployeeList = this.sliceList;
    });
    this.employeeService.getEmployeeByEmail(localStorage.getItem('user')).subscribe(res => {
      this.role = res.role.slice(5);
    });
  }

  employeeImages(): void {
    this.employees.forEach((employee: Employee) => {
      this.employeeService.viewImage(employee.email).subscribe((imageData: Blob) => {
        const reader = new FileReader();
        reader.onload = (res: any) => {
          this.profilePics.set(employee.email, res.target.result);
        };
        reader.readAsDataURL(imageData);
      });
    });
  }

  getProfilePic(email: String, gender: String): String {
    const profilePic = this.profilePics.get(email);
    if(gender=='Male'){
      return profilePic?profilePic:'https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp';
    }
    return profilePic?profilePic:'https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2.webp';
  }

  get filteredEmployees(): Employee[] {
    if (!this.search) {
      return this.sortEmployees(this.employees);
    } else {
      const query = this.search.toLowerCase();
      const filtered = this.employees.filter(employee =>
        employee.firstName.toLowerCase().includes(query) ||
        employee.lastName.toLowerCase().includes(query) ||
        employee.role.toLowerCase().includes(query) ||
        employee.email.toLowerCase().includes(query)
      );
      return this.sortEmployees(filtered);
    }
  }

  private sortEmployees(employees: Employee[]): Employee[] {
    const activeEmployees = employees.filter(employee => employee.active);
    const nonActiveEmployees = employees.filter(employee => !employee.active);
    return [...activeEmployees, ...nonActiveEmployees];
  }

  showFullDetails(id: number): void {
    this.router.navigate(['/home-page/employee-details', id]);
  }
  
  onChange(e) {
    this.index = +e.index;
    this.pageSize = +e.pageSize;
    this.sliceEmployeeList = this.sliceList;
  }

  get sliceList() {
    const startIndex = this.index * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    return this.filteredEmployees.slice(startIndex, endIndex).slice();
  }
}
