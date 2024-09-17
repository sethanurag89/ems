import { Component, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee.model';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpClient } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdatePasswordComponent } from './update-password/update-password.component';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent {
  employee: Employee;
  profilePic: any;
  tempProfilePic: any;
  showAlert: boolean = false;
  showMessage: boolean = false;
  file: File | undefined;
  @ViewChild('fileInput') fileInput: ElementRef;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    let email = localStorage.getItem('user');
    
    this.employeeService.getEmployeeByEmail(email).subscribe((emp) => {
      this.employee = emp;
      this.employee.role = emp.role.slice(5);
    });

    this.employeeService.viewImage(email).subscribe((imageData: Blob) => {
      const reader = new FileReader();
      reader.onload = (res: any) => {
        this.profilePic = res.target.result;
        this.tempProfilePic = this.profilePic
      };
      reader.readAsDataURL(imageData);
    });
  }

  updateEmployee(id: number) {
    this.router.navigate(['/home-page/update-employee', id]);
  }

  onChange(event: any) {
    this.file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = (res: any) => {
      this.profilePic = res.target.result;
    };
    reader.readAsDataURL(this.file);
    if(this.file){
      this.showMessage = true;
    }
  }

  updateImage() {
    this.showAlert = true;
  }

  closeAlert() {
    this.showAlert = false;
    this.showMessage = false;
    this.profilePic = this.tempProfilePic;
  }

  onUpload() {
    if (this.file) {
      const formData = new FormData();
      formData.append('profilePic', this.file);
      this.employeeService.updateImage(formData).subscribe((res: any) => {
        this.profilePic = res;
      });
    }
    this.showAlert = false;
    this.showMessage = false;
  }

  updatePassword(){
    this.modalService.open(UpdatePasswordComponent);
  }
}
