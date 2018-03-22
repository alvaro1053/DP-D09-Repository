
package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;
import domain.Manager;
import domain.Rende;

@Service
@Transactional
public class AdminService {

	// Managed Repository
	@Autowired
	private AdminRepository	adminRepository;


	// Supporting services

	// Constructors

	public AdminService() {
		super();
	}

	// Simple CRUD methods
	public Admin create() {
		Admin principal;
		Admin result;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		result = new Admin();
		return result;
	}

	public Admin save(final Admin Admin) {
		Admin saved;
		Assert.notNull(Admin);

		if (Admin.getId() == 0) {
			Admin principal;
			principal = this.findByPrincipal();
			Assert.notNull(principal);
			final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			Admin.getUserAccount().setPassword(passwordEncoder.encodePassword(Admin.getUserAccount().getPassword(), null));
		} else {
			Admin principal;
			principal = this.findByPrincipal();
			Assert.notNull(principal);

		}

		saved = this.adminRepository.save(Admin);

		return saved;
	}

	public Admin findOne(final int AdminId) {
		Admin result;
		result = this.adminRepository.findOne(AdminId);
		return result;
	}

	public Admin findByPrincipal() {
		Admin result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;

	}

	public Admin findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Admin result;
		result = this.adminRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	//statistics methods
	public Double averageOfRendesPerUser() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfRendesPerUser();
		return res;
	}
	public Double desviationOfRendesPerUser() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfRendesPerUser();
		return res;
	}
	
	
	
	public Double averageOfAnnouncementPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfAnnouncementPerRende();
		return res;
	}
	public Double desviationOfAnnouncementPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfAnnouncementPerRende();
		return res;
	}

	
	
	
	public Double averageOfQuestionsPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfQuestionsPerRende();
		return res;
	}
	public Double desviationOfQuestionsPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfQuestionsPerRende();
		return res;
	}
	
	
	
	

	public Double averageOfRepliesPerComment() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfRepliesPerComment();
		return res;
	}
	public Double desviationOfRepliesPerComment() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfRepliesPerComment();
		return res;
	}
	
	
	

	public Double averageOfRSVPPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfRSVPPerRende();
		return res;
	}
	public Double desviationOfRSVPPerRende() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfRSVPPerRende();
		return res;
	}
	
	
	

	public Double averageOfRSVPPerUser() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfRSVPPerUser();
		return res;
	}
	public Double desviationOfRSVPPerUser() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfRSVPPerUser();
		return res;
	}
	
	

	public Collection<Rende> rendesWithMoreAnnouncementsThanTheAverage() {
		final Collection<Rende> res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.rendesWithMoreAnnouncementsThanTheAverage();
		return res;
	}

	public Collection<Rende> rendesWithMoreLinkedThanTheAverage() {
		final Collection<Rende> res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.rendesWithMoreLinkedThanTheAverage();
		return res;
	}
	public Collection<Rende> top10RendesByAttendants() {
		final Collection<Rende> res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.top10RendesByAttendants();
		return res;
	}

	public Double ratioOfUsersWithRendesCreated() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.ratioOfUsersWithRendesCreated();
		return res;
	}
	
	
	
	public Double averageOfRepliesPerQuestion() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.averageOfRepliesPerQuestion();
		return res;
	}
	public Double desviationOfRepliesPerQuestion() {
		final Double res;
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		res = this.adminRepository.desviationOfRepliesPerQuestion();
		return res;
	}
	
	
	//Acme 2.0
	
	public Collection<domain.Service> topSellingServices(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Collection<domain.Service> res = this.adminRepository.topSellingServices();
		return res;
		
	}
	
	public Collection<Manager> ManagersWithMoreServicesThanTheAverage(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Collection<domain.Manager> res = this.adminRepository.ManagersWithMoreServicesThanTheAverage();
		return res;
		
	}
	
	
	public Collection<Rende> top5SellingServices(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		List<domain.Rende> res = new ArrayList<Rende>(this.adminRepository.top5SellingServices());
		List<Rende> top = res.subList(0, 4);
		return top;
		
	}

	public Double AverageCategoriesPerRendezvous(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.AverageCategoriesPerRendezvous();
		return res;
	}
	
	public Double AverageServicesRequestedPerRende(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.AverageServicesRequestedPerRende();
		return res;
	}
	
	public Double MaxServicesRequestedPerRende(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.MaxServicesRequestedPerRende();
		return res;
	}
	
	public Double MinServicesRequestedPerRende(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.MinServicesRequestedPerRende();
		return res;
	}
	
	public Double StandardDesviationServicesRequestedPerRende(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.StandardDesviationServicesRequestedPerRende();
		return res;
	}
	
	public Collection<Manager> ManagersWithMoreServicesCancelled(){
		 	Admin principal;
		    principal = this.findByPrincipal();
		    Assert.notNull(principal);
		    final Collection<Manager> res = new ArrayList<Manager>();
		    final List<Manager> listaDeManagersOrdenada = new ArrayList<Manager>(this.adminRepository.ManagersWithMoreServicesCancelled());
		    Manager top =listaDeManagersOrdenada.get(0);
		    res.add(top);  
		    Double maxServiciosEliminados = this.adminRepository.numberOfDeletedServices(top.getId());
		    
		    for (int i = 1; i < listaDeManagersOrdenada.size(); i++){
		      Manager aComprobar = listaDeManagersOrdenada.get(i);
		      if(this.adminRepository.numberOfDeletedServices(aComprobar.getId()) < maxServiciosEliminados)
		        break;
		      else{
		        res.add(aComprobar);
		      }
		    }
		    return res;
	}
	
	public Double AverageRatioOfServicesInEachCategory(){
		Admin principal;
		principal = this.findByPrincipal();
		Assert.notNull(principal);
		Double res = this.adminRepository.AverageRatioOfServicesInEachCategory();
		return res;
	}
	
}
