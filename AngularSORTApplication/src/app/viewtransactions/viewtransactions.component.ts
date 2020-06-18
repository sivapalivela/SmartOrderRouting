import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-viewtransactions',
  templateUrl: './viewtransactions.component.html',
  styleUrls: ['./viewtransactions.component.css']
})
export class ViewtransactionsComponent implements OnInit {

  consumerId : string = this.data.username;

  transactions : Array<string> = [];

  constructor(private http: HttpClient, private data : DataService) { }

  ngOnInit(): void {
    this.getTransactions(this.consumerId);
  }

  getTransactions(consumerId){
    this.http.get('http://localhost:8080/transactions/getTransactions/'+this.consumerId).subscribe(result => {
      for(let i=1;i<=result[0];i++){
        let temp = result[i].split("/");
        let temp2 = temp[2].split(" ");
        temp[2] = temp2[0];
        this.transactions.push(temp);
      }
      console.log(this.transactions);
    });
  }

}
