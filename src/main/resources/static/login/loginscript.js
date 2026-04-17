

//Javascript File for login User
document.addEventListener('DOMContentLoaded', function(){
	const loginButton = document.getElementById('loginButton');
	loginButton.style.display = "none";
})
	document.getElementById('loginForm').addEventListener('submit', async function(event) {
   	 	event.preventDefault(); 
		
		const username = document.getElementById('username').value;
		const password = document.getElementById('password').value;
		
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
		
			 
		if(result.success == false){
			 document.getElementById('message').textContent = result.message;
		}else if(result.message == "Admin"){
			window.location.href = "/admindash/admindashboard.html/";
		}else
			window.location.href = "/searchpage/search.html";
			
		
			 } catch (error) {
			         console.error('Error:', error);
			         document.getElementById('message').textContent = 'Something went wrong';
			     }
			 });	 
