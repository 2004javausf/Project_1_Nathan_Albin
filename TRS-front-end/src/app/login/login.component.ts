import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
import { RouterModule, RouterLink, Router, ActivatedRoute } from '@angular/router';
import { NavigationExtras } from '@angular/router'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, RouterModule {

  show: boolean = false;

  constructor(
    private httpClient : HttpClient, 
    private router: Router, 
    private route: ActivatedRoute
    ) { }
  

  ngOnInit(): void {
    
    
  }

  onClick(e : Event): void {
    var usr = ((document.getElementById("usr") as HTMLInputElement).value);
    var pass = ((document.getElementById("pass") as HTMLInputElement).value);
    localStorage.setItem("usr", usr);
    var acctype = ((document.getElementById("accType") as HTMLInputElement).value);
    var message = document.getElementById("msg");
    var user = {
      "username": usr,
      "password": pass,
      "accType" : acctype
    }
    this.httpClient.post("http://localhost:8080/TRS/login", user)
    .subscribe(x => 
      {if((x == true) && (user.accType == "Emp")) {
        this.router.navigate(['/menu'], {relativeTo: this.route});
        console.log("login success emp");
      } else if((x == true) && (user.accType == "BenCo")) {
        this.router.navigate(['/menuben'], {relativeTo: this.route});
        console.log("login success benco");
      } else if ((x == true) && (user.accType=="DeptHead")){
        this.router.navigate(['/menuhead'], {relativeTo: this.route});
        console.log("login success depthead");
      } else if ((x == true) && (user.accType=="DirSup")){
        this.router.navigate(['/menuhead'], {relativeTo: this.route});
        console.log("login successs direct supervisor");
      } else {
        this.router.navigate(['/login'], {relativeTo: this.route});
        console.log("login failed");
        message.innerHTML = "Login Failed, try again.";
        
      }
      
    });
  }
  password() {
    this.show = !this.show;
  }
  
}
