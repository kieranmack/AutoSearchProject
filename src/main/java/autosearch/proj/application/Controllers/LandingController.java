package autosearch.proj.application.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

	@RequestMapping
	public String landingPage() {
		return "redirect:/register.html";
	}
}
