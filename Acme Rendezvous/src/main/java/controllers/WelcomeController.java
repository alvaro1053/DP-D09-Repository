/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.WelcomeService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	WelcomeService welcomeService;
	
	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@CookieValue(value = "language" ,defaultValue = "")String cookieValue ) {

		ModelAndView result;

		final String welcomeMessage;
		final String name;



		if (cookieValue.endsWith("s")){
			welcomeMessage = this.welcomeService.find().getSpanishWelcome();
		}else{
			welcomeMessage = this.welcomeService.find().getEnglishWelcome();
		}
		
		
		name = this.welcomeService.find().getName();

		result = new ModelAndView("welcome/index");
		result.addObject("welcomeMessage", welcomeMessage);
		result.addObject("name", name);

		return result;

	}
	
}
