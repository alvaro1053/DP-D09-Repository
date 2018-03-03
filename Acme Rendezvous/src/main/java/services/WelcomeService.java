package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import repositories.WelcomeRepository;

import domain.Admin;
import domain.WelcomePage;


@Service
@Transactional
public class WelcomeService {
	
	@Autowired
	WelcomeRepository welcomeRepository;
	
	@Autowired
	AdminService adminService;

	
	public WelcomePage find() {

		WelcomePage result;

		result = this.welcomeRepository.findAll().get(0);
		Assert.notNull(result);
		return result;

	}


	public WelcomePage save(WelcomePage welcomePage) {
		WelcomePage result;
		Admin principal;
	
		principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);

		result = this.welcomeRepository.save(welcomePage);
		Assert.notNull(result);

		return result;
		
	}
	
}
