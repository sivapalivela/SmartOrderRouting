import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username : string;
  password : string;
  users : Array<string> = ["User", "Trader"];
  selectedUser : string;

  constructor(private router: Router, private http : HttpClient) { }

  ngOnInit(): void {
  }

  register(){
    this.router.navigate(['/register']);
  }

  submit(){
    const data = {"username" : this.username,
                  "password" : this.password,
                  "typeofuser" : this.selectedUser,
                  }
    this.http.post('http://localhost:8080/consumers/login', data, { headers: new HttpHeaders().set('Content-Type', 'application/json'), responseType: 'text' }).subscribe(
      result => {
        let d = JSON.stringify(JSON.parse(result)).substring(9).replace('"}','');
        console.log(d);
        alert(d);
        if(d.split(' ').length > 4){
          this.router.navigate(['']);
        }
        else{
          localStorage.setItem("username", d.split(' ')[2]);
          localStorage.setItem("Typeofuser", this.selectedUser);
          localStorage.setItem("Exchange", d.split(' ')[3]);
          this.router.navigate(['/dashboard']);
        }
      }
    );
  }

}
