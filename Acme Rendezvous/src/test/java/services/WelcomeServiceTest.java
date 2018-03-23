package services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.WelcomePage;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class WelcomeServiceTest extends AbstractTest {
	
	@Autowired
	private WelcomeService welcomeService;
	
	//=====================================================
	//En este test probaremos el welcome service para
	//asegurarnos que él único usuario que pueda modificar 
	//la página de bienvenida sea el administrador y que
	//además sus datos introducidos no sean incorrectos.
	//
	//
	//Este requisito no pertenece a los requisitos 
	//funcionales pero creemos que no está de más realizar
	//pruebas a este servicio.
	//
	//Requisito bajo testing Amce Rendezvous 2.0 - A (13)
	//=====================================================
	@Test
	public void driverFindByPrincipal(){
		Object testingData[][]= {
				//==================================================
				//TEST 1 POSITIVO
				//
				//Un usuario registrado como admin puede modificar
				//y guardar esa misma modificiación de la página 
				//de inicio
				//==================================================
				{"admin", "prueba unitaria",null},
				//==================================================
				//TEST 2 NEGATIVO (Arreglar este)
				//
				//Un usuario registrado como admin puede modificar
				//y guardar esa misma modificiación de la página 
				//de inicio
				//==================================================
				{"admin", null, null},
				//==================================================
				//TEST 3 NEGATIVO
				//
				//Un usuario registrado que no está registrado 
				//como admin no puede modificar la página de inicio
				//
				//y guardar esa misma modificiación de la página 
				//de inicio
				//==================================================
				{"user1", "prueba unitaria", IllegalArgumentException.class}
		};
		for (int i = 0; i < testingData.length;i++){
			templateFindByPrincipal((String) testingData[i][0], (String) testingData[i][1] ,(Class<?>) testingData[i][2]); 
		}
		
	}

	protected void templateFindByPrincipal(String username, String name,Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			WelcomePage welcome = welcomeService.find();
			welcome.setName(name);
			welcome.setEnglishWelcome("English welcome");
			welcome.setSpanishWelcome("Spanish welcome");
			welcome.setBanner("http://www.banklawyersblog.com/.a/6a00d8341c652b53ef01b8d230064f970c-popup");
			this.welcomeService.save(welcome);
			
			Assert.assertTrue(welcomeService.find().getName().equals("prueba unitaria"));
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}

}
