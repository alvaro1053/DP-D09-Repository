package services;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class AdminServiceTest extends AbstractTest {
	
	@Autowired
	private AdminService adminService;
	

	//================================================================
	//Este test consistirá en probar que el administrador sea el
	//único que puede ver la información estadística de las queries.
	//
	//
	//Requisito funcional bajo testing Acme Rendezvous 1.0 - C (6. 3),
	//(17. 2) y (22. 1)
	//================================================================
	@Test
	public void driverStatisticsOfAdmin(){
		Object testingData[][]= {
				{"admin",null},
				{"user1",IllegalArgumentException.class},
				{"manager1", IllegalArgumentException.class}
				
		};
		for (int i = 0; i < testingData.length;i++){
			templateStatisticsOfAdmin((String) testingData[i][0], (Class<?>) testingData[i][1]); 
		
		}
	}

	protected void templateStatisticsOfAdmin(String username, Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			
			this.adminService.averageOfAnnouncementPerRende();
			this.adminService.averageOfQuestionsPerRende();
			this.adminService.averageOfRendesPerUser();
			this.adminService.averageOfRepliesPerComment();
			this.adminService.averageOfRepliesPerQuestion();
			this.adminService.averageOfRSVPPerRende();
			this.adminService.averageOfRSVPPerUser();
			
			this.adminService.desviationOfAnnouncementPerRende();
			this.adminService.desviationOfQuestionsPerRende();
			this.adminService.desviationOfRendesPerUser();
			this.adminService.desviationOfRepliesPerComment();
			this.adminService.desviationOfRepliesPerQuestion();
			this.adminService.desviationOfRSVPPerRende();
			this.adminService.desviationOfRSVPPerUser();
			
			this.adminService.rendesWithMoreAnnouncementsThanTheAverage();
			this.adminService.rendesWithMoreLinkedThanTheAverage();
			
			this.adminService.ratioOfUsersWithRendesCreated();
			this.adminService.top10RendesByAttendants();
			
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}


}
