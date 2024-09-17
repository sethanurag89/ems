import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { NewEmployee } from '../employee-details/new-employee.model';
import { Observable } from 'rxjs';
import { Employee } from '../employee-details/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  base_url="http://localhost:8080/employees";
  constructor(private http: HttpClient, 
              private router: Router, 
              private activatedRoute: ActivatedRoute) { }
              
  addEmployee(employee: NewEmployee): Observable<NewEmployee> {
    return this.http.post<NewEmployee>(`${this.base_url}/add`, employee);
  }

  checkPassword(password: String){
    const formData = new FormData();
    formData.append('email', localStorage.getItem('user'));
    return this.http.post<boolean>(`${this.base_url}/checkPassword/${password}`, formData);
  }

  updatePassword(password: String, formData: FormData): Observable<any>{
    return this.http.put<any>(`${this.base_url}/updatePassword/`+password, formData);
  }

  viewImage(email: String){
    return this.http.get(`${this.base_url}/viewImage/${email}`,{ responseType: 'blob' });
  }

  updateImage(formData: FormData):Observable<any>{
    let email = localStorage.getItem('user');
    return this.http.post<any>(`${this.base_url}/updateImage/${email}`, formData);
  }

  updateEmployee(newEmployee: Employee): Observable<NewEmployee> {
    return this.http.put<NewEmployee>(`${this.base_url}/update`, newEmployee);
  }

  searchEmailExists(email: String){
    return this.http.get<boolean>(`${this.base_url}/searchEmail/${email}`);
  }

  getEmployeeByEmail(email: String){
    return this.http.get<any>(`${this.base_url}/findByEmail/${email}`).pipe(map((res) => {
      return {id:res.id, empId:res.empId, firstName: res.firstName, lastName: res.lastName, email: res.email, role:res.role, bloodGroup:res.bloodGroup, gender:res.gender, martialStatus:res.martialStatus, dob:res.dob,active:res.active,createdBy:res.createdBy,createdOn: res.createdOn,updatedOn: res.updatedOn,
        contact:{
          id:res.contact.id,
          number: res.contact.number,
          contactType: res.contact.contactType
        },
        address:{
          id:res.address.id,
          addressLine1: res.address.addressLine1,
          addressLine2: res.address.addressLine2,
          city: res.address.city,
          state: res.address.state,
          pincode: res.address.pincode
        }
      };
		}));
  }
  getEmployeeById(id: number){
    return this.http.get<any>(`${this.base_url}/find/${id}`).pipe(map((res) => {
      return {id:res.id, empId:res.empId, firstName: res.firstName, lastName: res.lastName, email: res.email, role:res.role, bloodGroup:res.bloodGroup, gender:res.gender, martialStatus:res.martialStatus, dob:res.dob,active:res.active,createdBy:res.createdBy,createdOn: res.createdOn,updatedOn: res.updatedOn,
        contact:{
          id:res.contact.id,
          number: res.contact.number,
          contactType: res.contact.contactType
        },
        address:{
          id:res.address.id,
          addressLine1: res.address.addressLine1,
          addressLine2: res.address.addressLine2,
          city: res.address.city,
          state: res.address.state,
          pincode: res.address.pincode
        }
      };
		}));
  }
  getAll(){
    return this.http.get<any>(this.base_url + "/findAll").pipe(map((result)=>{
      return result.map(res=> {
        return {id:res.id, empId:res.empId, firstName: res.firstName, lastName: res.lastName, email: res.email, role:res.role, bloodGroup:res.bloodGroup, gender:res.gender, martialStatus:res.martialStatus, dob:res.dob,active:res.active,createdBy:res.createdBy,createdOn: res.createdOn,updatedOn: res.updatedOn,
          contact:{
            id:res.contact.id,
            number: res.contact.number,
            contactType: res.contact.contactType
          },
          address:{
            id:res.address.id,
            addressLine1: res.address.addressLine1,
            addressLine2: res.address.addressLine2,
            city: res.address.city,
            state: res.address.state,
            pincode: res.address.pincode
          }
        };
      });
    }));
  }
}
