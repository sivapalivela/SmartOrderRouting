import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateorderComponent } from '../createorder/createorder.component';
import { ViewtransactionsComponent } from '../viewtransactions/viewtransactions.component';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from '../data.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  marketvalue : string = '';
  Exchange : string = '';
  overallTransValue : string = '';
  transValue : string;
  consumerSwitchPoint : boolean;
  traderSwitchPoint : boolean;
  usertype : string = '';
  username : string = "Unidentified User"

  constructor(public dialog: MatDialog, private http: HttpClient,private router : Router, private data : DataService) { }

  ngOnInit(): void {
    this.Exchange = localStorage.getItem('Exchange');
    this.username = localStorage.getItem('username');
    this.usertype = localStorage.getItem('Typeofuser');
    if(this.usertype == 'User'){
      this.consumerSwitchPoint = true;
      this.traderSwitchPoint = false;
    }
    else{
      this.consumerSwitchPoint = false;
      this.traderSwitchPoint = true;
    }
    this.data.username = this.username;
    this.data.homeExchange = this.Exchange;
    this.data.getDetails(this.username);
    this.getTodayTransValue();
    this.getTodayMarketValue();
    this.getOverallTransValue(this.Exchange);
  }

  createOrder() {
    this.dialog.open(CreateorderComponent, {width: '600px', height: '550px'});
  }

  viewTransactions() {
    this.dialog.open(ViewtransactionsComponent, {width: '990px', height: '800px'});
  }

  getTodayTransValue(){
    this.http.get('http://localhost:8080/exchange/getTodayTransValue').subscribe(data => {
      this.transValue = JSON.stringify(data);
    });
  }

  getTodayMarketValue(){
    this.http.get('http://localhost:8080/sort/todayMarketValue').subscribe(data => {
      this.marketvalue = JSON.stringify(data);
    });
  }

  getOverallTransValue(exchange){
    this.http.get('http://localhost:8080/exchange/overallTransValue/' + exchange).subscribe(data => {
      this.overallTransValue = JSON.stringify(data);
    });
  }

  logout(){
    localStorage.removeItem('username');
    localStorage.removeItem('Typeofuser');
    this.router.navigate(['']);
  }

  openprofile(){
    this.router.navigate(['/profile']);
  }

}
