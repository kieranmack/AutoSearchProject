
//need to learn how to do basic JS GET and POSTS, my knowledge is limited we will start 
//basic

const url = new URL('http://localhost:8080/api/search/?');

url.searchParams.append('make', 'Toyota');
url.searchParams.append('model', 'Corolla');
console.log(url);

fetch(url)
    .then(response => response.json()) // converts raw JSON to JS object/array
    .then(data => {

        const returnVals = document.getElementById('message');


        returnVals.textContent = JSON.stringify(data, null, 2);
        console.log(data); // your list of cars
    })
    .catch(err => console.error(err));