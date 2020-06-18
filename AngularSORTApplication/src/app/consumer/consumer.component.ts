import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-consumer',
  templateUrl: './consumer.component.html',
  styleUrls: ['./consumer.component.css']
})

export class ConsumerComponent implements OnInit{

  orderBook : Array<string> = [];
  user : string = '';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.user = localStorage.getItem('username');
    this.viewOrders();
  }

  viewOrders(){
    this.http.get('http://localhost:8080/orders/getorders').subscribe(result => {
      for(let i=1;i<=result[0];i++){
        let temp = result[i].split("-");
        if(temp[3] <= 150){
          temp[8] = "blue";
        }
        else if(temp[3] > 150 && temp[3] <= 400){
          temp[8] = "green";
        }
        else{
          temp[8] = "red";
        }
        this.orderBook.push(temp);
      }
    });
  }

}
