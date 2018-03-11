
package services;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ServiceRepository;
import domain.Manager;
import domain.Request;
import domain.User;
import forms.ServiceForm;


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
	
	@Autowired
	private Validator validator;

	

	// Constructors

	public ServiceService() {
		super();
	}

	// Simple CRUD methods

	public ServiceForm create() {
		Manager principal;
		ServiceForm service = new ServiceForm();

		principal = this.managerService.findByPrincipal();
		Assert.notNull(principal);

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

	public ServiceForm construct(domain.Service service) {
		ServiceForm result  = new ServiceForm();
		
		
		result.setId(service.getId());
		result.setVersion(service.getVersion());
		result.setName(service.getName());
		result.setDescription(service.getDescription());
		result.setPicture(service.getPicture());
		result.setCategory(service.getCategory());
		
		
		
		return result;
	}

	public domain.Service reconstruct(final ServiceForm serviceForm, final BindingResult binding) {
 		domain.Service service = new domain.Service();
		Manager manager;
		Collection<Request> requests = new ArrayList<Request>();
		
		
		manager = this.managerService.findByPrincipal();
		
		if(serviceForm.getId() == 0){
			service.setRequest(requests);
		}else{
			service = this.serviceRepository.findOne(serviceForm.getId());
		}
		
		if(serviceForm.getName() != "" && serviceForm.getDescription() != ""){
			service.setName(serviceForm.getName());
			service.setDescription(serviceForm.getDescription());
			service.setPicture(serviceForm.getPicture());
			service.setCategory(serviceForm.getCategory());
			service.setIsDeleted(false);
			service.setManager(manager);
		}
		
		
		this.validator.validate(service, binding);
		
		return service;
	}

	public domain.Service save(domain.Service service) {
		Manager manager;
		domain.Service result;
		
		manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		
		result = this.serviceRepository.save(service);
		
		
		return result;
	}


	



}
