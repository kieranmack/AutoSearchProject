
//need to learn how to do basic JS GET and POSTS, my knowledge is limited we will start 
//basic

const url = new URL('http://localhost:8080/api/search/?');
const favoriteUrl = new URL('http://localhost:8080/api/search/favorites/')
const searchButton = document.getElementById('searchButton');
const favButton = document.getElementById('favoritesButton');

//dropdowns, if changed value, append URL search.

//need to make it so they delete the search if going back to default value. 
const makeDropdown = document.getElementById('makeSelect');

makeDropdown.addEventListener('change', function() {

    const selectedValue = makeDropdown.value;

    if (selectedValue == "default") {
        url.searchParams.delete('make');
    } else

        url.searchParams.set('make', selectedValue);
    console.log(url);

});

const modelDropdown = document.getElementById('modelSelect');

modelDropdown.addEventListener('change', function() {
    const selectedValue = modelDropdown.value;

    if (selectedValue == "default") {
        url.searchParams.delete('model')
    } else

        url.searchParams.set('model', selectedValue);
    console.log(url);

});

const yearDropdown = document.getElementById('yearSelect');

yearDropdown.addEventListener('change', function() {


    const [minYear, maxYear] = yearDropdown.value.split("-");
    if (yearDropdown.value == "default") {
        url.searchParams.delete('minYear');
        url.searchParams.delete('maxYear');
    } else

        url.searchParams.set('minYear', minYear);
    url.searchParams.set('maxYear', maxYear);
    console.log(url);

});

searchButton.addEventListener('click', function() {
    fetch(url)
        .then(response => response.json()) // converts raw JSON to JS object/array
        .then(data => {

            const returnVals = document.getElementById('message');


            returnVals.textContent = JSON.stringify(data, null, 2);
            console.log(data);
        })
        .catch(err => console.error(err));

});

favButton.addEventListener('click', function() {

    fetch(favoriteUrl)
        .then(response => response.json())
        .then(data => {
            const returnVals = document.getElementById('message');

            returnVals.textContent = JSON.stringify(data, null, 2);
        })
        .catch(err => console.error(err));

});



