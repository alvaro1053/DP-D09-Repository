
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.RequestService;
import services.ServiceService;
import services.UserService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Request;
import domain.Service;
import domain.User;

@Controller
@RequestMapping("/request/user")
public class RequestUserController extends AbstractController {

	// Services

	@Autowired
	private ServiceService	serviceService;
	
	@Autowired
	private RequestService	requestService;

	@Autowired
	private UserService		userService;

	


	// Constructors

	public RequestUserController() {
		super();
	}

	// Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int serviceId, @CookieValue(value = "CreditCard" ,defaultValue = "0")String cookie, RedirectAttributes redir) {
		ModelAndView result;
		Request request = new Request();
		try{
		this.userService.findByPrincipal();
		Service service = this.serviceService.findOne(serviceId);
		request.setService(service);
		
		if(!cookie.equals("0")){
		CreditCard creditCard = this.requestService.reconstructCreditCard(cookie);
		request.setCreditCard(creditCard);
		}
		
		result = createEditModelAndView(request);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:../../service/list.do");
			redir.addFlashAttribute("message", "service.permision");
		}
		
		

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Request request, final BindingResult binding, HttpServletResponse response) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(request);
		} else
			try {
				this.requestService.save(request);
				result = new ModelAndView("redirect:../../service/list.do");
				String cookie = request.getCreditCard().getHolderName() + "." + request.getCreditCard().getBrandName() + "." + 
				request.getCreditCard().getNumber() + "."+ 
				request.getCreditCard().getExpirationMonth()+ "." + request.getCreditCard().getExpirationYear() + "." +request.getCreditCard().getCVV();
				response.addCookie(new Cookie("CreditCard",cookie));
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(request, "request.commit.error");
			}

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> res = new ArrayList<Request>();
		final String uri = "/user";
		try {
			final User principal = this.userService.findByPrincipal();
			res = this.requestService.findByUserId(principal.getId());
		} catch (final Throwable oops) {
			final String message = "request.error";
			result = this.CreateListModelAndView(res, message);
		}
		result = new ModelAndView("request/list");
		result.addObject("uri", uri);
		result.addObject("requests", res);

		return result;
	}

	

	private ModelAndView CreateListModelAndView(Collection<Request> res,
			String message) {
		String uri = "/user";
		ModelAndView result = new ModelAndView("request/list");
		result.addObject("uri", uri);
		result.addObject("requests", res);
		result.addObject("message", message);
		return null;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Request request) {
		ModelAndView result;

		result = this.createEditModelAndView(request,null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request request, final String message) {
		final ModelAndView result;
		User principal = this.userService.findByPrincipal();
		result = new ModelAndView("request/edit");
		result.addObject("request", request);
		result.addObject("message", message);
		result.addObject("rendes", principal.getRendes());

		return result;
	}
}
