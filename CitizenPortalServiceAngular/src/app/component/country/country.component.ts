import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import {MainService} from "../../service/main.service";
import {Subject} from "rxjs";
import Swal from 'sweetalert2';
import {ValidationService} from "../../service/validation.service";

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {

  countryList : any = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private mainService : MainService, private validateService : ValidationService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      paging: true,
      searching: true,
    }
    this.addCountry();
  }

  addCountry() {
    $('#add').show();
    $('#view').hide();
  }

  viewCountry() {
    $('#add').hide();
    $('#view').show();
    this.getCountryList();
  }

  getCountryList() {
    this.mainService.getCountryList(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
      if (data.statusCode == 200 && data.status == "Success") {
        this.countryList = data.data;
        this.dtTrigger.next(null);
      }
    });
  }

  addCountryData() {
    let countryName = $('#countryName').val();
    let countryCode = $('#countryCode').val();
    let status = $('#status').val();

    if (this.validateService.validateName(countryName && this.validateService.validateName(countryCode))) {
      this.mainService.addCountryData(countryName, countryCode, status, sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
        console.log(data);
        if (data.statusCode == 200 && data.status == "Success") {
          Swal.fire({
            title: 'Success',
            text: data.message,
            icon: 'success',
            confirmButtonText: 'Ok'
          }).then((result) => {
            if (result.isConfirmed) {
              $('#countryForm').trigger("reset");
            }
          });
        } else if (data.statusCode == 404 && data.status == "Failure") {
          Swal.fire({
            title: 'Error',
            text: data.message,
            icon: 'error'
          });
        }
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Please Provide Valid Data!',
      });
    }
  }

}
