

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
const userFavorites = document.getElementById('userFavorites');
userFavorites.style.display = "none";
const messageP = document.getElementById('message');
const favoriteCarIds = new Set();


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

	const seeMoreButton = document.getElementById('seeMore');
	let count = 0;
	sortDiv.style.display = "none";
    userFavorites.innerHTML = "";
    userFavorites.style.display = "none";
    carDiv.innerHTML = "";
    messageP.innerHTML = "";

    if (data.length == 0 && mode == "favorites") {
        messageP.innerHTML = "You have no favorites, try it out!";
        messageP.classList.add('show');
        sortDiv.style.display = "none";
        setTimeout(() => {
            messageP.classList.remove('show');

        }, 2000);
        return;

    }

    if (data.length == 0) {

        messageP.innerHTML = "No matching cars, try again.";
        messageP.classList.add('show');
        sortDiv.style.display = "none";

        setTimeout(() => {
            messageP.classList.remove('show');

        }, 2000);
        return;

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

        const sourceLink = document.createElement("a");
        sourceLink.innerHTML = `Source: ${car.source}`;
        carCard.appendChild(sourceLink);

        if (mode == "search" || mode == "favorites") {
            const favEachButton = document.createElement("i");
            favEachButton.classList.add("fa-solid", "fa-heart", "favorite-icon");
            favEachButton.dataset.carId = car.id;


			if (favoriteCarIds.has(car.id)) {
			    favEachButton.classList.add('active');
			}

            carCard.appendChild(favEachButton);

        }

        if (mode == "favorites") {
            carCard.classList.add('favorited');
            userFavorites.innerHTML = `${user.data.username}'s favorites:`;
            userFavorites.style.display = "block";
        }


        carDiv.appendChild(carCard)
		count++;


    });



}

/////////////////////////////////////////////////////////////////////////////////
//return Favorite button event Listener
favButton.addEventListener('click', function() {


    if (user.success == false) {
        favPopUp.textContent = `Log in or Register to unlock this feature!`;
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
				
				//store favorites here to persist
				favoriteCarIds.clear();
				data.forEach(car => favoriteCarIds.add(car.id));

                displayCars(currentCars, "favorites");
            })
            .catch(err => console.error(err));

    }

});

///////////////////////////////////////////////////////////////////////////////////////////////

//Adding a dynamic dropdown create element with data from the DB

document.addEventListener('DOMContentLoaded', async function() {

	//populate makes on page load
    fetch(makeDropUrl)
        .then(response => response.json())
        .then(data => {

            console.log(data);
            populateMakeDropdown(data);


        })
        .catch(err => console.error(err));

		//cache favorites on page load
		fetch(favoriteUrl)
		    .then(response => response.json())
		    .then(data => {
		        favoriteCarIds.clear();
		        data.forEach(car => favoriteCarIds.add(car.id));
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

        const carIdStr = e.target.dataset.carId;
		const carNum = Number(carIdStr);
        
		
       
       
        //checking current state, is it "active"
        const isActive = e.target.classList.contains('active');
        


        if (isActive) {
           
			//adding an await function, which will determine if we continue forward or not
			const confirmation = await waitForConfirmation();
			
			if(!confirmation) return;

            //if active remove favorite
            try {
                const response = await fetch(
                    `http://localhost:8080/api/removeFavorite?carId=${carNum}`, {

                    method: 'DELETE'
                });



                const result = await response.json();
                console.log(result);

                if (result.success == false) {
                    favPopUp.textContent = `${result.message}`;
                    favPopUp.classList.add("error");
                    favPopUp.classList.remove("success");
                } else {
                    favPopUp.textContent = `${result.message}`;
                    favPopUp.classList.add("success");
                    favPopUp.classList.remove("error");
					favoriteCarIds.delete(Number(carNum));
					e.target.classList.remove('active');
                }

                favPopUp.classList.add("show");

                setTimeout(() => {
                    favPopUp.classList.remove("show");
                }, 2000);
				
				




            } catch (error) {
                console.error('Error:', error);
                favPopUp.textContent = `Something went wrong`;
                favPopUp.classList.add("show");
            }

        } else {

            // if not active, add favorite
            try {
                const response = await fetch(
                    `http://localhost:8080/api/addFavorite?carId=${carNum}`,
                    {
                        method: 'POST'
                    }
                );

                const result = await response.json();
                console.log(result);

                if (result.success == false) {
                    favPopUp.textContent = `${result.message}`;
                    favPopUp.classList.add("error");
                    favPopUp.classList.remove("success");
                } else {
                    favPopUp.textContent = `${result.message}`;
                    favPopUp.classList.add("success");
                    favPopUp.classList.remove("error");
					favoriteCarIds.add(carNum);
					e.target.classList.add('active');
                }

                favPopUp.classList.add("show");

                setTimeout(() => {
                    favPopUp.classList.remove("show");
                }, 2000);

            } catch (error) {
                console.error('Error:', error);
                favPopUp.textContent = `Something went wrong`;
                favPopUp.classList.add("show");
            }
        }
    }
});
////////////////////////////////////await confirmation of deletion function///////


function waitForConfirmation(){
	return new Promise((resolve)=>{
		const confirmDeleteFav = document.getElementById('confirmDeleteFav');
		const yesButton = document.getElementById('yes');
		const noButton = document.getElementById('no');
		const overlay = document.getElementById('overlay');
		
		//display block
		confirmDeleteFav.style.display = "block";
		overlay.style.display = "block";
		
		//if yes clicked, return true aka success keep going
		//and hide display of confirmdelete div 
		yesButton.onclick = ()=>{
			resolve(true);
			confirmDeleteFav.style.display = "none";
			overlay.style.display = "none";
		}
		
		//if no clicked return false exit method. 
		noButton.onclick = ()=>{
			resolve(false);
			confirmDeleteFav.style.display = "none";
			overlay.style.display = "none";
		}
		
		
	});
		

	
	
}









////////////////////////////////////////////////////////////////////////////////////


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
                errorP.innerHTML = "Log in to save your favorite cars!"
                userDropdown.appendChild(errorP);
                //create new reg button when click on user icon, if not already 
                //logged in 
                const registerLink = document.createElement("a");
                registerLink.id = "registerLink";
                registerLink.innerHTML = "Register? ";
                const logInLink = document.createElement("a");
                logInLink.id = "loginLink";
                logInLink.innerHTML = "Login"
				const linkContainer = document.createElement("div");
				linkContainer.classList.add("link-row");

				linkContainer.appendChild(registerLink);
				linkContainer.appendChild(logInLink);

				userDropdown.appendChild(linkContainer);
                registerLink.onclick = () => {
                    window.location.href = "/register/register.html";
                };
                logInLink.onclick = () => {
                    window.location.href = "/login/login.html";
                };

               

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

                            successP.innerHTML = `Thanks for choosing Autosearch`;
                            logOutButton.style.display = "none";
							setTimeout(() => {
							        window.location.href = "/index.html";
							    }, 2000);

                        })
                };
                userDropdown.appendChild(logOutButton);

            }


        })

    userDropdown.classList.toggle("show");

    //////////////////////////////////////////////////////////////////////////////////////

})





