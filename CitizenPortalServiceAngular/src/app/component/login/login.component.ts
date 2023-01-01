import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../service/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  container : any;
  userName : any;
  password : any;

  constructor(private loginService : LoginService, private router : Router) { }

  ngOnInit(): void {
    this.container = document.getElementById('container');
  }

  signUp() {
    this.container.classList.add("right-panel-active");
  }

  signIn() {
    this.container.classList.remove("right-panel-active");
  }

  signInUser() {
    this.loginService.signInUser(this.userName, this.password).subscribe(data => {
      console.log(data);
      sessionStorage.setItem("loginStatus", "true");
      sessionStorage.setItem("userType", data.userDetails.userType);
      sessionStorage.setItem("name", data.userDetails.fullName);
      sessionStorage.setItem("authToken", data.userDetails.authToken);
      sessionStorage.setItem("userId", data.userDetails.userId);
      this.router.navigate(['welcome']);
    });
  }
}
