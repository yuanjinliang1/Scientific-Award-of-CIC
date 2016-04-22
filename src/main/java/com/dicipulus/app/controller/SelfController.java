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

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Controller
@SessionAttributes("person")
public class SelfController{
	private static final Logger logger=LoggerFactory.getLogger(SelfController.class);
	
	//ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	//private final SystemService systemService=(SystemService)context.getBean("systemService");
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String initLoginForm(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	
	public ModelAndView processLoginForm(Person person,ModelAndView modelAndView){
		logger.info("uid= "+person.getUid()+" password="+person.getPassword()+" role="+person.getRole());
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		if(person.getUid().contains("admin")||person.getUid().startsWith("0",0)){//admin:001
			AdminJdbc adminJdbc=(AdminJdbc)context.getBean("adminJdbc");
			String password= adminJdbc.getAdmin().getPassword();
			if(password.equals(person.getPassword())){
				modelAndView.setViewName("refereeManagedByAdmin");
				modelAndView.addObject("person",adminJdbc.getAdmin());
			}
			else{
				modelAndView.setViewName("login");
			}
		}
		
		else if(person.getUid().length()==4){//referee
			RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
			Referee referee=refereeJdbc.getRefereeByUid(person.getUid());
			if(referee.getPassword().equals(person.getPassword())){
				String viewName="redirect:/applier-managed-by-referee/applier-view/"+person.getUid();
				modelAndView.setViewName(viewName);
				modelAndView.addObject("person", referee);
			}
			else {
				modelAndView.setViewName("login");
			}
		}
		
		else if(person.getUid().length()==9){
			ApplierJdbc applierJdbc=(ApplierJdbc)context.getBean("applierJdbc");
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			if(applier.getPassword().equals(person.getPassword())){
				modelAndView.setViewName("home");
				modelAndView.addObject("person", applier);
			}
			else {
				modelAndView.setViewName("login");
			}
		}
		else{
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}
}