
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Rende;
import domain.Manager;
import forms.RendeForm;

@Controller
@RequestMapping("/rende/manager")
public class RendeManagerController extends AbstractController {

	// Services

	@Autowired
	private RendeService	rendeService;

	@Autowired
	private ManagerService		managerService;


	// Constructors

	public RendeManagerController() {
		super();
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendeId) {
		ModelAndView result;
		final String uri = "/manager";
		Rende rende = new Rende();
		final Manager principal = this.managerService.findByPrincipal();
		Boolean permisos = true;
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		rende = this.rendeService.findOne(rendeId);
		if ((años < 18) && rende.getAdultOnly() == true)
			permisos = false;

		result = new ModelAndView("rende/display");
		result.addObject("rende", rende);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		return result;
	}
}