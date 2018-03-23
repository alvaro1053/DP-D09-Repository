package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import domain.User;
import forms.ActorForm;

import security.UserAccount;
import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class UserServiceTest extends AbstractTest {

	
	@Autowired
	private UserService userService;
	
	//========================================================================
	//En esta clase test probamos que un usuario se pueda registrar en el
	//sistema satisfactoriamente, para ello simulamos que se registra
	//de manera análoga a como se realiza en nuestro sistema con el 
	//controlador
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (4.1)
	//========================================================================
	@Test
	public void driverRegisteringNewUsers(){
		Date over18 = new Date();
		Date under18 = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			over18 = sdf.parse("30/10/1995");
			under18 = sdf.parse("30/10/2005");
		} catch (ParseException e) {
			System.out.println("Problema al crear las fechas");
		}
		
		//Orden de los datos 
		//{nombreUsuario, apellido, email, edad, telefono, direccion, check terminos, nombreUserAccount, contraseña UserAccount, Repetir contaseña UserAccount, si ha iniciado sesion antes, error esperado}
		Object testingData[][] = {
				//==========================================================================//
				//Test 1 POSITIVO 
				//
				//El test consiste en probar que un usuario menor de edad
				//no registrado pueda registrarse en la web como un nuevo usuario			
				// con la dirección en blanco (opcional)
				//==========================================================================//
				{"NombrePr1", "ApellidoPr1","email@gmail.com", under18, "123456789", "", true, "prueba1", "password","password", null,null},
				//==========================================================================//
				//Test 2 POSITIVO 
				//
				//El test consiste en probar que un usuario no registrado pueda registrarse 				
				//en la web como un nuevo usuario con el teléfono en blanco (opcional)
				//==========================================================================//
				{"NombrePr2", "ApellidoPr2","email@gmail.com", over18, "", "Ernest Hemingway, 48", true, "prueba2", "password", "password", null, null},
				//==========================================================================//
				//Test 3 POSITIVO 
				//
				//El test consiste en probar que un usuario no registrado pueda registrarse 				
				//en la web como un nuevo usuario
				//==========================================================================//
				{"NombrePr3", "ApellidoPr3","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba3", "password", "password", null, null},	
				//==========================================================================//
				//Test 4 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda 	
				//registrarse en la web como un nuevo usuario con el campo de NOMBRE en
				//blanco
				//==========================================================================//
				{"", "ApellidoPr4","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba4" , "password", "password", null,java.lang.NullPointerException.class},	
				//==========================================================================//
				//Test 5 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda 	
				//registrarse en la web como un nuevo usuario con el campo de APELLIDO en
				//blanco
				//==========================================================================//
				{"NombrePr5", "","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba5", "password", "password", null,java.lang.NullPointerException.class},
				//==========================================================================//
				//Test 6 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda 	
				//registrarse en la web como un nuevo usuario con el campo de EMAIL en
				//blanco
				//==========================================================================//
				{"NombrePr6", "ApellidoPr6","", over18, "671948073", "Ernest Hemingway, 48", true, "prueba6", "password", "password", null,java.lang.NullPointerException.class},
				//==========================================================================//
				//Test 7 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda 	
				//registrarse en la web como un nuevo usuario con el campo de EMAIL que no
				//corresponda con un email
				//==========================================================================//
				{"NombrePr7", "ApellidoPr7","PruebaSinEmail00", over18, "671948073", "Ernest Hemingway, 48", true, "prueba7", "password", "password", null,java.lang.NullPointerException.class},
				//==========================================================================//
				//Test 8 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda 	
				//registrarse en la web como un nuevo usuario con el campo de TÉRMINOS sin  
				//marcar
				//==========================================================================//
				{"NombrePr8", "ApellidoPr8","email@gmail.com", over18, "123456789", "Ernest Hemingway, 48", false, "prueba8", "password", "password", null,java.lang.NullPointerException.class},
				//==========================================================================//
				//Test 9 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda  				
				//registrarse en la web como un nuevo usuario si ya existe el nombre de 
				//usuario a registrar
				//==========================================================================//
				{"NombrePr9", "ApellidoPr9","email@gmail.com", over18, "123456789", "Ernest Hemingway, 48", true, "user1", "password", "password", null,org.springframework.dao.DataIntegrityViolationException.class},
				//==========================================================================//
				//Test 10 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda  				
				//registrarse en la web como un nuevo usuario si la contraseña de  
				//confirmación no coincide con la contraseña introducida por el 
				//usuario a registrar
				//==========================================================================//
				{"NombrePr10", "ApellidoPr10","email@gmail.com", over18, "123456789", "Ernest Hemingway, 48", true, "prueba10", "password", "passwordNoCoincide", null,org.springframework.dao.DataIntegrityViolationException.class},
				//==========================================================================//
				//Test 11 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda  				
				//registrarse en la web como un nuevo usuario si se encuentra en el momento 
				//logeado en la página web como USER
				//==========================================================================//
				{"NombrePr11", "ApellidoPr11","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba11", "password", "password", "user1", org.springframework.dao.DataIntegrityViolationException.class},
				//==========================================================================//
				//Test 12 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda  				
				//registrarse en la web como un nuevo usuario si se encuentra en el momento 
				//logeado en la página web como ADMIN
				//==========================================================================//
				{"NombrePr12", "ApellidoPr12","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba12", "password", "password", "admin", org.springframework.dao.DataIntegrityViolationException.class},
				//==========================================================================//
				//Test 13 NEGATIVO 
				//
				//El test consiste en probar que un usuario no registrado NO pueda  				
				//registrarse en la web como un nuevo usuario si se encuentra en el momento 
				//logeado en la página web como MANAGER
				//==========================================================================//
				{"NombrePr13", "ApellidoPr13","email@gmail.com", over18, "671948073", "Ernest Hemingway, 48", true, "prueba13", "password", "password", "manager1", org.springframework.dao.DataIntegrityViolationException.class}
		};
		
		
		for (int i = 0; i < testingData.length; i++){
			templateRegisteringNewUser((String) testingData[i][0],(String) testingData[i][1],(String) testingData[i][2],
					(Date) testingData[i][3],(String) testingData[i][4],(String) testingData[i][5],(Boolean) testingData[i][6],(String) testingData[i][7],(String) testingData[i][8],(String) testingData[i][9],(String) testingData[i][10],(Class<?>) testingData[i][11]);
		}
	}
	

	
	protected void templateRegisteringNewUser(String name, String surname, String email,
			Date dateBirth,String phone, String address, Boolean checkTerms, String userAccountName,String userAccountPassword,String userAccountConfirmPassword, String username,Class<?> expected) {
		Class<?> caught;
		caught = null;
		User user = null;
		
		//==== Registrar datos =========
		ActorForm actorForm = registringActor(name, surname, email, dateBirth, phone, address, checkTerms, userAccountName, userAccountPassword, userAccountConfirmPassword);
		
		BindingResult binding = null;
		try{
			user = userService.reconstruct(actorForm, binding);
			userService.flush();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		
		if(user != null){
			try{
				super.authenticate(username);
				userService.save(user);
				userService.flush();
			}catch(Throwable oops){
				caught = oops.getClass();
			}
		}
		checkExceptions(expected, caught);
	}
	
	
	
	//========================================================================
	//En esta clase test probamos que un usuario no registrado pueda listar
	//a los usuarios que se encuentran en el sistema, satisfactoriamente. 
	//
	//Para realizar la prueba negativa, hemos probado que un manager no pueda
	//listar a los usuarios del sistema (el display del user sí es posible).
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (4.2)
	//========================================================================
	@Test
	public void driverListUsers(){
		Object testingData[][] = {
				//==========================================================================//
				//Test POSITIVO 
				//
				//Un usuario no registrado pueda ver la lista de usuarios registrados 
				//en el sistema.
				//==========================================================================//
				{null, null},
				//==========================================================================//
				//Test POSITIVO 
				//
				//Un usuario registrado como user pueda ver la lista de usuarios registrados 
				//en el sistema. (Readme.txt explicación)
				//==========================================================================//
				{"user1", null},
				//==========================================================================//
				//Test POSITIVO 
				//
				//Un usuario registrado como admin pueda ver la lista de usuarios registrados 
				//en el sistema.
				//==========================================================================//
				{"admin", null},
				//==========================================================================//
				//Test NEGATIVO 
				//
				//Un usuario registrado como manager NO pueda ver la lista de usuarios 
				//registrados en el sistema. 
				//==========================================================================//
				{"manager1", IllegalArgumentException.class}
				
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
			Collection<User> cU = userService.findAll();
			
			for(User u : cU){
				u.getName();
				u.getSurname();
				u.getrSVPS();
				break;
			}
			
			super.unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	@Test
	  public void driverDisplayUser(){
/*
 * 4.3. "An actor who is not authenticated must be able to: 
 * List the rendezvouses in the system and NAVIGATE to the PROFILES	 of the correspond-ing creators and attendants."
 */
	    Object testingData[][]= {
	    	//==========================================================================//
			//Test POSITIVO
			//
	    	//Comprueba que efectivamente puede hacer display de un user sin estar autentificado
	        {"user1",this.getEntityId("user2"),null}
	        
	    };
	    
	    for (int i = 0; i < testingData.length;i++){
	      this.startTransaction();
	      templateDisplayRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
	      this.rollbackTransaction();
	    }
	  }

	  
	  protected void templateDisplayRende(String username, Integer rendeId, Class<?> expected){
	    Class<?> caught;
	    caught = null;
	    try{
	      authenticate(username);
	      User user = userService.findOne(rendeId);
	      userService.flush();
	      Assert.notNull(user);
	      unauthenticate();
	    } catch(Throwable oops){
	      caught = oops.getClass();
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
