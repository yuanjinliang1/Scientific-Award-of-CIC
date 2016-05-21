package com.dicipulus.app.formController;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class FormUltiTests {
	private String message ;
	MockHttpServletRequest request;
	MockHttpSession session;
	private String applierUid;
	private Person self;
	
	@Before
	public void setUp(){
		this.session=new MockHttpSession();
		this.request= new MockHttpServletRequest();
		this.self= new Person();
	}
	
	@Test
	public void testRedirectErrorMessage(){
		message="hello";
		String redirect=FormUlti.redirectErrorMessage(message);
		assertTrue(redirect.equals("redirect:/error?message="+message));
	}
	@Test
	public void testIsAuthenticatedToRead(){
		self.setUid("100116001");
		self.setRole("applier");
		session.setAttribute("person", self);
		request.setSession(session);
		applierUid="100116001";
		
		assertTrue(FormUlti.isAuthenticatedToRead(request, applierUid));
		assertFalse(FormUlti.isAuthenticatedToRead(request, "100116002"));
	}
}
