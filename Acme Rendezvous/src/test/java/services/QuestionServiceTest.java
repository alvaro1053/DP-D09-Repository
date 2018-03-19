package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Question;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class QuestionServiceTest extends AbstractTest {
	
	@Autowired
	private QuestionService questionService;
	
	
	
	//================================================================
	//Este test consestirá en probar que un usuario pueda eliminar
	//una pregunta que este asociada a un rendezvous que él mismo 
	//haya creado.
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (21.1)
	//================================================================
	@Test
	public void driverDeletingQuestions(){
		Object testingData[][]= {
				//===================================================================
				//TEST 1 POSITIVO
				//
				//Probaremos que un user pueda eliminar una question de un rendezvous
				//que él mismo haya creado.
				//===================================================================
				{"user1",this.getEntityId("question3"),null},
				//===================================================================
				//TEST 2 NEGATIVO
				//
				//Probaremos que un user no pueda eliminar una question de un 
				//rendezvous que él no haya creado.
				//
				//====================================================================
				{"user1", this.getEntityId("question2"), javax.validation.ConstraintViolationException.class},
				//===================================================================
				//TEST 3 NEGATIVO
				//
				//Probaremos que un manager no pueda eliminar una question de un 
				//rendezvous.
				//
				//====================================================================
				{"manager1", this.getEntityId("question1"), javax.validation.ConstraintViolationException.class}
		};
			
		for (int i = 0; i < testingData.length;i++){
			templateDeletingQuestions((String) testingData[i][0],(int) testingData[i][1], (Class<?>) testingData[i][2]); 
		
		}
	}
	
	protected void templateDeletingQuestions(String principal,int questionId, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(principal);
			Question question = this.questionService.findOne(questionId);
			
			this.questionService.delete(question);
			
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
}
