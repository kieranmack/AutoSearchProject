

//Javascript File for login User

const messageP = document.getElementById('message');
const errorUsername = document.getElementById('errorUsername'); 
const errorPassword = document.getElementById('errorPassword');
const usernameClass = document.getElementById('username');
const passClass = document.getElementById('password');
const errorFull = document.getElementById('errorFull');


document.addEventListener('DOMContentLoaded', function() {
    const loginButton = document.getElementById('loginButton');
    loginButton.style.display = "none";
	errorFull.style.display = "none";
})

//remove errors on change
usernameClass.addEventListener('input', () => {
    if (usernameClass.value.trim() !== "") {
        errorUsername.style.display = "none";
        usernameClass.classList.remove('empty');
		errorFull.style.display = "none";
		
    }
});

passClass.addEventListener('input', () => {
    if (passClass.value.trim() !== "") {
        errorPassword.style.display = "none";
        passClass.classList.remove('empty');
    }
});



document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    messageP.innerHTML = "";

    const username = usernameClass.value;
    

	let isValid = true;

	if (!username) {
	    errorUsername.style.display = "block";
		errorUsername.textContent = "Username required"
	    usernameClass.classList.add('empty');
	    isValid = false;
	}
	const password = passClass.value;

	if (!password) {
	    errorPassword.style.display = "block";
		errorPassword.textContent = "Password required"
	    passClass.classList.add('empty');
	    isValid = false;
	}
	

	if (!isValid) return;
	


    const formData = new URLSearchParams();


    formData.append('username', username);
    formData.append('password', password);

    try {
        //await method, connects to controller endpoint
        const response = await fetch('/user/login/', {
            method: 'POST',
            //content in the form of URL query params, not JSOn 
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        });

        const result = await response.json();


        if (result.success == false) {
			errorFull.style.display = "block";
			errorFull.textContent = "Username or password incorrect"
			
            
        } else if (result.message == "Admin") {
            window.location.href = "/admindash/admindashboard.html/";
        } else
            window.location.href = "/searchpage/search.html";


    } catch (error) {
        console.error('Error:', error);
        messageP.innerHTML = 'Something went wrong';
    }
});	 
