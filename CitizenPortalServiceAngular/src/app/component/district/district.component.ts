import { Component, OnInit } from '@angular/core';
import {MainService} from "../../service/main.service";
import {Subject} from "rxjs";
import Swal from "sweetalert2";

@Component({
  selector: 'app-district',
  templateUrl: './district.component.html',
  styleUrls: ['./district.component.css']
})
export class DistrictComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  countryList : any;
  stateList : any;
  districtList : any;

  constructor(private mainService : MainService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      paging: true,
      searching: true,
    }
    this.addDistrict();
    this.getCountryList();
    this.getDistrictList();
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
      if (data.status == "Success" && data.statusCode == 200) {
        this.countryList = data.data;
      } else {
        Swal.fire({
          title: 'Error!',
          text: data.message,
          icon: 'error'
        });
      }
    });
  }

  getDistrictList(){
    this.mainService.getAllDistrictList(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
      if (data.status == "Success" && data.statusCode == 200) {
        this.districtList = data.data;
        this.dtTrigger.next(null);
      } else {
        Swal.fire({
          title: 'Error!',
          text: data.message,
          icon: 'error'
        });
      }
    });
  }

  getStateList(event : any){
    if (event.target.value != 0) {
      this.mainService.getStateListByCountryId(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken"), event.target.value).subscribe(data => {
        if (data.status == "Success" && data.statusCode == 200) {
          this.stateList = data.data;
          console.log(this.stateList);
        } else {
          Swal.fire({
            title: 'Error!',
            text: data.message,
            icon: 'error'
          });
        }
      });
    }
  }
  addDistrictData(){
    let countryId = $('#countryName').val();
    let stateId = $('#stateName').val();
    let districtName = $('#districtName').val();
    let status = $('#status').val();
    if (countryId == 0) {
      Swal.fire({
        title: 'Error!',
        text: "Please Select Country",
        icon: 'error'
      });
    }else if (stateId == 0) {
      Swal.fire({
        title: 'Error!',
        text: "Please Select State",
        icon: 'error'
      });
    }else if (districtName == "") {
      Swal.fire({
        title: 'Error!',
        text: "Please Enter District Name",
        icon: 'error'
      });
    } else if (districtName != undefined && districtName.toString().match(/^[a-zA-Z ]*$/) == null) {
      Swal.fire({
        title: 'Error!',
        text: "Please Enter Valid District Name",
        icon: 'error'
      });
    } else {
      this.mainService.addDistrictData(countryId, stateId, districtName, status, sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
        if (data.status == "Success" && data.statusCode == 200) {
          Swal.fire({
            title: 'Success',
            text: data.message,
            icon: 'success'
          });
        } else {
          Swal.fire({
            title: 'Error!',
            text: data.message,
            icon: 'error'
          });
        }
      });
    }
  }

}
