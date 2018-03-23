package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import domain.Service;
import forms.ServiceForm;




import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class ServiceServiceTest extends AbstractTest {

	
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private CategoryService categoryService;
				
//Tests pertenecientes a Acme-Rendezvous 2.0
	
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
6.1 An actor who is authenticated as an administrator must be able to CANCEL a service that he or she finds inappropriate.
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
	
	
	@Test
	public void diverEditService(){
		Object testingData[][]= {
/*
 5.2 "An actor who is authenticated as a manager must be able to UPDATE a service"
*/
				//TEST POSITIVO
				{"manager1",this.getEntityId("service1"),null},
				//TEST NEGATIVO Los users no deben poder editar servicios
				{"user1",this.getEntityId("service2"),IllegalArgumentException.class}, 
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateEditService((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateEditService(String username, Integer serviceId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Service service = serviceService.findOne(serviceId);
			service.setName("Prueba"); //Las pruebas de constraints se harán en el create puesto que se comprueban en el save tambien
			service.setDescription("Test de rende");
			serviceService.save(service);
			serviceService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	@Test
	public void diverDeleteService(){
		Object testingData[][]= {
/*
 5.2 "An actor who is authenticated as a manager must be able to DELETE a service"
*/
				//El servicio 1 tiene request asociados por lo que no se debe poder borrar
				//TEST NEGATIVO
				{"manager1",this.getEntityId("service1"),IllegalArgumentException.class},
				//Por el contrario el service 2 no tiene requests por lo que se debe poder borrar sin problemas
				//TEST POSITIVO
				{"manager1",this.getEntityId("service2"),null}, 
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateDeleteService((String) testingData[i][0], (Integer) testingData[i][1],(Class<?>) testingData[i][2]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateDeleteService(String username, Integer serviceId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			Service service = serviceService.findOne(serviceId);
			serviceService.delete(service);
			serviceService.flush();
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	
	@Test
	public void diverCreateService(){
		Object testingData[][]= {
/*
  5.2 "An actor who is authenticated as a manager must be able to CREATE a service"
 */
				//TEST POSITIVO: Los manager pueden crear services 5.2
				//En este caso la URL está vacía pues es opcional. 
				{"manager1","name","Description","https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java",false,
					this.getEntityId("category1"),null},
				//TEST POSITIVO: Lo mismo pero con un User2, NO debe poder crear un service
					{"user2","name","Description","",false,
						this.getEntityId("category1"),IllegalArgumentException.class}, 
				
		};
		
		for (int i = 0; i < testingData.length;i++){
			this.startTransaction();
			templateCreateService((String) testingData[i][0],(String)  testingData[i][1], (String) testingData[i][2],(String) testingData[i][3],
			(Boolean) testingData[i][4],(int) testingData[i][5],(Class<?>) testingData[i][6]); 
			this.rollbackTransaction();
		}
	}

	
	protected void templateCreateService(String username, String name, String description, String picture, Boolean isDeleted, int idCategory, Class<?> expected){
		Class<?> caught;
		caught = null;

		try{
			authenticate(username);
			ServiceForm serviceForm = this.serviceService.create();
			serviceForm.setName(name);
			serviceForm.setDescription(description);
			serviceForm.setPicture(picture);
			serviceForm.setCategory(this.categoryService.findOne(idCategory));
			BindingResult binding = null;
			

			Service service = serviceService.reconstruct(serviceForm, binding);
			this.serviceService.save(service);
			serviceService.flush();
			} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	

}

