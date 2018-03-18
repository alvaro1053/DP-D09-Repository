package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Rende;
import domain.User;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class ReplyQuestionServiceTest extends AbstractTest {
	
	@Autowired
	private ReplyQuestionService replyQuestionService;
	
	//Supporting services
	@Autowired
	private UserService userService;
	
	
	//===============================================================
	//Este test consestirá en probar que un usuario no autentificado
	//pueda mostar la información de un usuario que haya reservado
	//algún rendezvous, entraremos en detalle en el test específico,
	//y mostrar las respuestas a las preguntas que un usuario ha 
	//registrado.
	//
	//===============================================================
	@Test
	public void diverFindByPrincipal(){
		Object testingData[][]= {
				//================================================================
				//TEST 1 POSITIVO
				//
				//Ya que la realización de este test es algo compleja escogeremos
				//a un usuario que tenga un rendezvous ya registrado, ya que
				//si no tuviese ninguna RSVP mostraría los datos personales de un
				//usuario igualmente como esta probado en (UserServiceTest 4.2).
				//
				//De este usuario extraeremos sus preguntas y las respuestas a 
				//esas preguntas.
				//Este mismo proceso será probado para los distintos tipos de 
				//roles en el sistema.
				//================================================================
				{null,this.getEntityId("user1"),null}
		};
			
		for (int i = 0; i < testingData.length;i++){
			templateFindByPrincipal((String) testingData[i][0],(int) testingData[i][1], (Class<?>) testingData[i][2]); 
		
		}
	}
	
	protected void templateFindByPrincipal(String principal,int userId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(principal);
			
			User user = userService.findOne(userId);
			Rende firstRendeRSVPd = null;
			for(Rende rsvp : user.getrSVPS()){
				firstRendeRSVPd = rsvp;
				break;
			}
			
			this.replyQuestionService.selectByRendeId(firstRendeRSVPd.getId());
			
			
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}
