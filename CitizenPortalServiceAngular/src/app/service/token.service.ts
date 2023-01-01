import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  baseUrl = environment.baseURL;

  constructor(private http : HttpClient) { }

  generateToken() : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let options = {
      headers: headers,
      params: {
        "userName": "admin"
      }
    };
    return this.http.get<any>(this.baseUrl + '/api/token/generate', options);
  }
}
