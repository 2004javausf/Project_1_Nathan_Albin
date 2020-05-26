import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TRS_User } from '../TRS_User'
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"))
  }

  toggle() {
    document.getElementById("createapp").removeAttribute("hidden");
    var x = document.getElementById("createapp");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
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
    let user = {
      username: localStorage.getItem("usr")
    }

    this.httpClient.post<TRS_User[]>("http://localhost:8080/TRS/info", user)
    .subscribe(x => {
      let tmp = JSON.stringify(x);
      userinfo.innerHTML = tmp;
      
    });


  }

}
