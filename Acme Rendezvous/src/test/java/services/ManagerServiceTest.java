package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import domain.Manager;
import forms.ActorForm;

import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class ManagerServiceTest extends AbstractTest{

	@Autowired
	private ManagerService managerService;
	
	@Test
	public void driverRegistringNewUsers(){
/*		
3.1 An actor who is not authenticated must be able to register to the system as a manager.
*/
		Date over18 = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			over18 = sdf.parse("30/10/1995");
		} catch (ParseException e) {
			System.out.println("Problema al crear las fechas");
		}
		
		Object testingData[][] = {
				//==========================================================================//
				//Test 1 POSITIVO 
				//
				//El test consiste en probar que un manager no registrado pueda registrarse 				
				//en la web como un nuevo manager
				//==========================================================================//
				{"NombrePr1", "ApellidoPr1","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba1", "password", "password", null, null},	
				//==========================================================================//
				//Test 2 NEGATIVO 
				//
				//El test consiste en probar que un manager no registrado NO pueda 	
				//registrarse en la web como un nuevo manager con el campo de NOMBRE en
				//blanco
				//==========================================================================//
				{"", "ApellidoPr2","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba2" , "password", "password", null,java.lang.NullPointerException.class},	
				//==========================================================================//
				//Test 11 NEGATIVO 
				//
				//El test consiste en probar que un manager no registrado NO pueda  				
				//registrarse en la web como un nuevo manager si se encuentra en el momento 
				//logeado en la página web como MANAGER
				//==========================================================================//
				{"NombrePr11", "ApellidoPr11","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba1", "password", "password", "manager1", IllegalArgumentException.class}

		};

		for (int i = 0; i < testingData.length; i++) {
			this.startTransaction();
			templateRegistringNewUser((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Date) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (Boolean) testingData[i][6],
					(String) testingData[i][7], (String) testingData[i][8],
					(String) testingData[i][9], (String) testingData[i][10],
					(Class<?>) testingData[i][11]);
			this.rollbackTransaction();
		}
	}

	protected void templateRegistringNewUser(String name, String surname, String email,
			Date dateBirth,String phone, String address, Boolean checkTerms, String userAccountName,String userAccountPassword,String userAccountConfirmPassword, String username,Class<?> expected) {
		Class<?> caught;
		caught = null;
		Manager manager = null;
		
		//==== Registrar datos =========
		try{
		ActorForm actorForm = registringActor(name, surname, email, dateBirth, phone, address, checkTerms, userAccountName, userAccountPassword, userAccountConfirmPassword);
		
		BindingResult binding = null;
		
			manager = managerService.reconstruct(actorForm, binding);
			managerService.flush();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		
		if(manager != null){
			try{
				super.authenticate(username);
				managerService.save(manager);
				managerService.flush();
			}catch(Throwable oops){
				caught = oops.getClass();
			}
		}
		checkExceptions(expected, caught);
	}
	
	//Auxiliary method for registring a new user to the system
	
		protected ActorForm registringActor(String name, String surname, String email,
				Date dateBirth,String phone, String address, Boolean checkTerms, String userAccountName, String userAccountPassword, String userAccountConfirmPassword){
			ActorForm actorForm = new ActorForm();
			
			actorForm.setName(name);
			actorForm.setSurname(surname);
			actorForm.setEmail(email);
			actorForm.setDateBirth(dateBirth);
			actorForm.setPhone(phone);
			actorForm.setAddress(address);
			
			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(userAccountName);
			userAccount.setPassword("password");
			actorForm.setConfirmPassword("password");
			actorForm.setUserAccount(userAccount);
			
			actorForm.setCheck(checkTerms);		
			
			return actorForm;
		}
}