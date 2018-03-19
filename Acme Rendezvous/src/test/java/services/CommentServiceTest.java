package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
import domain.Rende;


import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class CommentServiceTest extends AbstractTest {

	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RendeService rendeService;
	
	
	@Test
	public void driverCreateComment(){
		final Date currentDate = new Date();
		final String picture = "http://www.picture.com";
		Object testingData[][] = {
				
/*
5.6 An actor who is authenticated as a user must be able to comment on the rendezvouses
that he or she has RSVPd.
*/
				
				//==========================================================================//

				//Tests POSITIVOS 
				//
				//Se pueden crear comments (que tienen asociados un rendezvous) si han RSVPd
				{"user1", "rende1", currentDate, "text", picture, null},
				{"user1", "rende3", currentDate, "text", picture, null},
				{"user2", "rende2", currentDate, "text", picture, null},
				{"user3", "rende4", currentDate, "text", picture, null},
				//El test consiste en probar que SÍ se pueda crear un comment con una picture vacía
				{"user1", "rende1", currentDate, "text", "", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//
				//NO se pueden crear comments si no han RSVPd esa rendezvous
				{"user2", "rende3", currentDate, "text", picture, IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un comment sin estar logeado
				{null, "rende1", currentDate, "text", picture, IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un comment con un usuario inexistente
				{"user100", "rende1", currentDate, "text", picture, IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un comment con un actor sin permiso
				{"manager1", "rende1", currentDate, "text", picture, IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda crear un comment con un text vacío
				{"user1", "rende1", currentDate, "", picture, javax.validation.ConstraintViolationException.class}
				
		
				
				//==========================================================================//
				
		};
		for (int i = 0; i < testingData.length; i++){
			templateCreateComment((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Date) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
		}
	}


	protected void templateCreateComment(final String username, final int rendeId, final Date moment, final String text, final String picture, final Class<?> expected) {

		Class<?> caught;
		caught = null;
		Comment comment;
		Rende rende;
		try{
			super.authenticate(username);
			rende = rendeService.findOne(rendeId);
			comment = commentService.create(rendeId);
			comment.setText(text);
			comment.setPicture(picture);
			comment.setRende(rende);
			commentService.save(comment);
			commentService.flush();
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
6.1 An actor who is authenticated as an administrator must be able to remove a comment
 that he or she thinks is inappropriate
*/
				//==========================================================================//
				//Tests POSITIVOS 
				//El test consiste en probar que SÍ se pueda eliminar un comment logeado como admin
				{"admin", "comment1", null},
				{"admin", "comment2", null},
				{"admin", "comment3", null},
				{"admin", "comment4", null},
				{"admin", "comment5", null},
				{"admin", "comment6", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//El test consiste en probar que NO se pueda borrar un comment sin estar logeado
				{null, "comment1", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un comment sin estar logeado
				{null, "comment3", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un comment sin estar logeado como admin
				{"user1", "comment1", IllegalArgumentException.class},
				//El test consiste en probar que NO se pueda borrar un comment sin estar logeado como admin
				{"manager1", "comment1", IllegalArgumentException.class}
				
		};
		for (int i = 0; i < testingData.length; i++){
			templateDeleteComment((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
		}
	}
	
	protected void templateDeleteComment(final String username, final int commentId, final Class<?> expected) {

		Class<?> caught;
		caught = null;
		Comment comment;
		try{
			super.authenticate(username);
			comment = commentService.findOne(commentId);
			commentService.delete(comment);
			commentService.flush();
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}

}

