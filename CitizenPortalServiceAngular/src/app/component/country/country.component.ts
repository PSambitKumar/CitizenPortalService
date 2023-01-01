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
    this.getCountryList();
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
      if (data.statusCode == 200 && data.status == "Success") {
        this.mainService.getCountryList(data.token, sessionStorage.getItem("authToken")).subscribe(data => {
          if (data.statusCode == 200 && data.status == "Success") {
            this.countryList = data.data;
          }
        });
      }
    });
  }

}
