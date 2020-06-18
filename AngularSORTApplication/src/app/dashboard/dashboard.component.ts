import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateorderComponent } from '../createorder/createorder.component';
import { ViewtransactionsComponent } from '../viewtransactions/viewtransactions.component';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  transValue : string;
  consumerSwitchPoint : boolean;
  traderSwitchPoint : boolean;
  usertype : string = '';
  username : string = "Unidentified User"

  constructor(public dialog: MatDialog, private http: HttpClient,private router : Router) { }

  ngOnInit(): void {
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
    this.getTodayTransValue();
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

  logout(){
    localStorage.removeItem('username');
    localStorage.removeItem('Typeofuser');
    this.router.navigate(['']);
  }

}
