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
public class SelfManagedByRefereeController {
	private static final Logger logger = LoggerFactory
			.getLogger(SelfManagedByRefereeController.class);
	
	private Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}
	
	private RefereeJdbc initRefereeJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		context.registerShutdownHook();//shutdown application context, from tutorialpoints.com
		//((ConfigurableApplicationContext)context).close();//close application context
		return refereeJdbc;
	}
	
	private boolean isAuthenticated(HttpServletRequest request, String refereeUid) {
		Person person = getPersonInRequest(request);
		logger.info("session uid=" + person.getUid() + ", " + "refereeUid="
				+ refereeUid);
		if (person.getUid().equals(refereeUid)) {
			return true;
		} else {
			return false;
		}
	}
	

	
	private boolean passwordCheck(String password, String refereeUid){
		RefereeJdbc refereeJdbc= initRefereeJdbc();
		if(refereeJdbc.getRefereeByUid(refereeUid).getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	@RequestMapping(value="/self-managed-by-referee/{refereeUid}",method=RequestMethod.GET)
	public ModelAndView selfManagedByReferee(ModelAndView modelAndView,@PathVariable("refereeUid") String refereeUid,HttpServletRequest request){
		
		logger.info("selfManagedByReferee()");
		
		try{
			if(isAuthenticated(request, refereeUid)==false){
					modelAndView.setViewName("redirect:/login");
					logger.info("authentication denied!");
					return modelAndView;
				}
				else{
					logger.info("authentication confirmed!");
					RefereeJdbc refereeJdbc=initRefereeJdbc();
					
					modelAndView.setViewName("selfManagedByReferee");
					modelAndView.addObject("person",refereeJdbc.getRefereeByUid(refereeUid));
					return modelAndView;
				}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/self-managed-by-referee/change-password",method=RequestMethod.POST)
	public String changepassword(HttpServletRequest request, String passwordOld,
			String passwordNew1, String passwordNew2,Person person){
		logger.info("changePassword()");
		Person personSession =getPersonInRequest(request);
		if(isAuthenticated(request, person.getUid())&&passwordCheck(passwordOld,person.getUid())){
			if(passwordNew1.equals(passwordNew2)){
				RefereeJdbc refereeJdbc=initRefereeJdbc();
				refereeJdbc.changePassword(person.getUid(), passwordNew2);
			}
			else{
				//do nothing
			}
			return "redirect:/self-managed-by-referee/"+personSession.getUid();
		}
		else{
			return "redirect:/login";
		}
	}
}
