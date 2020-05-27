import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-menuben',
  templateUrl: './menuben.component.html',
  styleUrls: ['./menuben.component.css']
})
export class MenubenComponent implements OnInit {

  constructor(private httpClient : HttpClient) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"));
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
        "Username: " + x[i]["username"] + ",    " +
        "Event Type: " + x[i]["eventType"] + ",    " +
        "Event Cost: " + x[i]["eventCost"] + "<br>"
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

}
