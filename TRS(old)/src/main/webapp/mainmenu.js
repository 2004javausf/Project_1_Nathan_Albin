var df = document.referrer;    
console.log(df);
if (df == "http://localhost:8080/TRS/") {
    console.log(df);
} else if(df == "http://localhost:8080/TRS/createrequest") {
	console.log(df);
} else {
    window.location.href = "http://localhost:8080/TRS/";
};

function displayTime(){
var refresh=1000; 
let mytime=setTimeout('displayDate()',refresh)
}

function displayDate() {
var d = new Date()
document.getElementById('time').innerHTML = d;
displayTime();
 }