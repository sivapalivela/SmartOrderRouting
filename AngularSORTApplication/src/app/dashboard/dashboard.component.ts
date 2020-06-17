import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateorderComponent } from '../createorder/createorder.component';
import { ViewtransactionsComponent } from '../viewtransactions/viewtransactions.component';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  transValue : string;
  consumerSwitchPoint = true;
  traderSwitchPoint = false;

  constructor(public dialog: MatDialog, private http: HttpClient) { }

  ngOnInit(): void {
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

}
