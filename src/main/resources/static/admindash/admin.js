/// Javascript for Admin Dashboard




//building timer
function updateCountdown() {

    const now = new Date();
    const nextEvent = new Date();
    nextEvent.setHours(24, 0, 0, 0);

    const diff = nextEvent - now;

    const hours = Math.floor(diff / (1000 * 60 * 60));
    const minutes = Math.floor((diff / (1000 * 60)) % 60);
    const seconds = Math.floor((diff / 1000) % 60);

    const timer = document.getElementById('timer');

    timer.innerHTML = `Next Scrape In:<br> ${hours}:${minutes}:${seconds}`;

}

//runs every second to update countdown
setInterval(updateCountdown, 1000);
updateCountdown();


const manualScrapeBtn = document.getElementById('manualTrigger');

	manualScrapeBtn.addEventListener('click', function(){
		
		fetch('http://localhost:8080/adminscrape')
		.then(response => response.json()) // converts raw JSON to JS object/array
		        .then(data => {
		
					console.log(data);
	})
	

	
})











