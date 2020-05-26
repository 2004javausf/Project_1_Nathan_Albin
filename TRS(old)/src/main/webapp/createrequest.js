var df = document.referrer;    
console.log(df);
if (df == "http://localhost:8080/TRS/login") {
    console.log(df);
} else if (df == "http://localhost:8080/TRS/createrequest?") {
	console.log(df);
} else {
    window.location.href = "http://localhost:8080/TRS/";
};