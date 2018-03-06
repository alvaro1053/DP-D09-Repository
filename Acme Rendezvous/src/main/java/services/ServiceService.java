
package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ServiceRepository;
import domain.Manager;
import domain.User;


@Service
@Transactional
public class ServiceService {

	// Managed Repository
	@Autowired
	private ServiceRepository			serviceRepository;

	// Supporting services
	@Autowired
	private UserService				userService;

	@Autowired
	private ManagerService		managerService;

	

	// Constructors

	public ServiceService() {
		super();
	}

	// Simple CRUD methods

	public domain.Service create() {
		Manager principal;
		domain.Service service = new domain.Service();

		principal = this.managerService.findByPrincipal();
		Assert.notNull(principal);
		service.setManager(principal);

		return service;
	}

	//  An actor who is not authenticated must be able to browse the list of Services and display them
	public Collection<domain.Service> findAll() {
		final Collection<domain.Service> result = this.serviceRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	// Other business methods
	public void delete(final domain.Service service) {
		User principal;
		Assert.notNull(service);
		Assert.isTrue(service.getId() != 0);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		Assert.isTrue(this.findByUserId(principal.getId()).contains(service));
		service.setIsDeleted(true);

		this.serviceRepository.save(service);
	}


	
	public Collection<domain.Service> findByUserId(final int id) {
		User principal;
		Collection<domain.Service> result;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		result = this.serviceRepository.selectByManagerId(id);

		Assert.notNull(result);

		return result;

	}


	public domain.Service findOne(final int ServiceId) {
		domain.Service result;

		result = this.serviceRepository.findOne(ServiceId);

		return result;

	}

	public Collection<domain.Service> findByCategory(final int categoryId){
		Collection<domain.Service> result;
		result = this.serviceRepository.findByCategory(categoryId);
		Assert.notNull(result);
		return result;
	}


	



}
