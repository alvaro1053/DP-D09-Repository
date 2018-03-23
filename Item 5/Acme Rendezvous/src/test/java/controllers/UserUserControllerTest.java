package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

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

import domain.Comment;
import domain.User;

import services.UserService;


@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class UserUserControllerTest {

	private MockMvc mockMvc;
	
    @Mock
    private UserService userService;

    @InjectMocks
    private UserUserController userUserController;
    
    
    @Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userUserController).build();
		 
		Date user1Birth = new Date();
		Date user2Birth = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			user1Birth = sdf.parse("30/10/1995");
			user2Birth = sdf.parse("30/10/1960");
		} catch (ParseException e) {
			System.out.println("Problema al crear las fechas");
		}
		
		Collection<Comment> u1Comment = new ArrayList<Comment>();
		Collection<Comment> u2Comment = new ArrayList<Comment>();
		 User u1 = new User();
		 u1.setId(1);
		 u1.setName("user1 name");
		 u1.setSurname("user1 surname");
		 u1.setAddress("user1 address");
		 u1.setDateBirth(user1Birth);
		 u1.setComments(u1Comment);
		 User u2 = new User();
		 u2.setId(2);
		 u2.setName("user2 name");
		 u2.setSurname("user2 surname");
		 u1.setAddress("user2 address");
		 u1.setDateBirth(user2Birth);
		 u1.setComments(u2Comment);
	     Collection<User> cU = new ArrayList<User>(Arrays.asList(u1, u2));
	        
	     Mockito.when(userService.findAll()).thenReturn(cU);
	     Mockito.when(userService.findByPrincipal()).thenReturn(u1);
	}

    @Test
    public void testListUsers() throws Exception {
        mockMvc.perform(get("/user/user/list"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(model().attribute("users", hasSize(2)))
        .andExpect(model().size(3))
        .andExpect(model().attributeExists("uri", "users", "principal"))
        .andExpect(view().name("user/list"));
        
    }
}