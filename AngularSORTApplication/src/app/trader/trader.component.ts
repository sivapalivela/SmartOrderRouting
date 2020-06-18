import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trader',
  templateUrl: './trader.component.html',
  styleUrls: ['./trader.component.css']
})
export class TraderComponent implements OnInit {

  trader : string = '';
  Orders = [];

  constructor(private http: HttpClient, private router : Router) { }

  ngOnInit(): void {
    this.trader = localStorage.getItem('username');
    this.displayOrders();
  }

  displayOrders(){
    this.http.get('http://localhost:8080/orders/getorders').subscribe(result => {
      for(let i=1;i<=result[0];i++){
        let temp = result[i].split("-");
        this.Orders.push(temp);
      }
    });
  }

  accept(id,range){
    this.http.post('http://localhost:8080/sort/processtrade/' + id + "/" + range, { headers: new HttpHeaders().set('Content-Type', 'application/json'), responseType: 'text' }).subscribe(
      result => {
        alert(result['text']);
        this.router.navigate(['/dashboard']);
      }
    );
  }

  reject(id){
    this.http.post('http://localhost:8080/orders/deleteOrder/' + id, { headers: new HttpHeaders().set('Content-Type', 'application/json'), responseType : 'text' }).subscribe(
      result => {
        alert(result['text']);
        this.router.navigate(['/dashboard']);
      }
    );
  }

}
