

//Javascript File for Registering User
	document.getElementById('registerForm').addEventListener('submit', async function(event) {
   	 	event.preventDefault(); 
		const messageP = document.getElementById('message');
	//get doc values, then append on the URL as a url search parameter
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
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
       messageP.innerHTML = result.message;
	   messageP.classList.add('show');
	   
	   setTimeout(() => {
	   					messageP.classList.remove('show');
	   				
	   		        }, 2000);
		
	//redirect to login page
		if(result.success == true){
			window.location.href = "/login/login.html";
		}
		
		
	
		
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('message').textContent = 'Something went wrong';
    }
});