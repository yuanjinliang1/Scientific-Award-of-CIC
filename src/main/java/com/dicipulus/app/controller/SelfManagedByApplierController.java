package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.formController.FormUlti;
import com.dicipulus.app.model.*;

import java.text.DateFormat;
import java.util.Arrays;
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
import org.springframework.dao.DataAccessException;
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
		Person person = FormUlti.getPersonInRequest(request);
		logger.info("session uid=" + person.getUid() + ", " + "applierUid="
				+ applierUid);
		if (person.getUid().equals(applierUid)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean passwordCheck(String password, String applierUid){
		ApplierJdbc applierJdbc= InitJdbc.initApplierJdbc();
		if(applierJdbc.getApplierByUid(applierUid).getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@RequestMapping(value="/self-managed-by-applier/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selfManagedByApplier(ModelAndView modelAndView,@PathVariable("applierUid") String applierUid,HttpServletRequest request,Person person){
		logger.info("selfManagedByApplier");
		if(request==null){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		try{
			person=FormUlti.getPersonInRequest(request);
		}
		//though catching runtime exception is not a good practice, here we just do it.
		catch(NullPointerException e){
			logger.info("session null pointer!\n Bad Clause in:\"Person person=FormUlti.getPersonInRequest(request);\" ");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		
		if(person==null){
			logger.info("person==null ");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		//guard clause
		if(person.getUid().equals(applierUid)==false){
			logger.info("non-applier access denied");
			logger.info("person.uid="+person.getUid()+", applierUid="+applierUid);
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		Application application=new Application();
		try{
			application=applicationJdbc.getApplicationByApplier(person.getUid());
		}
		catch(DataAccessException e){
			logger.info(e.getLocalizedMessage());
			modelAndView.setViewName("redirect:/data-access-exception");
			return modelAndView;
		}
		modelAndView.addObject("application", application);
		
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		try{
		modelAndView.addObject("person",applierJdbc.getApplierByUid(applierUid));
		}
		catch(DataAccessException e){
			logger.info(e.getLocalizedMessage());
			modelAndView.setViewName("redirect:/data-access-exception");
			return modelAndView;
		}
		modelAndView.setViewName("selfManagedByApplier");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/set-result-registration", method=RequestMethod.POST)
	public String setResultRegistration( HttpServletRequest request,String resultRegistration){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		if(FormUlti.getPersonInRequest(request)==null){
			return FormUlti.redirectErrorMessage("null-session");
		}
		if(FormUlti.rightRole(request, "applier")==false){
			return FormUlti.redirectErrorMessage("illegal-role");
		}
		String applierUid=FormUlti.getPersonInRequest(request).getUid();
		if(FormUlti.rightProjectStatus(applierUid, Arrays.asList("δ�ύ"))==false){
			return FormUlti.redirectErrorMessage("illegal-status");
		}
		
		try{
			InitJdbc.initApplicationJdbc().setResultRegistration(resultRegistration, applierUid);
		}
		catch(DataAccessException e){
			logger.info(e.getLocalizedMessage());
			return FormUlti.redirectErrorMessage("DataAccessException");
		}
		return FormUlti.redirectPrevious(request);
	}

	@RequestMapping(value="/self-managed-by-applier/change-name",method=RequestMethod.POST)
	public String changeName(HttpServletRequest request, String name,Person person){
		logger.info("changeName()");
		Person personSession =FormUlti.getPersonInRequest(request);
		if(isAuthenticated(request, person.getUid())){
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
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
		Person personSession =FormUlti.getPersonInRequest(request);
		if(isAuthenticated(request, person.getUid())&&passwordCheck(passwordOld,person.getUid())){
			if(passwordNew1.equals(passwordNew2)){
				ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
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