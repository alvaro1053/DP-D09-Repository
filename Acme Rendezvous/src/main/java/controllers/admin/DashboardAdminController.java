package controllers.admin;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;

import controllers.AbstractController;
import domain.Manager;
import domain.Rende;
import domain.Service;


@Controller
@RequestMapping("dashboard/admin")
public class DashboardAdminController extends AbstractController {

	
	//Autowired
	@Autowired
	AdminService 		adminService;
	

	
	//Constructor
	public DashboardAdminController(){
		super();
	}
	
	//Save
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		final ModelAndView result;
		
		Double averageOfRendesPerUser,desviationOfRendesPerUser, averageOfRSVPPerRende, desviationOfRSVPPerRende, averageOfRSVPPerUser,
		desviationOfRSVPPerUser, averageOfAnnouncementPerRende, desviationOfAnnouncementPerRende, averageOfQuestionsPerRende, desviationOfQuestionsPerRende,
		averageOfRepliesPerComment, desviationOfRepliesPerComment, averageOfRepliesPerQuestion, desviationOfRepliesPerQuestion;
		Collection<Rende> top10RendesByAttendants, rendesWithMoreAnnouncementsThanTheAverage, rendesWithMoreLinkedThanTheAverage;
		Double ratioOfUsersWithRendesCreated; 
		
		Collection<Service> topSellingServices;
		Collection<Manager> ManagersWithMoreServicesThanTheAverage;
		Double AverageCategoriesPerRendezvous, AverageServicesRequestedPerRende, MaxServicesRequestedPerRende,
		MinServicesRequestedPerRende, StandardDesviationServicesRequestedPerRende;
		Collection<Rende> top5SellingServices;

		//Stadistics
		averageOfRendesPerUser = this.adminService.averageOfRendesPerUser();
		desviationOfRendesPerUser = this.adminService.desviationOfRendesPerUser();
		
		averageOfRSVPPerRende = this.adminService.averageOfRSVPPerRende();
		desviationOfRSVPPerRende = this.adminService.desviationOfRSVPPerRende();

		averageOfRSVPPerUser = this.adminService.averageOfRSVPPerUser();
		desviationOfRSVPPerUser = this.adminService.desviationOfRSVPPerUser();

		averageOfAnnouncementPerRende = this.adminService.averageOfAnnouncementPerRende();
		desviationOfAnnouncementPerRende = this.adminService.desviationOfAnnouncementPerRende();

		averageOfQuestionsPerRende = this.adminService.averageOfQuestionsPerRende();
		desviationOfQuestionsPerRende = this.adminService.desviationOfQuestionsPerRende();

		averageOfRepliesPerComment = this.adminService.averageOfRepliesPerComment();
		desviationOfRepliesPerComment = this.adminService.desviationOfRepliesPerComment();

		top10RendesByAttendants = this.adminService.top10RendesByAttendants();
		rendesWithMoreAnnouncementsThanTheAverage = this.adminService.rendesWithMoreAnnouncementsThanTheAverage();
		rendesWithMoreLinkedThanTheAverage = this.adminService.rendesWithMoreLinkedThanTheAverage();
		ratioOfUsersWithRendesCreated = this.adminService.ratioOfUsersWithRendesCreated();
		
		averageOfRepliesPerQuestion= this.adminService.averageOfRepliesPerQuestion();
		desviationOfRepliesPerQuestion= this.adminService.desviationOfRepliesPerQuestion();
		
		//Acme-Rendezvous 2.0
		topSellingServices = this.adminService.topSellingServices();
		ManagersWithMoreServicesThanTheAverage = this.adminService.ManagersWithMoreServicesThanTheAverage();
		AverageCategoriesPerRendezvous = this.adminService.AverageCategoriesPerRendezvous();
		AverageServicesRequestedPerRende = this.adminService.AverageServicesRequestedPerRende();
		MaxServicesRequestedPerRende = this.adminService.MaxServicesRequestedPerRende();
		MinServicesRequestedPerRende = this.adminService.MinServicesRequestedPerRende();
		StandardDesviationServicesRequestedPerRende = this.adminService.StandardDesviationServicesRequestedPerRende();
		top5SellingServices = this.adminService.top5SellingServices();
		

		result = new ModelAndView("administrator/dashboard");

		result.addObject("averageOfRendesPerUser", averageOfRendesPerUser);
		result.addObject("desviationOfRendesPerUser", desviationOfRendesPerUser);
		result.addObject("averageOfRSVPPerRende", averageOfRSVPPerRende);
		result.addObject("desviationOfRSVPPerRende", desviationOfRSVPPerRende);
		result.addObject("averageOfRSVPPerUser", averageOfRSVPPerUser);
		result.addObject("desviationOfRSVPPerUser", desviationOfRSVPPerUser);
		result.addObject("averageOfAnnouncementPerRende", averageOfAnnouncementPerRende);
		result.addObject("desviationOfAnnouncementPerRende", desviationOfAnnouncementPerRende);
		result.addObject("averageOfQuestionsPerRende", averageOfQuestionsPerRende);
		result.addObject("desviationOfQuestionsPerRende", desviationOfQuestionsPerRende);
		result.addObject("averageOfRepliesPerComment", averageOfRepliesPerComment);
		result.addObject("desviationOfRepliesPerComment", desviationOfRepliesPerComment);
		result.addObject("top10RendesByAttendants", top10RendesByAttendants);
		result.addObject("rendesWithMoreAnnouncementsThanTheAverage", rendesWithMoreAnnouncementsThanTheAverage);
		result.addObject("rendesWithMoreLinkedThanTheAverage", rendesWithMoreLinkedThanTheAverage);
		result.addObject("ratioOfUsersWithRendesCreated", ratioOfUsersWithRendesCreated);
		result.addObject("averageOfRepliesPerQuestion", averageOfRepliesPerQuestion);
		result.addObject("desviationOfRepliesPerQuestion", desviationOfRepliesPerQuestion);
		
		//Acme-Rendezvous 2.0
		result.addObject("topSellingServices",topSellingServices);
		result.addObject("ManagersWithMoreServicesThanTheAverage",ManagersWithMoreServicesThanTheAverage);
		result.addObject("AverageCategoriesPerRendezvous",AverageCategoriesPerRendezvous);
		result.addObject("AverageServicesRequestedPerRende",AverageServicesRequestedPerRende);
		result.addObject("MaxServicesRequestedPerRende",MaxServicesRequestedPerRende);
		result.addObject("MinServicesRequestedPerRende",MinServicesRequestedPerRende);
		result.addObject("StandardDesviationServicesRequestedPerRende",StandardDesviationServicesRequestedPerRende);
		result.addObject("top5SellingServices",top5SellingServices);		

		return result;
	}
}
