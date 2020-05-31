import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TRS_User } from '../TRS_User'
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { Route } from '@angular/compiler/src/core';



@Component({

  
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, RouterModule {

  users: Observable<TRS_User[]>;

  constructor(
    private httpClient: HttpClient,
    private userService : UserService,
    private router : Router,
    private route : ActivatedRoute) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"));
    this.getNotifications();
    let j = localStorage.getItem("usr");
    if(j == null) {
      this.router.navigate(['/login'], {relativeTo: this.route});
    }
  }

  getNotifications() {
    let todonotif = document.getElementById("todonotif");
    let input = {
      "username" : localStorage.getItem("usr")
    }
    this.httpClient.post("http://localhost:8080/TRS/getnotification", input)
    .subscribe(x => {
      let i = 0;
      todonotif.innerHTML = "";
      for(i = 0; i < Object.keys(x).length; i++) {
        todonotif.insertAdjacentHTML('afterbegin', 
        "Form ID: " + x[i]["formId"] + ",    " +
        "New Amount: " + x[i]["changeAmt"] + ",    " +
        "New Balance: " + x[i]["newBal"] + "<br>" + "<br>"
        ); 
      }
    });
  }

  accDenyNotif() {
    let notifreturnstmt = document.getElementById("notifreturnstmt");
    let input = {
      "formId" : ((document.getElementById("f4id") as HTMLInputElement).value),
      "acceptDeny" : ((document.getElementById("accden") as HTMLInputElement).value)
    }
    this.httpClient.post("http://localhost:8080/TRS/removenotification", input)
    .subscribe(x => {
      if (x == true) {
        notifreturnstmt.innerHTML = "Accepted changes.";
      } else if (x == false) {
        notifreturnstmt.innerHTML = "Denied changes. Application has been voided and reimbursement amount has been returned to account balance.";
      }
    });
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

  showUpdateGrade() {
    var x = document.getElementById("updgrade");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else if (x.style.display == "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }

  showDenials() {
    var k = document.getElementById("vdenials");
    if (k.style.display == "none") {
      k.style.display = "block";
    } else if (k.style.display == "block") {
      k.style.display = "none";
    } else {
      k.style.display = "block";
    }
    let vdenials = document.getElementById("vdenials");
    let input = {
      "username" : localStorage.getItem("usr")
    }
    this.httpClient.post("http://localhost:8080/TRS/denial", input)
    .subscribe(x => {
      console.log(x);
      let i = 0;
      vdenials.innerHTML = "";
      for(i = 0; i < Object.keys(x).length; i++) {
        vdenials.insertAdjacentHTML('beforeend', 
        "Form ID: " + x[i]["formId"] + ",    " +
        "Benefits Coordinator Reason: " + x[i]["bencoReason"] + ",    " +
        "Dept. Head Reason: " + x[i]["deptheadReason"] + ",    " +
        "Direct Supervisor Reason: " + x[i]["dirsupReason"] + "<br>" + "<br>"
        ); 
      }

    });

  }

  submitApp() {
    let form = {
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

  showAppInfo() {
    let appinfo = document.getElementById("userappinfo");
    let usr = {
      username : localStorage.getItem("usr")
    }
    this.httpClient.post("http://localhost:8080/TRS/appinfo", usr)
    .subscribe(x => {
      console.log(x);
      let i = 0;
      appinfo.innerHTML = "";
      for(i = 0; i < Object.keys(x).length; i++) {
        appinfo.insertAdjacentHTML('beforeend', 
        "Form ID: " + x[i]["formId"] + ",    " +
        "Start Date: " + x[i]["startDate"] + ",    " +
        "Event Type: " + x[i]["eventType"] + ",    " +
        "Event Cost: " + x[i]["eventCost"] + ",    " +
        "Grade: " + x[i]["grade"] + ",    " +
        "Reimbursement Amount: " + x[i]["rAmt"] + ",    " +
        "Additional Reimbursement: " + x[i]["rAdditional"] + ",    " +
        "Status: " + x[i]["status"] + ",    " +
        "Number Accepted: " + x[i]["numAccepted"] + "/3" + "<br>" + "<br>"
        ); 
      }
    });
  }

  updateGrade() {

    let updgradeReturnStmt = document.getElementById("updgradeReturnStmt");
    let input = {
      "username" : localStorage.getItem("usr"),
      "formId": ((document.getElementById("fid") as HTMLInputElement).value),
      "grade": ((document.getElementById("gType") as HTMLInputElement).value)
    }
    this.httpClient.post("http://localhost:8080/TRS/updategrade", input)
    .subscribe(x => {
      updgradeReturnStmt.innerHTML = "";
      updgradeReturnStmt.insertAdjacentHTML('beforeend', x[0]["sentence"]);
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login'], {relativeTo: this.route});
  }


}
