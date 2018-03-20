package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Question;
import domain.Rende;
import domain.ReplyQuestion;
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
	@Autowired
	private RendeService rendeService;
	
	
	//================================================================
	//Este test probará que un usuario no autentificado
	//pueda mostar la información de un usuario que haya reservado
	//algún rendezvous, entraremos en detalle en el test específico,
	//y mostrar las respuestas a las preguntas que un usuario ha 
	//registrado.
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (20. 1)
	//================================================================
	@Test
	public void driverFindByPrincipal(){
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
	
	
	
	

	//================================================================
	//Este test consestirá en probar que un usuario pueda responder
	//a las preguntas de un rendezvous que está reservando ahora.
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (21. 2)
	//================================================================
	@Test
	public void driverReplyingToQuestion(){
		Object testingData[][]= {
				//================================================================
				//TEST 1 POSITIVO
				//
				//Para realizar este test comprobaremos que efectivamente un 
				//usuario puede responder a las questions de un rendezvous al que 
				//no esta registrado y se esta registrando.
				//
				//Tomaremos al user1 y lo registraremos en el rendezvous2
				//respondiendo a la pregunta asociada al rendezvous y después
				//procedemos a realizar la RSVP (Como se haría en el sistema).
				//================================================================
				{"user1",this.getEntityId("user1"),this.getEntityId("question2"),this.getEntityId("rende2"),null},
				//================================================================
				//TEST 1 NEGATIVO
				//
				//Comprobaremos que un manager no pueda responder a las preguntas
				//de un rendezvous (ya que no se pueden registrar), saltando una
				//excecpión en el save de replyQuestions.
				//================================================================
				{"manager1",this.getEntityId("user1"),this.getEntityId("question2"),this.getEntityId("rende2"), IllegalArgumentException.class}
		};
			
		for (int i = 0; i < testingData.length;i++){
			templateReplyingToQuestion((String) testingData[i][0],(int) testingData[i][1],(int) testingData[i][2],(int) testingData[i][3] ,(Class<?>) testingData[i][4]); 
		}
	}
	
	
	protected void templateReplyingToQuestion(String principal,int userId,int questionId,int rendeId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(principal);
			
			Rende rende = this.rendeService.findOne(rendeId);			
			
			for (Question q : rende.getQuestions()) {
				ReplyQuestion rq = new ReplyQuestion();
				rq.setQuestion(q);
				rq.setReply("respuesta prueba");

				this.replyQuestionService.save(rq);
			}
			
			User user = this.userService.findOne(userId);
			this.rendeService.rsvp(rende, user);
				
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
}
