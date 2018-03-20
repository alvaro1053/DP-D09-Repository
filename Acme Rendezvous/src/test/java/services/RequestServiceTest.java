package services;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.CreditCard;
import domain.Rende;
import domain.Request;
import domain.Service;





import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class RequestServiceTest extends AbstractTest {

	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private RendeService rendeService;
	
	@Autowired
	private RequestService requestService;
				
	
	
	
	@Test
	public void driverRequestService(){
		Object testingData[][] = {
				
/*
4.3 Request a service for one of the rendezvouses that he or she´s created. He or she must specify
a valid credit card in every request for a service. Optionally, he or she can provide some comments
in the request.
*/
				
				//==========================================================================//
				//Tests POSITIVOS 
				//
				//El test consiste en probar que SÍ se pueda hacer request de un servicio logeado como user. Los comentarios son opcionales.
				{"user1", "", "Juan Cuesta Sanabria","MasterCard","5220277771031876",7,22,621, "service4", "rende1", null},
				//==========================================================================//
				//Tests NEGATIVOS 
				//
				//El test consiste en probar que NO se pueda hacer request de un servicio si no lo está haciendo sobre una rendezvous que haya creado ese usuario.
				{"user1", "", "Juan Cuesta Sanabria","MasterCard","5220277771031876",7,22,621, "service4", "rende2", IllegalArgumentException.class},
				//El test consiste en probar que No se pueda hacer request de un servicio si no es válida la tarjeta de credito
				{"user1", "", "Juan Cuesta Sanabria","MasterCard","0220277771031876",7,22,621, "service4", "rende1", javax.validation.ConstraintViolationException.class},
				//El test consiste en probar que No se pueda hacer request de un servicio que esté cancelado
				{"user1", "", "Juan Cuesta Sanabria","MasterCard","5220277771031876",7,22,621, "service3", "rende1", IllegalArgumentException.class},
	
		};
		for (int i = 0; i < testingData.length; i++){
			templateRequestService((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], 
					(String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (int) testingData[i][7],
					super.getEntityId((String) testingData[i][8]), super.getEntityId((String) testingData[i][9]),
					(Class<?>) testingData[i][10]);
		}
	}
	
	protected void templateRequestService(final String username,final String comment, final String holderName, final String brandName, final String number, final int expirationMonth, final int expirationYear, final int CVV, final int serviceId, final int rendeId, final Class<?> expected) {

		Class<?> caught;
		caught = null;
		Rende rende;
		Service service;
		Request request;
		
		try{
			super.authenticate(username);
			rende = this.rendeService.findOne(rendeId);
			service = this.serviceService.findOne(serviceId);
			request = requestService.create();
			request.setComment(comment);
			CreditCard creditCard = new CreditCard();
			creditCard.setHolderName(holderName);
			creditCard.setBrandName(brandName);
			creditCard.setNumber(number);
			creditCard.setExpirationMonth(expirationMonth);
			creditCard.setExpirationYear(expirationYear);
			creditCard.setCVV(CVV);
			request.setCreditCard(creditCard);
			request.setRende(rende);
			request.setService(service);
			requestService.save(request);
			requestService.flush();
			unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	

}

