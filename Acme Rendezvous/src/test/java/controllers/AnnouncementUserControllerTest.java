package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import controllers.user.UserUserController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import domain.User;

import services.UserService;


@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnouncementUserControllerTest {

	private MockMvc mockMvc;
	
    @Mock
    private UserService userService;

    @InjectMocks
    private UserUserController userUserController;
    
    
    @Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		 this.mockMvc = MockMvcBuilders.standaloneSetup(userUserController).build();
		 User u1 = new User();
		 u1.setName("user1");
		 User u2 = new User();
		 u2.setName("user2");
	     Collection<User> cU = new ArrayList<User>(Arrays.asList(u1, u2));
	        
	     Mockito.when(userService.findAll()).thenReturn(cU);
	     Mockito.when(userService.findByPrincipal()).thenReturn(u1);
	}

    @Test
    public void testGetAllPerson() throws Exception {
        
//        p1.setFirstName("firstName01");
//        p1.setLastName("lastName01");
//        p1.setId(123l);
//        p1.setMoney(321d);

      
//        p2.setFirstName("firstName02");
//        p2.setLastName("lastName02");
//        p2.setId(123l);
//        p2.setMoney(321d);


        
        mockMvc.perform(get("/user/user/list"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(model().attribute("users", hasSize(2)))
        .andExpect(model().size(3))
        .andExpect(model().attributeExists("uri", "users", "principal"));
    }
}