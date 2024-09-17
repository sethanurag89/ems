import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from '../../employee.model';
import { EmployeeService } from 'src/app/services/employee.service';
import { NewEmployee } from '../../new-employee.model';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit{
  employee: NewEmployee;
  exist: boolean = false;
  currentPassword: String = '';
  newPassword: String = '';
  confirmPassword: String = '';
  showCurrentPasswordMatch: boolean = false;
  showCurrentPasswordNotMatch: boolean = false;
  constructor(public employeeService: EmployeeService,
              public activeModal: NgbActiveModal,
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  passBack() {
    this.activeModal.close();
  }

  showCurrentPassword: boolean = false;
  showNewPassword: boolean = false;
  showConfirmPassword: boolean = false;
  showSamePassword: boolean = false;
  showNotSameMessage: boolean = false;

  viewPassword(f: string) {
    if (f === 'newPassword') {
      this.showNewPassword = !this.showNewPassword;
    } 
    else if(f === 'confirmPassword') {
      this.showConfirmPassword = !this.showConfirmPassword;
    }
    else if(f === 'currentPassword'){
      this.showCurrentPassword = !this.showCurrentPassword;
    }
  }
  checkCurrent(){
    this.showCurrentPasswordMatch = false;
    this.showCurrentPasswordNotMatch = false;
    this.employeeService.checkPassword(this.currentPassword).subscribe(res=>{
      if(res==false){
        this.showCurrentPasswordNotMatch = true;
      }
      else{
        this.showCurrentPasswordMatch = true;
      }
      this.exist = res;
    });
  }

  showButton(){
    if(this.exist==true && (this.newPassword==this.confirmPassword) && this.newPassword!='' && this.showSamePassword==false){
      return true;
    }
    return false;
  }

  checkConfirmPassword(){
    this.showSamePassword = false;
    this.showNotSameMessage = false;
    if(this.newPassword==this.currentPassword){
      this.showSamePassword = true;
    }
    if(this.newPassword!=this.confirmPassword){
      this.showNotSameMessage = true;
    }
  }

  changePassword(){
    const formData = new FormData();
    formData.append('email', localStorage.getItem('user'));
    this.employeeService.updatePassword(this.confirmPassword, formData).subscribe(res=>{
      this.toastr.success('Changed password');
    },error=>{
      this.toastr.error('Password not changed');
    });
    this.activeModal.close();
  }
}
