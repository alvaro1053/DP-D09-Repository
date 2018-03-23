
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ManagerService;
import domain.Actor;
import domain.Manager;
import forms.ActorForm;

@Controller
@RequestMapping("/manager")
public class ManagerRegisterController {

	@Autowired
	private ManagerService	managerService;
	
	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Actor principal = null;
		Boolean permiso;
		final ActorForm actorForm = new ActorForm();
		result = this.createEditModelAndView(actorForm);
		try {
		principal = this.actorService.findByPrincipal();
		} catch (final RuntimeException oops) {	
		}
		if (principal == null) {
			permiso = true;
			result.addObject("permiso", permiso);
		} else {
			permiso = false;
			result.addObject("permiso", permiso);
		}
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager = new Manager();
		manager = this.managerService.reconstruct(actorForm, binding);
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(actorForm);
			result.addObject("permiso", true);
		} else
			try {
				this.managerService.save(manager);
				result = new ModelAndView("redirect:../");
			} catch (final DataIntegrityViolationException oops) {
				binding.rejectValue("userAccount.username", "user.username.error");
				result = this.createEditModelAndView(actorForm);
				result.addObject("permiso", true);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actorForm, "user.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("manager/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		return result;
	}
}
