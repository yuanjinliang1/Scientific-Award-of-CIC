package com.dicipulus.app.formController;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dicipulus.app.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={AcceptApplicationController.class})
@WebAppConfiguration
public class AcceptApplicationControllerTests {
	@Autowired
	private AcceptApplicationController acceptApplicationController;
	
	private MockMvc mockMvc;
	private MockHttpServletRequest request;
	private MockHttpSession session;
	private Person person;
	
	@Before
	public void setup(){
		new MockMvcBuilders();
		this.mockMvc=MockMvcBuilders.standaloneSetup(acceptApplicationController).build();
		this.request=new MockHttpServletRequest();
		this.session=new MockHttpSession();
		this.person=new Person();
	}
	
	@Test
	public void testSubmitApplicationByApplier() throws Exception{
		person.setRole("applier");
		person.setUid("100116001");
		session.setAttribute("person", person);
		mockMvc.perform(MockMvcRequestBuilders.get("/submit-application-by-applier").session(session))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name(FormUlti.redirectPrevious(request)));
	}
}
