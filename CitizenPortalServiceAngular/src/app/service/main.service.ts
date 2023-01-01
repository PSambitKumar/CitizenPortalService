import { Injectable } from '@angular/core';
import {TokenService} from "./token.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as http from "http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MainService {

  baseUrl = environment.baseURL;

  constructor(private tokenService : TokenService, private http : HttpClient) { }

  getURL(apiToken : any, authToken : any, userId : any) : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      "Authorization":  authToken,
      "Token": apiToken,
    });

    let options = {
      headers: headers,
      params: {
        "userId": userId
      }
    };
    return this.http.get<any>(this.baseUrl + '/api/main/getURL', options);
  }

  getCountryList(apiToken : any, authToken : any) : Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      "Authorization": authToken,
      "Token": apiToken,
    });

    let options = {
      headers: headers
    };
    return this.http.get<any>(this.baseUrl + '/api/main/getCountryList', options);
  }
}
