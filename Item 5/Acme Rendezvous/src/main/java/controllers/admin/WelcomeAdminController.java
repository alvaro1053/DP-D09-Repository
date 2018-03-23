package controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Admin;
import domain.WelcomePage;

import services.AdminService;
import services.WelcomeService;


@Controller
@RequestMapping("welcome/admin")
public class WelcomeAdminController extends AbstractController {

	
	@Autowired
	WelcomeService welcomeService;
	
	@Autowired
	AdminService adminService;
	
	
	public WelcomeAdminController(){
		
	}
	
	@RequestMapping(value="/customisation", method= RequestMethod.GET)
	public ModelAndView customization(){
		ModelAndView result;
		final Admin principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);
		
		WelcomePage welcome;
		
		welcome = this.welcomeService.find();
		Assert.notNull(welcome);
		
		result = this.createEditModelAndView(welcome);
		return result;
		
		
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@Valid final WelcomePage welcomePage, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(welcomePage);
		else
			try {
				this.welcomeService.save(welcomePage);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(welcomePage, "welcome.commit.error");
			}
		return result;
	}
	// Ancillary methods ------------------------------------------------------

		protected ModelAndView createEditModelAndView(final WelcomePage welcomePage) {
			ModelAndView result;
			result = this.createEditModelAndView(welcomePage, null);

			return result;
		}
		protected ModelAndView createEditModelAndView(final WelcomePage welcomePage, final String message) {
			ModelAndView result;

			result = new ModelAndView("welcome/edit");
			result.addObject("welcomePage", welcomePage);
			result.addObject("message", message);
			return result;
		}
	
}
