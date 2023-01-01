import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import {MainService} from "../../service/main.service";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {

  countryList : any = [];

  constructor(private mainService : MainService, private tokenService : TokenService) { }

  ngOnInit(): void {
    this.addCountry();
  }

  addCountry() {
    $('#add').show();
    $('#view').hide();
  }

  viewCountry() {
    $('#add').hide();
    $('#view').show();
  }

  getCountryList() {
    this.tokenService.generateToken().subscribe(data => {
      console.log(data);
      if (data.statusCode == 200 && data.status == "Success") {
        this.countryList = this.mainService.getCountryList(data.token, sessionStorage.getItem("authToken"));
        console.log("Country List");
        console.log(this.countryList);
      }
    });
  }

}
