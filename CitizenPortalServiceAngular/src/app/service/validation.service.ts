import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  validateName(name : any) {
    let regex = /^[a-zA-Z ]{2,30}$/;
    return regex.test(name);
  }
}
