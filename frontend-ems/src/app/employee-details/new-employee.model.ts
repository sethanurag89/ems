export interface NewEmployee {
    empId: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    role: string;
    address: Address;
    contact: Contact;
    bloodGroup: string;
    gender: string;
    active: boolean;
    martialStatus: string;
    dob: string;
    createdBy: number;
  }
  
  export interface Address {
    addressLine1: string;
    addressLine2: string;
    city: string;
    state: string;
    pincode: string;
  }
  
  export interface Contact {
    contactType: string;
    number: string;
    active: boolean;
  }
  