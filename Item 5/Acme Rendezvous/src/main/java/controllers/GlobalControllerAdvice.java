package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import services.WelcomeService;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@Autowired
	private WelcomeService	welcomeService;

	@ModelAttribute("bannerWelcome")
	public String populateUser() {
		final String banner = this.welcomeService.find().getBanner();
		return banner;
	}

}
