

//Redoing javascript, can refactor into one method instead of constant event listeners
//became too much to handle 4/8/2026

const favoriteUrl = new URL('http://localhost:8080/api/search/favorites/')
const makeDropUrl = new URL('http://localhost:8080/api/makes');
const searchButton = document.getElementById('searchButton');
const favButton = document.getElementById('favoritesButton');


//search button event listener, will combine all previous methods here to streamline
/////////////////////////////////////////////////////////////////////////////////////
searchButton.addEventListener('click', function() {

    const url = new URL('http://localhost:8080/api/search/?');
    const make = document.getElementById('makeSelect').value;
    const model = document.getElementById('modelSelect').value;
    const year = document.getElementById('yearSelect').value;

    //if not default value append it's value to the search param
    if (make !== 'default') {
        url.searchParams.set('make', make);
    }

    if (model !== 'default') {
        url.searchParams.set('model', model);
    }

    if (year !== 'default') {
        const [minYear, maxYear] = year.split("-");
        url.searchParams.set('minYear', minYear);
        url.searchParams.set('maxYear', maxYear);
    }

    const priceDetermination = document.getElementById('priceSelect').value;
    const priceValue = document.getElementById('priceValue').value;

    if (priceValue == "") {
        url.searchParams.set('minPrice', 0);
    } else if (priceDetermination == "greater") {
        url.searchParams.set('minPrice', priceValue);
    } else if (priceDetermination == 'less') {
        url.searchParams.set('maxPrice', priceValue);
    } else if (priceDetermination == "equal") {
        url.searchParams.set('minPrice', priceValue);
        url.searchParams.set('maxPrice', priceValue);

    }

    const mileageDetermination = document.getElementById('mileageSelect').value;
    const mileageValue = document.getElementById('mileageValue').value;

    if (mileageValue == "") {
        url.searchParams.set('minMileage', 0);
    } else if (mileageDetermination == 'greater') {
        url.searchParams.set('minMileage', mileageValue);
    } else if (mileageDetermination == 'less') {
        url.searchParams.set('maxMileage', mileageValue);
    } else if (mileageDetermination == 'equal') {
        url.searchParams.set('minMileage', mileageValue);
        url.searchParams.set('maxMileage', mileageValue);
    }

    fetch(url)
        .then(response => response.json()) // converts raw JSON to JS object/array
        .then(data => {


            console.log(data);
            displayCars(data);
        })
        .catch(err => console.error(err));

});
///////////////////////////////////////////////////////////////////////////////////////////////


//displaying cars nicely
function displayCars(data) {

    carDiv.innerHTML = "";
    data.forEach(car => {


        //for each car in the list, createElements and add it to the div
        const carCard = document.createElement("div");
        carCard.classList.add("car-card");

        const makeP = document.createElement("p");
        makeP.innerHTML = `Make: ${car.make}`;
        carCard.appendChild(makeP);

        const modelP = document.createElement("p");
        modelP.innerHTML = `Model: ${car.model}`;
        carCard.appendChild(modelP);

        const yearP = document.createElement("p");
        yearP.innerHTML = `Year: ${car.year}`;
        carCard.appendChild(yearP);



        const mileageP = document.createElement("p");
        mileageP.innerHTML = `Mileage: ${car.mileage}`;
        carCard.appendChild(mileageP);

        const priceP = document.createElement("p");
        priceP.innerHTML = `Price: $${car.price}`;
        carCard.appendChild(priceP);




        carDiv.appendChild(carCard)
    });

    ///////////////////////////////////////////////////////////////////////////////////////////////


}

//Favorite button event Listener
favButton.addEventListener('click', function() {

    const returnVals = document.getElementById('message');

    fetch(favoriteUrl)

        .then(response => response.json())
        .then(data => {


            //have to refactor this to display like the other function did. 
            returnVals.textContent = JSON.stringify(data, null, 2);
        })
        .catch(err => console.error(err));

});

///////////////////////////////////////////////////////////////////////////////////////////////

//Adding a dynamic dropdown create element with data from the DB

document.addEventListener('DOMContentLoaded', function() {

    fetch(makeDropUrl)
        .then(response => response.json())
        .then(data => {

			console.log(data);
            populateMakeDropdown(data);


        })
        .catch(err => console.error(err));


});

//populate the dropdown when the DOM is loaded, works. 
function populateMakeDropdown(data) {

	const makeDropdown = document.getElementById('makeSelect');
    data.forEach(make => {
       
			const option = new Option(make, make);
			
			makeDropdown.appendChild(option);


    });




}





