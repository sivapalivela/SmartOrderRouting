import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-createorder',
  templateUrl: './createorder.component.html',
  styleUrls: ['./createorder.component.css']
})
export class CreateorderComponent implements OnInit {

  //Replace later
  consumersId : string = "Pbskumar";
  //Replace later

  orderStatus : string = "Incomplete";
  orderType: Array<string> = ["BuyOrder", "SellOrder"];
  Stocks : Array<string> = [];
  Exchanges : Array<string> = [];

  //Data for Form
  selectedStock : string = "";
  selectedOrder : string = "";
  selectedExchange : string = "";
  numberOfShares : number;
  sharePrice : number;
  bufferRange : number;
  //Data for Form

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getStocks();
    this.getExchanges();
  }

  getStocks(){
    this.http.get('http://localhost:8080/companies/getcompanies').subscribe(data => {
      for(let i=1;i<=data[0];i++){
        this.Stocks.push(data[i]);
      }
    });
  }

  getExchanges(){
    this.http.get('http://localhost:8080/exchange/getexchange').subscribe(data => {
      for(let i=1;i<=data[0];i++){
        this.Exchanges.push(data[i]);
      }
    });
  }

  onSubmit(){
    const exc = this.selectedExchange.split(" ");
    const com = this.selectedStock.split(" ");
    const data = {"numberOfShares" : this.numberOfShares,
                  "orderExchangeId" : exc[2],
                  "typeOfOrder" : this.selectedOrder,
                  "price" : this.sharePrice,
                  "orderStatus" : this.orderStatus + " " + this.bufferRange
                  }
    this.http.post('http://localhost:8080/orders/createOrder/' + this.consumersId + "/" + com[2], data, { headers: new HttpHeaders().set('Content-Type', 'application/json'), responseType: 'text' }).subscribe(
      result => {
        alert(result);
      }
    );
  }

}
