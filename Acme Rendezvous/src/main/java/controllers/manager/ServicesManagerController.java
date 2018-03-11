
package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.ManagerService;
import services.ServiceService;
import domain.Actor;
import domain.Category;
import domain.Manager;

import domain.Service;
import forms.ServiceForm;


@Controller
@RequestMapping("/manager/service")
public class ServicesManagerController {
	// Services

	@Autowired
	private ServiceService	serviceService;
	
	@Autowired
	private ActorService	actorService;
	
	@Autowired
	private ManagerService	managerService;
	
	@Autowired
	private CategoryService	categoryService;
	



	// Constructors

	public ServicesManagerController() {
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
	
	// Creation 
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ServiceForm serviceForm;
		this.managerService.findByPrincipal();
		serviceForm = this.serviceService.create();
		result = this.createEditModelAndView(serviceForm);

		return result;
	}

	@RequestMapping(value="/edit", method= RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int serviceId){
		ModelAndView result;
		Service service;
		
		service = this.serviceService.findOne(serviceId);
		
		result = this.createEditModelAndView(service);
		
		return result;
		
	}
	
	@RequestMapping(value="/edit", method= RequestMethod.POST, params="save")
	public ModelAndView save(@Valid final ServiceForm serviceForm, final BindingResult binding){
		ModelAndView result;
		Service service;
		
		if(serviceForm.getCategory() == null){
			result = this.createEditModelAndView(serviceForm);
			return result;
		}
	
		
		service = this.serviceService.reconstruct(serviceForm, binding);
		
		if (binding.hasErrors())
			result = this.createEditModelAndView(serviceForm);
		else
			try {
				this.serviceService.save(service);
				result = new ModelAndView("redirect:../../manager/service/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(service, "service.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ServiceForm serviceForm){
		ModelAndView result;
		Service service;
		Manager principal;
		
		principal = this.managerService.findByPrincipal();
		service = this.serviceService.findOne(serviceForm.getId());
		
		if(service.getManager() != principal){
			result = new ModelAndView("redirect:/service/list.do");
		}else{
			try{
				this.serviceService.delete(service);
				result = new ModelAndView("redirect:/service/list.do");
			}catch(Throwable oops){
				String message = "service.commit.error";
				result = this.createEditModelAndView(serviceForm, message);
			}
			
		}
		
		
		return result;
	}

	protected ModelAndView createEditModelAndView(ServiceForm service) {
		ModelAndView result;
		
		result = this.createEditModelAndView(service, null);
		
		return result;
	}

	protected ModelAndView createEditModelAndView(ServiceForm serviceForm,
			String message) {
		ModelAndView result;
		Collection<Category> categories;
		Category parentCategory;
		categories = this.categoryService.findAll();
		parentCategory = this.categoryService.findRootCategory();
		categories.remove(parentCategory);
		
		result = new ModelAndView("service/edit");
		result.addObject("serviceForm", serviceForm);
		result.addObject("categories", categories);
		result.addObject("message", message);
		
		return result;
	}

	protected ModelAndView createEditModelAndView(final Service service) {
		ModelAndView result;

		result = this.createEditModelAndView(service, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Service service, String message) {

		ModelAndView result;
		Collection<Category> categories;
		Category parentCategory;
		categories = this.categoryService.findAll();
		parentCategory = this.categoryService.findRootCategory();
		categories.remove(parentCategory);
		
		ServiceForm serviceForm = new ServiceForm();
		
		serviceForm = this.serviceService.construct(service);
		
		result = new ModelAndView("service/edit");
		result.addObject("serviceForm", serviceForm);
		result.addObject("categories", categories);
		result.addObject("message", message);

		return result;
	}

}
