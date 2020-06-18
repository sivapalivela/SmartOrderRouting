import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private router : Router, private http: HttpClient, public data : DataService) { }

  ngOnInit(): void {
  }

  close(){
    this.router.navigate(['/dashboard']);
  }

}
