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
@Controller
@SessionAttributes("person")
public class ApplierManagedByRefereeController{
	private static final Logger logger=LoggerFactory.getLogger(RefereeManagedByAdminController.class);
	
	private ApplierJdbc initApplierJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		ApplierJdbc applierJdbc=(ApplierJdbc)context.getBean("applierJdbc");
		context.registerShutdownHook();//shutdown application context, from tutorialpoints.com
		//((ConfigurableApplicationContext)context).close();//close application context
		return applierJdbc;
	}
	
	//only when session and ownerUid in url match with each other, authentication is granted
	private boolean isAuthenticated(HttpServletRequest request, String ownerUid){
		HttpSession session= request.getSession();
		Person person =(Person) session.getAttribute("person");
		logger.debug("session uid="+person.getUid()+", "+"ownerUid="+ownerUid);
		if(person.getUid().equals(ownerUid)){
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value="/applier-managed-by-referee/applier-view/{ownerUid}",method=RequestMethod.GET)
	public ModelAndView showRefereeList(ModelAndView modelAndView,@PathVariable("ownerUid") String ownerUid,HttpServletRequest request){
		
		logger.info("showRefereeList()");
		
		try{
			if(isAuthenticated(request, ownerUid)==false){
				//if(1==0){
					modelAndView.setViewName("login");
					logger.info("authentication denied!");
					return modelAndView;
				}
				else{
					logger.info("authentication confirmed!");
					ApplierJdbc applierJdbc=initApplierJdbc();
					
					modelAndView.setViewName("applierManagedByReferee");
					modelAndView.addObject("appliers",applierJdbc.getAppliers(ownerUid));
					return modelAndView;
				}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		
	}
	
}