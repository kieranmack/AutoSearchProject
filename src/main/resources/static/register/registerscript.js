

//Javascript File for Registering User

const usernameError = document.getElementById('usernameError');
const passwordError = document.getElementById('passwordError');
const emailError = document.getElementById('emailError');
const usernameClass = document.getElementById('username');
const passClass = document.getElementById('password');
const emailClass = document.getElementById('email');
const errorFull = document.getElementById('errorFull');

//remove error stuff

usernameClass.addEventListener('input', () => {
    if (usernameClass.value.trim() !== "") {
        usernameError.style.display = "none";
        usernameClass.classList.remove('empty');
		errorFull.style.display = "none";
    }
});

passClass.addEventListener('input', () => {
    if (passClass.value.trim() !== "") {
        passwordError.style.display = "none";
        passClass.classList.remove('empty');
		errorFull.style.display = "none";
    }
});

emailClass.addEventListener('input', () => {
    if (emailClass.value.trim() !== "") {
        emailError.style.display = "none";
        emailClass.classList.remove('empty');
		errorFull.style.display = "none";
    }
});


document.getElementById('registerForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const messageP = document.getElementById('message');
    //get doc values, then append on the URL as a url search parameter
    const username = usernameClass.value;

    let isValid = true;

	//custom validation 
    if (!username) {
        usernameError.style.display = "block";
        usernameError.textContent = "Username is required.";
        usernameClass.classList.add('empty');
        isValid = false;
    }
    const password = passClass.value;
    if (!password) {
        passwordError.style.display = "block";
        passwordError.textContent = "Password is required";
        passClass.classList.add('empty');
        isValid = false;
    } else if (password.length < 8) {
        passwordError.style.display = "block";
        passwordError.textContent = "Password must exceed 8 characters";
        passClass.classList.add('empty');
        isValid = false;
    }
	
	const email = emailClass.value.trim();
	const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	
	if(!email){
		emailError.style.display = "block";
		emailError.textContent = "Email is required";
		emailClass.classList.add('empty');
		isValid = false;
	}else if(!emailPattern.test(email)){
		emailError.style.display = "block";
		emailError.textContent = "Please enter a valid email address";
		emailClass.classList.add('empty');
		isValid = false;
	}
	
	if(!isValid) return;
    

    const formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('password', password);
    formData.append('email', email);

    try {
        messageP.innerHTML = "";

        //await method, connects to controller endpoint
        const response = await fetch('/user/register/', {
            method: 'POST',
            //content in the form of URL query params, not JSOn 
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        });

        //Spring auto returns as Json, so return our custom Object as Json, then display message
        const result = await response.json();
        errorFull.textContent = result.message
        errorFull.style.display = "block";

        //redirect to login page
        if (result.success == true) {
            messageP.classList.add('success');
            setTimeout(() => {
                window.location.href = "/login/login.html";
                messageP.classList.remove('success');
            }, 2000);
        }




    } catch (error) {
        console.error('Error:', error);
        document.getElementById('message').textContent = 'Something went wrong';
    }
});