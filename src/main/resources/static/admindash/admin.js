/// Javascript for Admin Dashboard

const adminCarUrl = new URL('http://localhost:8080/admin/getcars');
const statsUrl = new URL('http://localhost:8080/admin/stats');
const table = document.querySelector("#carTable tbody");
const scrapeSummary = document.getElementById('scrapeSummary');
let lastCompletedDate = null;

//load the most recent 10 cars into the page. 

function loadCars() {
    table.innerHTML = "";
    fetch(adminCarUrl)
        .then(response => response.json())
        .then(cars => {

			

            cars.forEach(car => {
				const date = new Date(car.dateAdded);
				const formattedDate = date.toLocaleDateString();
                const row = `
		                   <tr>
		                       <td>${car.make}</td>
		                       <td>${car.model}</td>
		                       <td>${car.year}</td>
		                       <td>$${car.price}</td>
							   <td>${formattedDate}</td>
		                   </tr>
		               `;
                table.innerHTML += row;
            });
        });
}


function loadStats() {
    //load stats ie, how much of each entity do we have. 
    fetch(statsUrl)
        .then(response => response.json())
        .then(data => {
            const userP = document.getElementById('userCount');
            userP.textContent = data.userCount;
            const carP = document.getElementById('carCount');
            carP.textContent = data.carCount;
            const favP = document.getElementById('favCount');
            favP.textContent = data.favoriteCount;
        })
}


document.addEventListener('DOMContentLoaded', function() {
    loadCars();
    loadStats();

})


//building timer
function updateCountdown() {

    const now = new Date();
    const nextEvent = new Date();
    nextEvent.setHours(24, 0, 0, 0);

    const diff = nextEvent - now;

    const hours = Math.floor(diff / (1000 * 60 * 60));
    const minutes = Math.floor((diff / (1000 * 60)) % 60);
    const seconds = Math.floor((diff / 1000) % 60);
	
	const hoursStr = String(hours).padStart(2, '0');
	const minutesStr = String(minutes).padStart(2, '0');
	const secondsStr = String(seconds).padStart(2, '0');

    const timer = document.getElementById('timer');

    timer.innerHTML = `Next Scrape In:<br> ${hoursStr}:${minutesStr}:${secondsStr}`;

}

//runs every second to update countdown
setInterval(updateCountdown, 1000);
updateCountdown();

//function to update scrape info

async function loadScrapeData() {


    fetch('http://localhost:8080/scrapesummary')
        .then(response => response.json()) // converts raw JSON to JS object/array
        .then(data => {

			if(data.completedDate === lastCompletedDate){
				return;
			}
			
			lastCompletedDate = data.completedDate;

            //reload data and stats after every scrape
            loadCars();
            loadStats();
            manualScrapeBtn.disabled = false;
            manualScrapeBtn.textContent = "Trigger Scrape";

            const date = new Date(data.completedDate);

            const formattedDate = date.toLocaleString();

            scrapeSummary.textContent = `Last Recorded Scrape: Cars Added: ${data.carsAdded} | Time Allotted:
						${data.timeTaken}s | Completed Date: ${formattedDate}`;

        })

}

//constantly asking backend for updates, so the scheduling trigger is also displayed
setInterval(() => {
    loadScrapeData();
}, 5000);

const manualScrapeBtn = document.getElementById('manualTrigger');

manualScrapeBtn.addEventListener('click', function() {

    manualScrapeBtn.disabled = true;
    manualScrapeBtn.textContent = "Scraping...";

    fetch('http://localhost:8080/adminscrape')
	 .then(() => loadCars(), loadScrapeData());

})















