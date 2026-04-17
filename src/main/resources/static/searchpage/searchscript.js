

//Redoing javascript, can refactor into one method instead of constant event listeners
//became too much to handle 4/8/2026

//global vars
const favoriteUrl = new URL('http://localhost:8080/api/search/favorites/')
const makeDropUrl = new URL('http://localhost:8080/api/makes/');
const modelDropUrl = new URL('http://localhost:8080/api/models/?');
const loginCheckUrl = new URL('http://localhost:8080/user/logcheck');
const searchButton = document.getElementById('searchButton');
const favButton = document.getElementById('heartIcon');
const currentCars = [];
const sortDiv = document.getElementById('sortDiv');
const avgButton = document.getElementById('avgButton');
const favPopUp = document.getElementById('favPopUp');
let user = null;


///////////////////////////////////////////////////////////////////////////
////need to save instance of user.  
async function loginCheck() {
    fetch(loginCheckUrl)
        .then(response => response.json())
        .then(data => {

            //instance of user success, false = not logged, true = does. 
            user = data;
            console.log(user);
        })
}

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

            currentCars.length = 0;
            currentCars.push(...data);


            displayCars(currentCars, "search");
        })
        .catch(err => console.error(err));

});
///////////////////////////////////////////////////////////////////////////////////////////////


//displaying cars nicely
function displayCars(data, mode) {

    carDiv.innerHTML = "";

    if (data.length == 0) {
        const emptyP = document.createElement("p");
        emptyP.innerHTML = "No matching cars.";
        carDiv.appendChild(emptyP);
        sortDiv.style.display = "none";

    }

    if (mode === "search" && data.length > 0) {
        sortDiv.style.display = "flex";

    }


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

        if (mode == "search") {
            const favEachButton = document.createElement("i");
            favEachButton.classList.add("fa-solid", "fa-heart", "favorite-icon");
            favEachButton.dataset.carId = car.id;
            carCard.appendChild(favEachButton);


        }


        carDiv.appendChild(carCard)


    });



}

///////////////////////////////////////////////////////////////////////////////////////////////


//return Favorite button event Listener
favButton.addEventListener('click', function() {
	
	
    if (user.success == false) {
        favPopUp.textContent = `${user.message}`;
        favPopUp.classList.add("error");
        favPopUp.classList.remove("success");
        

		favPopUp.classList.add("show");
        setTimeout(() => {
			favPopUp.classList.remove('show');
        }, 2000);
    } else {
        fetch(favoriteUrl)

            .then(response => response.json())
            .then(data => {

                currentCars.length = 0;
                currentCars.push(...data);

                displayCars(currentCars, "favorites");
            })
            .catch(err => console.error(err));

    }

});

///////////////////////////////////////////////////////////////////////////////////////////////

//Adding a dynamic dropdown create element with data from the DB

document.addEventListener('DOMContentLoaded', async function() {

    fetch(makeDropUrl)
        .then(response => response.json())
        .then(data => {

            console.log(data);
            populateMakeDropdown(data);


        })
        .catch(err => console.error(err));


    //check for login at page load. 
    loginCheck();
});

//populate the dropdown when the DOM is loaded, works. 
function populateMakeDropdown(data) {

    const makeDropdown = document.getElementById('makeSelect');
    data.forEach(make => {

        const option = new Option(make, make);

        makeDropdown.appendChild(option);


    });


}

//////////////////////////////////////////////////////////////////////////

//need a listener for change of Make, so we know what Models to populate dropdown with. 

const makeDropdown = document.getElementById('makeSelect');

makeDropdown.addEventListener('change', function() {

    modelDropUrl.searchParams.set('make', makeDropdown.value);
    fetch(modelDropUrl)
        .then(response => response.json())
        .then(data => {

            console.log(data);
            populateModelDropdown(data);




        })
        .catch(err => console.error(err));


});

////////////////////////////////////////////////////////////////////////
//function to populate model dropdown

function populateModelDropdown(data) {

    const modelDropdown = document.getElementById('modelSelect');
    //while loop removes elements, but keeps default. 
    while (modelDropdown.options.length > 1) {
        modelDropdown.remove(1);
    }

    data.forEach(model => {

        const option = new Option(model, model);

        modelDropdown.appendChild(option);


    });

}
///////////////////////////////////////////////////////////////////////////////////
///Sort Function Below based on value of select.


