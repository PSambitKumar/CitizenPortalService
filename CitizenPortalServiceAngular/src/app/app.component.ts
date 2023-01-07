import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {MainService} from "./service/main.service";
import {TokenService} from "./service/token.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isCollapsed = false;
  loginStatus : any;
  userType : any;
  fullName : any;
  apiToken : any;
  authToken : any;
  userId : any;
  urlList : any;

  constructor(private router : Router, private mainService : MainService, private tokenService : TokenService) {
  }
  ngOnInit() {
    this.loginStatus = sessionStorage.getItem("loginStatus");
    this.userType = sessionStorage.getItem("userType");
    this.fullName = sessionStorage.getItem("name");
    this.authToken = sessionStorage.getItem("authToken");
    this.userId = sessionStorage.getItem("userId");
    console.log("Login Status : "+this.loginStatus);
    this.getURL();
  }

  getURL() {
    if (this.userType == "Admin") {
      this.tokenService.generateToken().subscribe(data => {
        console.log(data);
        if (data.statusCode == 200 && data.status == "Success") {
          this.apiToken = data.token;
          this.mainService.getURL(this.apiToken, this.authToken, this.userId).subscribe(data => {
            if (data.statusCode == 200 && data.status == "Success") {
              this.urlList = data.data;
              console.log(this.urlList);
            }
          });
        }
      });
    }
  }

  logoutUser() {
    sessionStorage.clear();
    this.router.navigate(['']).then(() => {
      window.location.reload();
    });
  }
}
