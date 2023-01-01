import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseURL = environment.baseURL;

  constructor(private http : HttpClient) { }

  signInUser(userName : any, password : any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let options = {
      headers: headers,
      params: {
        "userName": userName,
        "password": password
      }
    };
    return this.http.get<any>(this.baseURL + '/api/login/user', options);
  }
}