const sortSelect = document.getElementById('sortSelect');

sortSelect.addEventListener('change', function() {

    const option = document.getElementById('sortSelect').value;

    if (option == "prhl") {
        currentCars.sort((a, b) => b.price - a.price);
    } else if (option == "prlh") {
        currentCars.sort((a, b) => a.price - b.price);
    } else if (option == "mihl") {
        currentCars.sort((a, b) => b.mileage - a.mileage);
    } else if (option == "milh") {
        currentCars.sort((a, b) => a.mileage - b.mileage);
    } else if (option == "yrhl") {
        currentCars.sort((a, b) => Number(b.year) - Number(a.year));

    } else if (option == "yrlh") {
        currentCars.sort((a, b) => Number(a.year) - Number(b.year));

    }
    displayCars(currentCars, "search");



});

/////////////////////////////////////////////////////////////////
////adding avg button



avgButton.addEventListener('click', function() {
    const sum = [];
    const avgValueP = document.getElementById('AvgCalc')

    currentCars.forEach(car => {

        sum.push(car.price);

    })

    const sumValue = sum.reduce((partialSum, a) => partialSum + a, 0);

	const avg = sumValue / currentCars.length;
    avgValueP.innerHTML = `Average Price of Selected Cars: $${avg.toFixed(2)}`;

})

//////////////////////////////////////////////////////////////////////////////

////Button for favoriting cars


carDiv.addEventListener('click', async function(e) {



    if (e.target.classList.contains('favorite-icon')) {
        const carId = e.target.dataset.carId;
		

        try {
            //await method, connects to controller endpoint
            const response = await fetch(`http://localhost:8080/api/addFavorite?carId=${carId}`, {
                method: 'POST',

            });

            const result = await response.json();

            console.log(result);

            if (result.success == false) {
                favPopUp.textContent = `Error: ${result.message}`;
                favPopUp.classList.add("error");
                favPopUp.classList.remove("success");
            } else if (result.success == true) {
                favPopUp.textContent = `${result.message}`;
                favPopUp.classList.add("success");
                favPopUp.classList.remove("error")

            }
			
			favPopUp.classList.add('show');

           

            setTimeout(() => {
                favPopUp.classList.remove("show");
            }, 2000);


        } catch (error) {
            console.error('Error:', error);
            favPopUp.textContent = `Something went wrong`;
        }

    }

})


//////////////////////////////////////////////////////////////////////
/////Adding checking for login and populating userdropdown menu/////////////////////////////////////


const userIcon = document.getElementById('userIcon');
const userDropdown = document.getElementById('userDropdown');

userIcon.addEventListener('click', function() {

    userDropdown.innerHTML = "";

    fetch('http://localhost:8080/user/logcheck', {
        credentials: "include"
    })

        .then(response => response.json())
        .then(data => {
            if (data.success == false) {
                //if it's a fail, then we prompt user to register
                const errorP = document.createElement("p");
                errorP.innerHTML = "Log in To save your Favorite Cars!"
                userDropdown.appendChild(errorP);
                //create new reg button when click on user icon, if not already 
                //logged in 
                const registerLink = document.createElement("a");
                registerLink.id = "registerLink";
                registerLink.innerHTML = "Register? ";
                const logInLink = document.createElement("a");
                logInLink.id = "loginLink";
                logInLink.innerHTML = "Login"
                registerLink.onclick = () => {
                    window.location.href = "/register/register.html";
                };
                logInLink.onclick = () => {
                    window.location.href = "/login/login.html";
                };

                userDropdown.appendChild(registerLink);
                userDropdown.appendChild(logInLink);

            } else if (data.success == true) {
                const successP = document.createElement("p");
                successP.innerHTML = `Welcome, ${data.data.username}<br>`;
                userDropdown.appendChild(successP);
                const logOutButton = document.createElement('button');
                logOutButton.id = "logoutButton";
                logOutButton.innerHTML = "Logout";

                logOutButton.onclick = () => {
                    fetch('http://localhost:8080/user/logout')
                        .then(response => response.json())
                        .then(data => {

                            successP.innerHTML = `Thanks for choosing Autosearch ${data.message}`;
							logOutButton.style.display = "none";

                        })
                };
                userDropdown.appendChild(logOutButton);

            }


        })

    userDropdown.classList.toggle("show");

    //////////////////////////////////////////////////////////////////////////////////////

})





