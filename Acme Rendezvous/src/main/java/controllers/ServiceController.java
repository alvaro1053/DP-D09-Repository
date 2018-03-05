
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ManagerService;
import services.ServiceService;
import domain.Actor;
import domain.Manager;
import domain.Service;

@Controller
@RequestMapping("/service")
public class ServiceController extends AbstractController {

	// Services

	@Autowired
	private ServiceService	serviceService;
	
	@Autowired
	private ActorService	actorService;


	// Constructors

	public ServiceController() {
		super();
	}

	// Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Manager manager = new Manager();
		Actor actor = this.actorService.findByPrincipal();
		if(actor instanceof Manager){
		manager = (Manager) actor;
		}
		Collection<Service> services;
		services = this.serviceService.findAll();
		result = new ModelAndView("service/list");
		result.addObject("principal", manager);
		result.addObject("services", services);
		return result;
	}

}
