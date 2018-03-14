package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.User;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class UserServiceTest extends AbstractTest {

	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void driverSaveUser(){
		Object testingData[][] = {
				//==========================================================================//
				//Test POSITIVO 
				//
				//Para probar que un usuario no registrado puede registrarse  				
				//(simulamos que lo hace como usuario ya registrado, si su nombre de 
				//userAccount ya se encuentra en la base de datos se trata desde el 
				//controlador (controllers.UserRegisterController.java especificamente
				// en /register
				//==========================================================================//
				{null, "user1", null},
				//==========================================================================//
				//Test NEGATIVO 
				//
				//El test consiste en probar que un usuario no pueda volver  				
				//a registrarse en la web estando ya registrado.
				//==========================================================================//
				{"user1", "user2", IllegalArgumentException.class}
										
		};
		for (int i = 0; i < testingData.length; i++){
			templateFindByPrincipal((String) testingData[i][0], (int) super.getEntityId((String) testingData[i][1]),  (Class<?>) testingData[i][2]);
		}
	}



	protected void templateFindByPrincipal(String username, int userId, Class<?> expected) {
		Class<?> caught;
		caught = null;
		try{
			super.authenticate(username);
			User user = userService.findOne(userId);
			userService.save(user);
			
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	
	@Test
	public void driverListUsers(){
		Object testingData[][] = {
				//==========================================================================//
				//Test POSITIVO 
				//
				//El test consiste en probar que un usuario no registrado pueda ver la 				
				//lista de usuarios registrados en el sistema.
				//==========================================================================//
				{null, null}				
		};
		
		
		for (int i = 0; i < testingData.length; i++){
			templateListUsers((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}
	}



	protected void templateListUsers(String username, Class<?> expected) {
		Class<?> caught;
		caught = null;
		
		try{
			super.authenticate(username);
			userService.findAll();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
}
