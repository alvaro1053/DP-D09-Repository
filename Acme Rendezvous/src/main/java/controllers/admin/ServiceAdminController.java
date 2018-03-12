package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Service;

import services.AdminService;
import services.ServiceService;


@Controller
@RequestMapping("service/admin")
public class ServiceAdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	ServiceService	serviceService;
	
	public ServiceAdminController(){
		
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int serviceId, RedirectAttributes redir){
		ModelAndView result;
		Service service;
		
		service = this.serviceService.findOne(serviceId);
		
		service.setIsDeleted(true);
		
		try{
			this.serviceService.saveAdmin(service);
			result = new ModelAndView("redirect:/service/list.do");
			String successfulMessage = "service.commit.ok";
			redir.addFlashAttribute("successfulMessage", successfulMessage);
			redir.addFlashAttribute("wasSucessful", true);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/service/list.do");
		}
		
		
		return result;
	}

}
