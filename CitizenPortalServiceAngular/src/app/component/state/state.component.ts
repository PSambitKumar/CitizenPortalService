import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";
import {MainService} from "../../service/main.service";

@Component({
  selector: 'app-state',
  templateUrl: './state.component.html',
  styleUrls: ['./state.component.css']
})
export class StateComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject<any>();
  countryList : any = [];
  stateList : any = [];

  constructor(private mainService : MainService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      paging: true,
      searching: true,
    }
    this.getCountryList();
    this.getStateList()
    this.addState();
  }

  addState() {
    $('#add1').show();
    $('#view1').hide();
  }

  viewState() {
    $('#add1').hide();
    $('#view1').show();
  }

  getCountryList() {
    this.mainService.getCountryList(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
      if (data.statusCode == 200 && data.status == "Success") {
        this.countryList = data.data;
      }
    });
  }

  getStateList() {
    this.mainService.getStateList(sessionStorage.getItem("apiToken"), sessionStorage.getItem("authToken")).subscribe(data => {
      console.log(data);
      if (data.statusCode == 200 && data.status == "Success") {
        this.stateList = data.data;
        this.dtTrigger.next(null);
      }
    });
  }

  addStateData() {

  }
}
