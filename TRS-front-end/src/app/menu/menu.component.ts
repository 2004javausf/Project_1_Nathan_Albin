import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TRS_User } from '../TRS_User'
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';



@Component({

  
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  users: Observable<TRS_User[]>;

  constructor(
    private httpClient: HttpClient,
    private userService : UserService) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"))
  }

  toggle() {
    var x = document.getElementById("createapp");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else if (x.style.display == "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }

  submitApp() {
    var form = {
      "username": localStorage.getItem("usr"),
      "eventType": ((document.getElementById("eventType") as HTMLInputElement).value),
      "eventCost": ((document.getElementById("cost") as HTMLInputElement).value),
      "gradeType": ((document.getElementById("gradeType") as HTMLInputElement).value),
      "startDate": ((document.getElementById("date") as HTMLInputElement).value)
    }
    this.httpClient.post("http://localhost:8080/TRS/application", form)
    .subscribe(x => {
      if(x == true) {
        console.log("true");
        document.getElementById("returnStmt").innerHTML = "Application submitted. To view/cancel, click the 'View Applications' button. Applications cannot be canceled after being considered.";
      } else if (x == false) {
        console.log("false");
        document.getElementById("returnStmt").innerHTML = "Submition failed. An event can only be requested more than one week prior to the start date."
      }
      
      
    });
  
  }
  
  
  showAccInfo() {
    let userinfo = document.getElementById("userinfo");
    let usr = {
      username: localStorage.getItem("usr")
    }
    this.httpClient.post("http://localhost:8080/TRS/info", usr)
    .subscribe(x => {
      let xx = JSON.stringify(x);
      let xxx = JSON.parse(xx);
      console.log(xxx);
      userinfo.innerHTML = 
        "Username: " + xxx[0]["username"] + "<br>" +
        "First Name: " + xxx[0]["firstName"] + "<br>" +
        "Last Name: " + xxx[0]["lastName"] + "<br>" +
        "Balance: " + xxx[0]["balance"] + "<br>" +
        "Account Type: " + xxx[0]["accType"];
    });


  
  }

  
}
