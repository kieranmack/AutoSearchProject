
//need to learn how to do basic JS GET and POSTS, my knowledge is limited we will start 
//basic

const url = new URL('http://localhost:8080/api/search/?');
const searchButton = document.getElementById('searchButton');

const makeDropdown = document.getElementById('makeSelect');

	makeDropdown.addEventListener('change', function() {

    	const selectedValue = makeDropdown.value;
    	url.searchParams.set('make', selectedValue);
    	console.log(url);

});

const modelDropdown = document.getElementById('modelSelect');

	modelDropdown.addEventListener('change', function(){
		const selectedValue = modelDropdown.value;
		url.searchParams.set('model', selectedValue);
		console.log(url);
		
});

searchButton.addEventListener('click', function(){
	fetch(url)
	    .then(response => response.json()) // converts raw JSON to JS object/array
	    .then(data => {

	        const returnVals = document.getElementById('message');


	        returnVals.textContent = JSON.stringify(data, null, 2);
	        console.log(data); 
	    })
	    .catch(err => console.error(err));
	
});



    