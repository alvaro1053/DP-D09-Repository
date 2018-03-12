
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Manager;
import forms.ActorForm;

@Service
@Transactional
public class ManagerService {

	// Managed Repository
	@Autowired
	private ManagerRepository	ManagerRepository;
	@Autowired
	private Validator		validator;


	// Supporting services

	// Constructors

	public ManagerService() {
		super();
	}

	// Simple CRUD methods
	public Manager create() {
		Manager result;

		result = new Manager();
		result.setVATnumber(this.generateVATNumber());

		return result;
	}

	public Manager save(final Manager Manager) {
		Manager saved;
		Assert.notNull(Manager);

		if (Manager.getId() == 0) {
			final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			Manager.getUserAccount().setPassword(passwordEncoder.encodePassword(Manager.getUserAccount().getPassword(), null));
		} else {
			Manager principal;
			principal = this.findByPrincipal();
			Assert.notNull(principal);

		}

		saved = this.ManagerRepository.save(Manager);

		return saved;
	}

	public Manager findOne(final int ManagerId) {
		Manager result;
		result = this.ManagerRepository.findOne(ManagerId);
		return result;
	}

	public Collection<Manager> findAll() {
		Collection<Manager> result;
		result = this.ManagerRepository.findAll();
		Assert.notNull(result);
		return result;

	}

	//Other business methods
	public Manager findByPrincipal() {
		Manager result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;

	}

	public Manager findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Manager result;
		result = this.ManagerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Manager reconstruct(final ActorForm actorForm, final BindingResult binding) {
		final Manager manager = this.create();
		manager.setName(actorForm.getName());
		manager.setSurname(actorForm.getSurname());
		manager.setEmail(actorForm.getEmail());
		manager.setId(actorForm.getId());
		manager.setVersion(actorForm.getVersion());
		manager.setPhone(actorForm.getPhone());
		manager.setUserAccount(actorForm.getUserAccount());
		manager.setDateBirth(actorForm.getDateBirth());
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority auth = new Authority();
		auth.setAuthority("MANAGER");
		authorities.add(auth);
		manager.getUserAccount().setAuthorities(authorities);

		this.validator.validate(actorForm, binding);
		if (!(actorForm.getConfirmPassword().equals((actorForm.getUserAccount().getPassword()))) || actorForm.getConfirmPassword() == null)
			binding.rejectValue("confirmPassword", "user.passwordMiss");
		if ((actorForm.getCheck() == false))
			binding.rejectValue("check", "user.uncheck");
		return manager;
	}
	public String generateVATNumber(){
		String res;
		Random r = new Random();
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = upper.toLowerCase();
		String digits = "0123456789";
		String alphanum = upper + lower + digits;
		char[] alphanumArray = alphanum.toCharArray();
		final char a = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char b = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char c = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char d = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char e = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char f = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		final char g = (char) alphanumArray[r.nextInt(alphanumArray.length)];
		res = String.valueOf(a) + String.valueOf(b) +"-"+ String.valueOf(c) + String.valueOf(d)+"-"+ String.valueOf(e)+ String.valueOf(f)+ String.valueOf(g);
		
		return res;
	}
}
