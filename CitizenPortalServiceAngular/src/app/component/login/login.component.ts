import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../service/login.service";
import {Router} from "@angular/router";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  container : any;
  userName : any;
  password : any;
  apiToken : any;

  constructor(private loginService : LoginService, private router : Router,private  tokenService : TokenService) { }

  ngOnInit(): void {
    this.container = document.getElementById('container');
    this.generateToken();
  }

  signUp() {
    this.container.classList.add("right-panel-active");
  }

  signIn() {
    this.container.classList.remove("right-panel-active");
  }

  generateToken() {
    this.tokenService.generateToken().subscribe(data => {
      if (data.statusCode == 200 && data.status == "Success") {
        this.apiToken = data.token;
        sessionStorage.setItem("apiToken", data.token);
      }
    });
  }

  signInUser() {
    this.loginService.signInUser(this.userName, this.password).subscribe(data => {
      console.log(data);
      sessionStorage.setItem("loginStatus", "true");
      sessionStorage.setItem("userType", data.userDetails.userType);
      sessionStorage.setItem("name", data.userDetails.fullName);
      sessionStorage.setItem("authToken", data.userDetails.authToken);
      sessionStorage.setItem("userId", data.userDetails.userId);

      this.router.navigate(['/welcome']).then(() => {
        window.location.reload();
      });
    });
  }
}
