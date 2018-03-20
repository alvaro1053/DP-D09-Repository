package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Announcement;
import domain.Rende;


import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private RendeService rendeService;
	

	@Test
	public void driverCreateAnnouncement(){
		final Date currentDate = new Date();
		Object testingData[][] = {
				
/*
16.3 An actor who is authenticated as a user must be able to create an announcement regarding
one of the rendezvouses that he or she´s created previously.
*/
				
				//==========================================================================//

				//Tests POSITIVOS 
				//
				//Se pueden crear announcements si se les asocia un rendezvous y se está logeado como user
				{"user1", "rende1", currentDate, "title", "description", null},
				{"user1", "rende3", currentDate, "title", "description", null},
				{"user2", "rende2", currentDate, "title", "description", null},
				{"user3", "rende4", currentDate, "title", "description", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//
				//El test consiste en probar que NO se pueda crear un announcement sin estar logeado
				{null, "rende1", currentDate, "title", "description", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un announcement con un usuario inexistente
				{"user100", "rende1", currentDate, "title", "description", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un announcement con un actor sin permiso
				{"manager1", "rende1", currentDate, "title", "description", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un announcement con un title vacío
				{"user1", "rende1", currentDate, "", "description", javax.validation.ConstraintViolationException.class},
				//El test consiste en probar que NO se pueda crear un announcement con un decription vacía
				{"user1", "rende1", currentDate, "title", "", javax.validation.ConstraintViolationException.class},
				//El test consiste en probar que NO se pueda crear un announcement con un rende que no haya creado previamente ese user
				{"user1", "rende2", currentDate, "title", "description", IllegalArgumentException.class}
				
				//==========================================================================//
				
		};
		for (int i = 0; i < testingData.length; i++){
			templateCreateAnnouncement((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Date) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
		}
	}


	protected void templateCreateAnnouncement(final String username, final int rendeId, final Date moment, final String title, final String description, final Class<?> expected) {

		Class<?> caught;
		caught = null;
		Announcement announcement;
		Rende rende;
		try{
			super.authenticate(username);
			rende = rendeService.findOne(rendeId);
			announcement = announcementService.create();
			announcement.setTitle(title);
			announcement.setDescription(description);
			announcement.setRende(rende);
			announcementService.save(announcement);
			announcementService.flush();
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	

	@Test
	public void driverDeleteAnnouncement(){
		Object testingData[][] = {
				
/*
17.1 An actor who is authenticated as an administrator must be able to remove an announcement
 that he or she thinks is inappropriate
*/
				
				//==========================================================================//
				//Tests POSITIVOS 
				//El test consiste en probar que SÍ se pueda eliminar un announcement logeado como admin
				{"admin", "announcement1", null},
				{"admin", "announcement2", null},
				{"admin", "announcement3", null},
				{"admin", "announcement4", null},
				{"admin", "announcement5", null},
				{"admin", "announcement6", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//El test consiste en probar que NO se pueda borrar un announcement sin estar logeado
				{null, "announcement1", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un announcement sin estar logeado
				{null, "announcement3", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un announcement sin estar logeado como admin
				{"user1", "announcement1", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un announcement sin estar logeado como admin
				{"manager1", "announcement1", IllegalArgumentException.class}
				
		};
		for (int i = 0; i < testingData.length; i++){
			templateDeleteAnnouncement((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateDeleteAnnouncement(final String username, final int announcementId, final Class<?> expected) {

		Class<?> caught;
		caught = null;
		Announcement announcement;
		try{
			super.authenticate(username);
			announcement = announcementService.findOne(announcementId);
			announcementService.delete(announcement);
			announcementService.flush();
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	
	@Test
	public void diverListAnnouncement(){ 
		Object testingData[][]= {
				
/*
 15.1 An actor who is not authenticated must be able to list the announcements that are associated with each rendezvous.
*/
				//==========================================================================//
				//Tests POSITIVOS 
				{null, "rende1", null},
				{"user1", "rende1", null},
				{"admin", "rende1", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				{"manager1", "rende1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++){
			templateListAnnouncement((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateListAnnouncement(final String username, final int rendeId, final Class<?> expected){
		Class<?> caught;
		caught = null;
		
		try{
			authenticate(username);
			announcementService.announcementsChronological(rendeId);//devuelve la lista de announcement dado una id de un rende (ordenado cronológicamente.
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	

	@Test
	public void diverStreamOfAnnouncements(){ 
		Object testingData[][]= {
				
/*
 16.5 An actor who is authenticated as a user must be able to display a stream of announcements
  that have been posted to the rendezvouses that he or she´s RSVPd. The announcements must be listed
  chronologically in descending order.
*/
				//==========================================================================//
				//Tests POSITIVOS 
				//Un user que tenga hecha/s RSVP debe poder hacer display de los anuncios asociados a sus reservas
				{"user1","user1", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//Un user que NO tenga hecha/s RSVP no debe poder hacer display de los anuncios asociados a sus reservas (ya que no tiene)
				{"user1","user4", IllegalArgumentException.class}
		};
		for (int i = 0; i < testingData.length; i++){
			templateStreamOfAnnouncements((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateStreamOfAnnouncements(final String username, final int userId, final Class<?> expected){
		Class<?> caught;
		caught = null;
		
		try{
			authenticate(username);
			announcementService.announcementsChronologicalByUser(userId);
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}

