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
import org.springframework.http.HttpRequest;

@Controller
@SessionAttributes("person")
public class RefereeManagedByAdminController{
	private static final Logger logger=LoggerFactory.getLogger(RefereeManagedByAdminController.class);
	
	private RefereeJdbc initRefereeJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		context.registerShutdownHook();//shutdown application context, from tutorialpoints.com
		//((ConfigurableApplicationContext)context).close();//close application context
		return refereeJdbc;
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
	
	@RequestMapping(value="/referee-managed-by-admin/referee-view",method=RequestMethod.GET)
	public ModelAndView showRefereeList(ModelAndView modelAndView,HttpServletRequest request){
		logger.info("showRefereeList()");
		try{
			if(isAuthenticated(request)==false){
				modelAndView.setViewName("login");
				logger.info("authentication denied!");
				return modelAndView;
			}
			else{
				logger.info("authentication confirmed!");
				RefereeJdbc refereeJdbc=initRefereeJdbc();
				modelAndView.setViewName("refereeManagedByAdmin");
				modelAndView.addObject("referees",refereeJdbc.getReferees());
				return modelAndView;
			}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
	}
	
	@RequestMapping(value="/referee-managed-by-admin/referee-create", method=RequestMethod.POST)
	public String createReferee(String uid, String name){
		logger.info("createReferee("+name+")");
		RefereeJdbc refereeJdbc=initRefereeJdbc();
		
		refereeJdbc.createReferee(uid, name);
		return "redirect:referee-view";
	}
	
	@RequestMapping(value="/referee-managed-by-admin/reset-password", method=RequestMethod.GET)
	public String resetPassword( @RequestParam String uid){
		logger.info("resetPassword()");
		RefereeJdbc refereeJdbc=initRefereeJdbc();
		
		refereeJdbc.resetPassword(uid);
		return "redirect:/referee-managed-by-admin/referee-view";
	}
	
	@RequestMapping(value="/referee-managed-by-admin/delete-referee", method=RequestMethod.GET)
	public String deleteReferee( @RequestParam String uid){
		logger.info("deleteReferee()");
		RefereeJdbc refereeJdbc=initRefereeJdbc();
		
		refereeJdbc.deleteReferee(uid);
		return "redirect:/referee-managed-by-admin/referee-view";
	}
}