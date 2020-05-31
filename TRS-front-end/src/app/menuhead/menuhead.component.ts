import { Component, OnInit } from '@angular/core';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-menuhead',
  templateUrl: './menuhead.component.html',
  styleUrls: ['./menuhead.component.css']
})
export class MenuheadComponent implements OnInit, RouterModule {

  constructor(
    private router : Router,
    private route : ActivatedRoute,
    private httpClient : HttpClient) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"));
    let j = localStorage.getItem("usr");
    if(j == null) {
      this.router.navigate(['/login'], {relativeTo: this.route});
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login'], {relativeTo: this.route});
  }

  toggle() {
    var x = document.getElementById("adoffer");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else if (x.style.display == "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }

  getAllOffers() {
    let offerinfo = document.getElementById("offerinfo");
    var z = document.getElementById("offerinfo");
    if (z.style.display == "none") {
      z.style.display = "block";
    } else if (z.style.display == "block") {
      z.style.display = "none";
    } else {
      z.style.display = "block";
    }
    this.httpClient.get("http://localhost:8080/TRS/application").subscribe(x => {
      console.log(x);
      let i = 0;
      offerinfo.innerHTML = "";
      for(i = 0; i < Object.keys(x).length; i++) {
        offerinfo.insertAdjacentHTML('beforeend', 
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
  acceptDenyOffer() {
    var info = {
      "formId": ((document.getElementById("formId") as HTMLInputElement).value),
      "acceptDeny": ((document.getElementById("acceptordeny") as HTMLInputElement).value),
      "accType": localStorage.getItem("acctiep"),
      "reason": ((document.getElementById("reason") as HTMLInputElement).value)
    }

    this.httpClient.post("http://localhost:8080/TRS/acceptdeny", info).subscribe(x => {
      console.log(x);


    });
  }

  goToUserMenu() {
    this.router.navigate(['/menu'], {relativeTo: this.route});
  }




}
