
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Request;
import domain.Manager;

@Controller
@RequestMapping("/request/manager")
public class RequestManagerController extends AbstractController {

	// Services
	@Autowired
	private RequestService	requestService;

	@Autowired
	private ManagerService		managerService;

	


	// Constructors

	public RequestManagerController() {
		super();
	}


	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> res = new ArrayList<Request>();
		final String uri = "/manager";
		try {
			final Manager principal = this.managerService.findByPrincipal();
			res = this.requestService.findByManagerId(principal.getId());
		} catch (final Throwable oops) {
			final String message = "request.error";
			result = this.CreateListModelAndView(res, message);
		}
		result = new ModelAndView("request/list");
		result.addObject("uri", uri);
		result.addObject("requests", res);

		return result;
	}

	

	

	// Ancillary methods ------------------------------------------------------
	private ModelAndView CreateListModelAndView(Collection<Request> res,
			String message) {
		String uri = "/manager";
		ModelAndView result = new ModelAndView("request/list");
		result.addObject("uri", uri);
		result.addObject("requests", res);
		result.addObject("message", message);
		return null;
	}
}
