import { Component, OnInit } from '@angular/core';
import {MainService} from "../../service/main.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-district',
  templateUrl: './district.component.html',
  styleUrls: ['./district.component.css']
})
export class DistrictComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  countryList : any;

  constructor(private mainService : MainService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      paging: true,
      searching: true,
    }
    this.addDistrict();
    this.getCountryList();
  }

  addDistrict(){
    $('#add1').show();
    $('#view1').hide();
  }
  viewDistrict(){
    $('#add1').hide();
    $('#view1').show();
  }
  getCountryList(){
    this.mainService.getCountryList(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
      console.log("Country List Fetched From Server: ");
      console.log(data);
    });
  }
  addDistrictData(){}

}
