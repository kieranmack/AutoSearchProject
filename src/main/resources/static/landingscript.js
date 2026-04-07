//Javascript for Landing Screen Buttons


const loginButton = document.getElementById('loginButton').addEventListener('click', function() {
    window.location.href = "/login/login.html";
});

const registerButton = document.getElementById('registerButton').addEventListener('click', function() {
    window.location.href = "/register/register.html";
});

const guestButton = document.getElementById('guestButton').addEventListener('click', function() {
    window.location.href = "/searchpage/search.html";
});