package services;

import java.sql.Date;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Location;
import domain.Rende;
import domain.User;
import utilities.AbstractTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class RendeServiceTest extends AbstractTest {


	@Autowired
	private RendeService rendeService;
	
	@Autowired
	private UserService userService;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Test
	public void driverListRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//Do the same as an actor who is not authenticated, but register to the system. 5.1"
				//El usuario 1 es mayor de edad por lo que deber�a poder listar a todos los rendes sin problemas
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 es menor de edad por lo que no deber�a poder listar todos los rendes (En todo caso tan s�lo los que son 
				//Calificados para todos los p�blicos (Acme-Rendezvous 14)
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				//Este caso no tiene ninguna diferencia con el primero puesto que se usa el user1, pero tan s�lo en el caso de listar y display.
				//Est� aqu� tan s�lo para usar los mismos datos en los pr�ximos tests
				{"user1",this.getEntityId("rende4"),null},  
				//Los managers no necesitan listar los Rendezvous, en Acme-Rendezvous 2.0 no dice nada acerca de ello
				//Adicionalmente, hemos permitido que s� pueden hacer display de los rendezvous que sus Servicios tienen requests
				//Lo cual se comprueba en el siguiente test
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//Los admins deben de poder listar sin restricciones
				{"admin",this.getEntityId("rende2"),null}, 
				//Los usuarios no registrados no deber�an de poder listar todos los rendezvous al igual que los usuarios menores
				//De edad (Acme-Rendezvous 14)
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				//Es el mismo caso que el anterior, se usa en los siguientes tests
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateListRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
		this.rollbackTransaction();
		}
	}

	
	protected void templateListRende(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			rendeService.findAll();
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverListFilterRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//Do the same as an actor who is not authenticated, but register to the system. 5.1"
				//El usuario 1 es mayor de edad por lo que deber�a poder listar a todos los rendes sin problemas
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 es menor de edad por lo que no deber�a poder listar todos los rendes (En todo caso tan s�lo los que son 
				//Calificados para todos los p�blicos (Acme-Rendezvous 14)
				{"user2",this.getEntityId("rende1"),null}, 
				//Este caso no tiene ninguna diferencia con el primero puesto que se usa el user1, pero tan s�lo en el caso de listar y display.
				//Est� aqu� tan s�lo para usar los mismos datos en los pr�ximos tests
				{"user1",this.getEntityId("rende4"),null},  
				//Los managers no necesitan listar los Rendezvous, en Acme-Rendezvous 2.0 no dice nada acerca de ello
				//Adicionalmente, hemos permitido que s� pueden hacer display de los rendezvous que sus Servicios tienen requests
				//Lo cual se comprueba en el siguiente test
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//Los admins deben de poder listar sin restricciones
				{"admin",this.getEntityId("rende2"),null}, 
				//Los usuarios no registrados no deber�an de poder listar todos los rendezvous al igual que los usuarios menores
				//De edad (Acme-Rendezvous 14)/Acme-Rendezvous 4.3
				{null,this.getEntityId("rende2"),null},
				//Es el mismo caso que el anterior, se usa en los siguientes tests
				{null,this.getEntityId("rende1"),null}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateListFilterRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateListFilterRende(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			rendeService.selectNotAdultRendes();
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverDisplayRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//Do the same as an actor who is not authenticated, but register to the system. 5.1"
				//El usuario 1 es mayor de edad por lo que deber�a poder hacer dispay a todos los rendes sin problemas
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 es menor de edad por lo que no deber�a poder hacer display de todos los rendes (En todo caso tan s�lo los que son 
				//Calificados para todos los p�blicos (Acme-Rendezvous 14) 
				//El rende1 est� calificado para mayores de edad por lo que debe de saltar excepci�n al intentar acceder a �lcomo user2
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				//Este caso no tiene ninguna diferencia con el primero puesto que se usa el user1, pero tan s�lo en el caso de listar y display.
				//Est� aqu� tan s�lo para usar los mismos datos en los pr�ximos tests
				{"user1",this.getEntityId("rende4"),null},  
				//Los managers no necesitan listar los Rendezvous, en Acme-Rendezvous 2.0 no dice nada acerca de ello
				//Adicionalmente, hemos permitido que s� pueden hacer display de los rendezvous que sus Servicios tienen requests
				{"manager1",this.getEntityId("rende4"),null},
				//Los admins deben de poder hacer display sin restricciones
				{"admin",this.getEntityId("rende2"),null}, 
				//Los usuarios no registrados no deber�an de poder hacer display de todos los rendezvous al igual que los usuarios menores
				//De edad (Acme-Rendezvous 14)
				//El rende2 es para menores de edad por lo que no deber�a haber problema
				{null,this.getEntityId("rende2"),null},
				//El rende1 sin embargo es para mayores de edad por lo tanto los usuarios no logueados no deben verlo
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
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
			rendeService.findOne(rendeId);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	@Test
	public void driverEditRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//Edit and delete the Rendes. 5.3"
				//El usuario 1 es el creador del rende1 por lo que debe poder editarlo
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 no es el creador del rende1 por lo que debe saltar excepci�n
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				//El usuario 1 no es el creador del rende4
				{"user1",this.getEntityId("rende4"),IllegalArgumentException.class},  
				//Los managers no deben de poder editar rendes ya que no los crean
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//Sobre los administradores no se habla nada sobre que puedan editar ni crear rendes, tan s�lo eliminarlos por lo que
				//Debe saltar error
				{"admin",this.getEntityId("rende2"),IllegalArgumentException.class}, 
				//Por supuesto, los usuarios no registrados no pueden editar rendes
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateEditRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateEditRende(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rende = rendeService.findOne(rendeId);
			rende.setName("Prueba"); //Las pruebas de constraints se har�n en el create puesto que se comprueban en el save tambien
			rende.setDescription("Test de rende");
			rendeService.save(rende);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void driverDeleteByUserRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//Edit and delete the Rendes. 5.3"
				//El usuario 1 es el creador del rende1 por lo que debe poder editarlo
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 no es el creador del rende1 por lo que debe saltar excepci�n
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				//El usuario 1 no es el creador del rende4
				{"user1",this.getEntityId("rende4"),IllegalArgumentException.class},  
				//Los managers no deben de poder editar rendes ya que no los crean
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//Sobre los administradores no se habla nada sobre que puedan editar ni crear rendes, tan s�lo eliminarlos por lo que
				//Debe saltar error
				{"admin",this.getEntityId("rende2"),IllegalArgumentException.class}, 
				//Por supuesto, los usuarios no registrados no pueden borrar rendes
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateDeleteByUserRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateDeleteByUserRende(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rende = rendeService.findOne(rendeId);
			rendeService.delete(rende);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	
	

	@Test
	public void driverDeleteByAdmin(){
		Object testingData[][]= {
			//En este test debe de saltar error cualquier rol que intente borrar un rende que no sea un admin
				//Acme Rendezvous (6.2)
				{"admin",this.getEntityId("rende2"),null},
				{"user1",this.getEntityId("rende1"),IllegalArgumentException.class},
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				{"user1",this.getEntityId("rende4"),IllegalArgumentException.class},  
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},		 
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
				
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateDeleteByAdmin((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateDeleteByAdmin(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rende = rendeService.findOne(rendeId);
			rendeService.deleteByAdmin(rende);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		this.entityManager.clear();
	}

	
	@Test
	public void driverCreateRende(){
		Object testingData[][]= {
				//TEST POSITIVO: Los usuarios pueden crear rendezvous 5.2
				//En este caso la URL est� vac�a pues es opcional
				{"user1","name","Description",new Date(119,01,02),"",
					14.02,82.2,true,false,false,null},
				//TEST POSITIVO: Lo mismo pero con un User2
					{"user2","name","Description",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
						14.02,82.2,true,false,false,null}, 
				//TEST POSITIVO: Con URL
				{"user1","name","Description",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					14.02,82.2,true,false,false,null}, 
					//TEST NEGATIVO: El name no puede estar en blanco
				{"user1","","Description",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					14.02,82.2,true,false,false,ConstraintViolationException.class}, 
					//TEST NEGATIVO: Ni la descripci�n
				{"user1","name","",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					14.02,82.2,true,false,false,ConstraintViolationException.class}, 
					//TEST NEGATIVO: La fecha no puede ser en pasado
				{"user1","name","Description",new Date(117,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					14.02,82.2,true,false,false,ConstraintViolationException.class}, 
					//TEST NEGATIVO: Las pictures deben de ser una URL
				{"user1","name","Description",new Date(119,01,02),"estonoesunaURL",
					14.02,82.2,true,false,false,ConstraintViolationException.class}, 
					//TEST NEGATIVO: Rango de coordenadas en el l�mite
				{"user1","name","Description",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					90.0,180.0,true,false,false,null}, 
				//TEST NEGATIVO: Creando un rende para mayor de edad siendo menor
				{"user2","name","Description",new Date(119,01,02),"https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",
					14.02,82.2,true,true,false,IllegalArgumentException.class}, 
			 //TESTS NEGATIVOS: Cualquier otro rol no deber�a poder crear un rende
				{"manager1","name","Description",new Date(2019,01,02),"",
					14.02,82.2,true,false,false,IllegalArgumentException.class},
				{"admin","name","Description",new Date(2019,01,02),"",
					14.02,82.2,true,false,false,IllegalArgumentException.class},
				{null,"name","Description",new Date(2019,01,02),"",
					14.02,82.2,true,false,false,IllegalArgumentException.class},
				
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateCreateRende((String) testingData[i][0],(String)  testingData[i][1], (String) testingData[i][2], (Date)  testingData[i][3],(String) testingData[i][4],(Double) testingData[i][5],
					(Double) testingData[i][6],(Boolean) testingData[i][7],(Boolean) testingData[i][8],(Boolean) testingData[i][9],(Class<?>) testingData[i][10]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateCreateRende(String username, String name, String description, Date moment, String picture, Double latitude, Double longitude, Boolean isDraft, Boolean adultOnly, Boolean isDeleted, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rende = this.rendeService.createRende();
			rende.setName(name);
			rende.setDescription(description);
			rende.setMoment(moment);
			rende.setPicture(picture);
			Location coordenates = new Location();
			coordenates.setLatitude(latitude);
			coordenates.setLongitude(longitude);
			rende.setCoordenates(coordenates);
			rende.setIsDraft(isDraft);
			rende.setAdultOnly(adultOnly);
			rende.setIsDeleted(isDeleted);
			rendeService.save(rende);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	

	@Test
	public void driverRSVP(){
		Object testing[][]= {
				//Casos de uso Acme-Rendezvous 5.4
				//User1 es el propietario del rende1 por lo que no puede hacer RSVP ya que est� obligado a asistir a ese Rende
				//Por lo tanto es como si ya hubiera hecho RSVP
				
				{"user1",this.getEntityId("rende1"),IllegalArgumentException.class,false},
				//Al igual que no puede hacer RSVP, no puede cancelarlo
				{"user1",this.getEntityId("rende1"),IllegalArgumentException.class,true}, //Cancelar rsvp de un rende propio
				//Los dem�s roles y/o los usuarios no logueados no pueden hacer RSVP puesto que Acme-Rendezvous no menciona nada
				//Sobre ellos
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class,true},
				{"admin",this.getEntityId("rende2"),IllegalArgumentException.class,false}, 
				{null,this.getEntityId("rende2"),IllegalArgumentException.class,false},
				{null,this.getEntityId("rende1"),IllegalArgumentException.class,true}
				
		};
		int size = testing.length;
		for (int i = 0; i < size; i++) {
			this.startTransaction();
			templateRSVP((String) testing[i][0], (Integer) testing[i][1],
					(Class<?>) testing[i][2], (Boolean) testing[i][3]);
			this.rollbackTransaction();
		}
	}

	
	protected void templateRSVP(String username, int idRende, Class<?> expected, Boolean cancelling){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rende = this.rendeService.findOne(idRende);
			User principal = this.userService.findByPrincipal();
			if(cancelling){
				this.rendeService.cancelRsvp(rende, principal);
			} else{
				this.rendeService.rsvp(rende, principal);
			}
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void driverListRSVP(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//List the rendezvouses that he or shes RSVPd.
				//TEST POSITIVO
				{"user1",this.getEntityId("rende1"),null},
				//TEST POSITIVO
				{"user2",this.getEntityId("rende1"),null}, 
				//TEST POSITIVO
				{"user1",this.getEntityId("rende4"),null},  
				//TEST NEGATIVO: Los managers no pueden tener RSVP
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//TEST NEGATIVO: Los admins tampoco
				{"admin",this.getEntityId("rende2"),IllegalArgumentException.class}, 
				//TEST NEGATIVO: Los usuarios no registrados tampoco
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				//TEST NEGATIVO: Los usuarios no registrados tampoco
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateListRSVP((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateListRSVP(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			rendeService.findRSVPSByUser();
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverLinkRende(){
		Object testingData[][]= {
				//"An actor who is authenticated as a user must be able to:
				//. Link one of the rendezvouses that he or shes created to other similar rendezvouses. 16.4
				//El usuario 1 es el creador del rende1 por lo que debe poder editarlo
				{"user1",this.getEntityId("rende1"),null},
				//El usuario 2 no es el creador del rende1 por lo que debe saltar excepci�n
				{"user2",this.getEntityId("rende1"),IllegalArgumentException.class}, 
				//El usuario 1 no es el creador del rende4
				{"user1",this.getEntityId("rende4"),IllegalArgumentException.class},  
				//Los managers no deben de poder editar rendes ya que no los crean
				{"manager1",this.getEntityId("rende4"),IllegalArgumentException.class},
				//Sobre los administradores no se habla nada sobre que puedan editar ni crear rendes, tan s�lo eliminarlos por lo que
				//Debe saltar error
				{"admin",this.getEntityId("rende2"),IllegalArgumentException.class}, 
				//Por supuesto, los usuarios no registrados no pueden editar rendes
				{null,this.getEntityId("rende2"),IllegalArgumentException.class},
				{null,this.getEntityId("rende1"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateLinkRende((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateLinkRende(String username, Integer rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Rende rendeToLink = rendeService.findOne(rendeId);
			Rende linked = this.rendeService.findOne(this.getEntityId("rende3"));
			Collection<Rende> linkeds = rendeToLink.getLinked();
			linkeds.add(linked);
			rendeToLink.setLinked(linkeds);
			rendeService.save(rendeToLink);
			rendeService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	//=====================================================
	 //Listar rendezvous por categoria
	 //
	 //Requisito bajo testing Acme Rendezvous 2.0 - B(10.1)
	 //====================================================
	 
	 @Test
	 public void driverListRendezvousByCategory(){
	  Object testingData[][]= {
		    {null,this.getEntityId("category2"),null},
	  };
	  
	  for (int i = 0; i < testingData.length;i++){
		  templateListRendezvousByCategory((String) testingData[i][0], (int) testingData[i][1] ,(Class<?>) testingData[i][2]); 
	  
	  }
	 }

	 protected void templateListRendezvousByCategory(String username, int categoryId,Class<?> expected){
	  Class<?> caught;
	  caught = null;
	  try{
		   authenticate(username);
		   this.rendeService.findRendezvousByCategory(categoryId);
		   unauthenticate();
	  } catch(Throwable oops){
		  caught = oops.getClass();
	  }
	  checkExceptions(expected, caught);
	 }
	
}
