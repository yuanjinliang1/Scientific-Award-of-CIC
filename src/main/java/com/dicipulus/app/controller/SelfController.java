package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.service.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Controller
public class SelfController{
	private static final Logger logger=LoggerFactory.getLogger(SelfController.class);
	
	//ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	//private final SystemService systemService=(SystemService)context.getBean("systemService");
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String initLoginForm(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLoginForm(Person person){
		logger.info("uid= "+person.getUid()+" password="+person.getPassword()+" role="+person.getRole());
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		/*String password=systemService.findPersonByUid(person.getUid()).getPassword();
		
		if(person.getPassword().equals(password)){
			return "home";
		}
		else {
			return "login";
		}*/
		
		if(person.getUid().contains("admin")){
			AdminJdbc adminJdbc=(AdminJdbc)context.getBean("adminJdbc");
			String password= adminJdbc.getAdmin().getPassword();
			if(password.equals(person.getPassword())){
				return "home";
			}
			else{
				return "login";
			}
		}
		else{
			return "login";
		}
	}
}