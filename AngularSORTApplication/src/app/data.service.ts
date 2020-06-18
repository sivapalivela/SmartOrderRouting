import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  username : string = '';
  email : string = '';
  firstname : string = '';
  lastname : string = '';
  mobile : string = '';
  location : string = '';
  homeExchange : string = '';
  transactiontillnow : string = '';
  Details : Array<string> = [];
  

  constructor(private http: HttpClient) { }

  getDetails(user : string){
    this.http.get('http://localhost:8080/consumers/getdetails/' + user).subscribe(data => {
      for(let i=1;i<=data[0];i++){
        this.email = data[3];
        this.firstname = data[1];
        this.lastname = data[2];
        this.mobile = data[5];
        this.location = data[4];
        this.transactiontillnow = data[6];
      }
      console.log(this.Details);
      console.log(this.homeExchange);
    });
  }
}
