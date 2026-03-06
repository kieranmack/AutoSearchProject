package autosearch.proj.application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class TestBasicScrapingLogic {

	@Test
	public void testScrape() throws IOException {
		//File object which reads the classpath and grabs the matching file
		File file = new ClassPathResource("static/siteA.html").getFile();
		
		//check if file exists, if doesn;t break the logic so as to not break anything
		if(!file.exists()) {
			System.out.println("File not found.");
			return;
		}
		
		//parse the file for it's contents
		
		Document doc = Jsoup.parse(file, "UTF-8");
		
		
		//We now have content, select the heading, then print
		String headerText = doc.selectFirst("h2").text();
		System.out.println("Header: " + headerText);
		
		//testing to grab div using selectFirst tab
		String firstListing = doc.selectFirst("div").text();
		System.out.println("First listing (test): " + firstListing);
		
		//Now we will test to see if we can use for each loop using Jsoups element object
		//and succesfully check through each div.  
		
		Elements divs = doc.select("div");
		//Elements pulls everything that is annotated with it's respective tag, "p" or "div" 
		//in this case
		Elements paragraph = doc.select("p");
		
		for(Element para: paragraph ) {
			System.out.println("Paragraphs: " + para.text());
		}
		
		//success, nice all there needs to be done is to create static sites, and then 
		//populate raw car objects
		for(Element div : divs) {
			System.out.println("Div: " + div.text());
		}
		
		
		
		
		
	}
}
