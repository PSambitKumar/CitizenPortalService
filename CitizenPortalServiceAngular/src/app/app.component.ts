import { Component } from '@angular/core';
import {Router} from "@angular/router";

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

  constructor(private router : Router) {
  }
  ngOnInit() {
    this.loginStatus = sessionStorage.getItem("loginStatus");
    this.userType = sessionStorage.getItem("userType");
    this.fullName = sessionStorage.getItem("name");
    console.log("Login Status : "+this.loginStatus);
  }

  logoutUser() {
    sessionStorage.clear();
    this.router.navigate(['']);
  }
}
