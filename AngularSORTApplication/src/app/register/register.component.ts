import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username : string;
  firstname : string;
  lastname : string;
  email : string;
  password : string;
  mnumber : number;
  location : string;
  homeExchange : string;
  exchange : Array<string> = [];

  constructor(private http: HttpClient, private router : Router) { }

  ngOnInit(): void {
    this.exchanges();
  }

  exchanges(){
    this.http.get('http://localhost:8080/exchange/getexchange').subscribe(data => {
      for(let i=1;i<=data[0];i++){
        this.exchange.push(data[i]);
      }
    });
  }

  backtologin(){
    this.router.navigate(['/login']);
  }

  onSubmit(){
    const ex = this.homeExchange.split(" ");
    const data = {
                    "consumersId" : this.username,
                    "firstName" : this.firstname,
                    "lastName" : this.lastname,
                    "email" : this.email,
                    "password" : this.password,
                    "mobileNumber" : this.mnumber,
                    "location" : this.location
                  };
    this.http.post('http://localhost:8080/consumers/createconsumer/' + ex[2], data, { headers: new HttpHeaders().set('Content-Type', 'application/json'), responseType: 'text' }).subscribe(
      result => {
        alert(result);
      }
    );
  }

}
