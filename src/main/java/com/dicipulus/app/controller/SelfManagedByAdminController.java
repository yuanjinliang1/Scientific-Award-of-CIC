package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;

@Controller
@SessionAttributes("person")
public class SelfManagedByAdminController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SelfManagedByAdminController.class);
	
	private Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}
	
	private AdminJdbc initAdminJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AdminJdbc adminJdbc=(AdminJdbc)context.getBean("adminJdbc");
		context.registerShutdownHook();//shutdown application context, from tutorialpoints.com
		//((ConfigurableApplicationContext)context).close();//close application context
		return adminJdbc;
	}
	
	//only when session and ownerUid in url match with each other, authentication is granted
	private boolean isAuthenticated(HttpServletRequest request){
		HttpSession session= request.getSession();
		Person person =(Person) session.getAttribute("person");
		logger.debug("session uid="+person.getUid());
		if(person.getUid().contains("admin")){
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean passwordCheck(String password){
		AdminJdbc adminJdbc= initAdminJdbc();
		if(adminJdbc.getAdmin().getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value="/self-managed-by-admin",method=RequestMethod.GET)
	public ModelAndView selfManagedByAdmin(ModelAndView modelAndView, HttpServletRequest request){
		
		logger.info("selfManagedByAdmin()");
		
		try{
			if(isAuthenticated(request)==false){
					modelAndView.setViewName("redirect:/login");
					logger.info("authentication denied!");
					return modelAndView;
				}
				else{
					logger.info("authentication confirmed!");
					AdminJdbc adminJdbc=initAdminJdbc();
					
					modelAndView.setViewName("selfManagedByAdmin");
					modelAndView.addObject("person",adminJdbc.getAdmin());
					return modelAndView;
				}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/self-managed-by-admin/change-password",method=RequestMethod.POST)
	public String changepassword(HttpServletRequest request, String passwordOld,
			String passwordNew1, String passwordNew2,Person person){
		logger.info("changePassword()");
		Person personSession =getPersonInRequest(request);
		if(isAuthenticated(request)&&passwordCheck(passwordOld)){
			if(passwordNew1.equals(passwordNew2)){
				AdminJdbc adminJdbc=initAdminJdbc();
				adminJdbc.changePassword(passwordNew2);
			}
			else{
				//do nothing
			}
			return "redirect:/self-managed-by-admin";
		}
		else{
			return "redirect:/login";
		}
	}
}