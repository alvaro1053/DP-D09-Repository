package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Service;




import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class ServiceServiceTest extends AbstractTest {

	
	@Autowired
	private ServiceService serviceService;
				
	
	@Test
	public void diverListServices(){ 
		Object testingData[][]= {
				
/*
4.2 An actor who is authenticated as a user must be able to list the services that are available in the system.
*/
				//==========================================================================//
				//Tests POSITIVOS 
				//
				//Este test consiste en probar que un usuario logeado puede listar los servicios
				{"user1", null},
				//Este test consiste en probar que un admin logeado puede listar los servicios
				{"admin", null},
				//Este test consiste en probar que un manager logeado puede listar los servicios
				{"manager1", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//
				//Este test consiste en probar que un usuario no logeado NO pueda acceder a la lista de servicios
				{null, IllegalArgumentException.class},
				//Este test consiste en probar que un usuario inexistente NO pueda acceder a la lista de servicios
				{"user100", IllegalArgumentException.class},

		};
		for (int i = 0; i < testingData.length; i++){
			templateListServices((String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}
	
	protected void templateListServices(final String username, final Class<?> expected){
		Class<?> caught;
		caught = null;
		
		try{
			authenticate(username);
			serviceService.findAll();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	@Test
	public void diverCancelService(){ 
		Object testingData[][]= {
				
/*
6.1 An actor who is authenticated as an administrator must be able to cancel a service that he or she finds inappropriate.
 Such services cannot be re-quested for any rendezvous. They must be flagged appropriately when listed.
*/
				//==========================================================================//
				//Tests POSITIVOS 
				//
				//Este test consiste en probar que un admin logeado puede cancelar un servicio 
				{"admin", "service1", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//
				//Este test consiste en probar que un usuario no logeado NO pueda cancelar un servicio 
				{null, "service1", IllegalArgumentException.class},
				//Este test consiste en probar que un user logeado NO pueda cancelar un servicio
				{"user1", "service1", IllegalArgumentException.class},
				//Este test consiste en probar que un manager logeado NO pueda cancelar un servicio
				{"manager1", "service1", IllegalArgumentException.class},
				//Este test consiste en probar que un usuario inexistente NO pueda cancelar un servicio 
				{"user100", "service1", IllegalArgumentException.class},

		};
		for (int i = 0; i < testingData.length; i++){
			templateCancelService((String) testingData[i][0],super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateCancelService(final String username,final int serviceId, final Class<?> expected){
		Class<?> caught;
		caught = null;
		Service service;
		
		try{
			authenticate(username);
			service = this.serviceService.findOne(serviceId);
			this.serviceService.saveAdmin(service);
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

