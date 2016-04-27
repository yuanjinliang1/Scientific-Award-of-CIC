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
public class SelfManagedByApplierController {
	private static final Logger logger = LoggerFactory
			.getLogger(SelfManagedByApplierController.class);

	private Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}

	private ApplierJdbc initApplierJdbc() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		ApplierJdbc applierJdbc = (ApplierJdbc) context.getBean("applierJdbc");
		context.registerShutdownHook();// shutdown application context, from
										// tutorialpoints.com
		// ((ConfigurableApplicationContext)context).close();//close application
		// context
		return applierJdbc;
	}

	// only when session and ownerUid in url match with each other,
	// authentication is granted
	private boolean isAuthenticated(HttpServletRequest request, String applierUid) {
		Person person = getPersonInRequest(request);
		logger.info("session uid=" + person.getUid() + ", " + "applierUid="
				+ applierUid);
		if (person.getUid().equals(applierUid)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean passwordCheck(String password, String applierUid){
		ApplierJdbc applierJdbc= initApplierJdbc();
		if(applierJdbc.getApplierByUid(applierUid).getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value="/self-managed-by-applier/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selfManagedByApplier(ModelAndView modelAndView,@PathVariable("applierUid") String applierUid,HttpServletRequest request){
		
		logger.info("selfManagedByApplier()");
		
		try{
			if(isAuthenticated(request, applierUid)==false){
					modelAndView.setViewName("redirect:/login");
					logger.info("authentication denied!");
					return modelAndView;
				}
				else{
					logger.info("authentication confirmed!");
					ApplierJdbc applierJdbc=initApplierJdbc();
					
					modelAndView.setViewName("selfManagedByApplier");
					modelAndView.addObject("person",applierJdbc.getApplierByUid(applierUid));
					return modelAndView;
				}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}

	@RequestMapping(value="/self-managed-by-applier/change-name",method=RequestMethod.POST)
	public String changeName(HttpServletRequest request, String name,Person person){
		logger.info("changeName()");
		Person personSession =getPersonInRequest(request);
		if(isAuthenticated(request, person.getUid())){
			ApplierJdbc applierJdbc=initApplierJdbc();
			applierJdbc.changeName(personSession.getUid(),name);
			return "redirect:/self-managed-by-applier/"+personSession.getUid();
		}
		else{
			return "redirect:/login";
		}
	}

	@RequestMapping(value="/self-managed-by-applier/change-password",method=RequestMethod.POST)
	public String changepassword(HttpServletRequest request, String passwordOld,
			String passwordNew1, String passwordNew2,Person person){
		logger.info("changePassword()");
		Person personSession =getPersonInRequest(request);
		if(isAuthenticated(request, person.getUid())&&passwordCheck(passwordOld,person.getUid())){
			if(passwordNew1.equals(passwordNew2)){
				ApplierJdbc applierJdbc=initApplierJdbc();
				applierJdbc.changePassword(person.getUid(), passwordNew2);
			}
			else{
				//do nothing
			}
			return "redirect:/self-managed-by-applier/"+personSession.getUid();
		}
		else{
			return "redirect:/login";
		}
		
	}
}