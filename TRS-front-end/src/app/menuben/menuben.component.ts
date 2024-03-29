import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-menuben',
  templateUrl: './menuben.component.html',
  styleUrls: ['./menuben.component.css']
})
export class MenubenComponent implements OnInit, RouterModule {

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

  showProcessInput(){
    var x = document.getElementById("papp");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else if (x.style.display == "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }

  showChReAmt() {
    var x = document.getElementById("chreamt");
    if (x.style.display == "none") {
      x.style.display = "block";
    } else if (x.style.display == "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }

  processReim() {
    
    let rstatement = document.getElementById("rstatement");
    let input = {
      "formId": ((document.getElementById("ffid") as HTMLInputElement).value),
      "addReim" : ((document.getElementById("addreim") as HTMLInputElement).value),
      "acceptDeny": ((document.getElementById("processorno") as HTMLInputElement).value),
      "reason": ((document.getElementById("reeson") as HTMLInputElement).value)
    }
    this.httpClient.post("http://localhost:8080/TRS/processapp", input)
    .subscribe(x => {
      rstatement.innerHTML = x[0]["sentence"];

    }); 


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
    let returnstmt = document.getElementById("returnstmt");
    var info = {
      "formId": ((document.getElementById("formId") as HTMLInputElement).value),
      "acceptDeny": ((document.getElementById("acceptordeny") as HTMLInputElement).value),
      "accType": localStorage.getItem("acctiep"),
      "reason": ((document.getElementById("reason") as HTMLInputElement).value)
    }

    this.httpClient.post("http://localhost:8080/TRS/acceptdeny", info).subscribe(x => {
      returnstmt.innerHTML = x[0]["sentence"];


    });
  }

  changeReimbursement() {
    let chrestmt = document.getElementById("chrestmt");
    let info = {
      "formId" : ((document.getElementById("fffid") as HTMLInputElement).value),
      "changeAmt" : ((document.getElementById("reamount") as HTMLInputElement).value)
    }
    this.httpClient.post("http://localhost:8080/TRS/changereamt", info)
    .subscribe(x => {
      chrestmt.innerHTML = x[0]["sentence"];
    });

  }


  goToUserMenu() {
    this.router.navigate(['/menu'], {relativeTo: this.route});
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login'], {relativeTo: this.route});
  }


}
