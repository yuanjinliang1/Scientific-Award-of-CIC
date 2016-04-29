package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.applicationModel.Constants;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinionTA;
import com.dicipulus.app.form.*;

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
public class EditApplicationController {
	private static final Logger logger = LoggerFactory
			.getLogger(EditApplicationController.class);
	
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
	
	private SecondRefereeUnitOpinionTAJdbc initSecondRefereeUitOpinionTA(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionTAJdbc=(SecondRefereeUnitOpinionTAJdbc) context.getBean("secondRefereeUnitOpinionTAJdbc");
		return secondRefereeUnitOpinionTAJdbc;
	}
	
	private boolean isAuthenticated(HttpServletRequest request) {
		Person person = getPersonInRequest(request);
		logger.info("session uid=" + person.getUid());
		if (person==null||person.getUid().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	@RequestMapping(value="/edit-initialize-application",method=RequestMethod.GET)
	public ModelAndView editInitializeApplicationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editInitializeApplication()");
		try{
			if(isAuthenticated(request)==false){
				modelAndView.setViewName("redirect:/login");
				logger.info("authentication denied!");
				return modelAndView;
			}
			else{
				logger.info("authentication confirmed!");
				ApplierJdbc applierJdbc=initApplierJdbc();
				modelAndView.setViewName("editInitializeApplication");
				Person person = getPersonInRequest(request);
				modelAndView.addObject("person",applierJdbc.getApplierByUid(person.getUid()));
				return modelAndView;
			}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/edit-initialize-application",method=RequestMethod.POST)
	public String editInitializeApplicationPost(HttpServletRequest request, String applicationType){
		logger.info("editInitializeApplicationPost()");
		if(applicationType.equals("自然科学类")){
			return "redirect:/edit-first-project-basic-situationNS";
		}
		else if(applicationType.equals("科技进步类") ){
			return "redirect:/edit-first-project-basic-situationTA";
		}
		else if(applicationType.equals("技术发明类")){
			return "redirect:/edit-first-project-basic-situationTI";
		}
		else{
			logger.info(applicationType);
			return "redirect:/error";
		}
	}
	
	@RequestMapping(value="/edit-first-project-basic-situationTA",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationTAGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editFirstProjectBasicSituationTAGet()");
		try{
			if(isAuthenticated(request)==false){
				modelAndView.setViewName("redirect:/login");
				logger.info("authentication denied!");
				return modelAndView;
			}
			else{
				logger.info("authentication confirmed!");
				ApplierJdbc applierJdbc=initApplierJdbc();
				modelAndView.setViewName("editFirstProjectBasicSituationTA");
				Person person = getPersonInRequest(request);
				modelAndView.addObject("person",applierJdbc.getApplierByUid(person.getUid()));
				modelAndView.addObject("subjectCategories",Constants.SUBJECTCATEGORIES);
				return modelAndView;
			}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
		
	}
	
	@RequestMapping(value="/edit-referee-unit-opinion",method=RequestMethod.POST)
	public String editSecondRefereeUnitOpinion(@ModelAttribute("secondFormAttri")SecondRefereeUnitOpinionTA secondRefereeUnitOpinionTA,Model model){
		logger.info("editSecondRefereeUnitOpinionPost()");
		SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionJdbc=initSecondRefereeUitOpinionTA();
		try{
		secondRefereeUnitOpinionJdbc.updateSecondRefereeUnitOpinionTA(secondRefereeUnitOpinionTA, 1);
		}
		catch(Exception e){
			logger.info("update fail!");
			return "redirect:/edit-referee-unit-opinion";
		}
		return "redirect:/edit-referee-unit-opinion";
	}
	
	
	
	@RequestMapping(value="/edit-referee-unit-opinion",method=RequestMethod.GET)
	public ModelAndView initSecondRefereeUnitOpinionForm(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("initSecondRefereeUnitOpinionForm");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
		
		String applierUid=person.getUid();
		if(applierUid.equals("")){
			modelAndView.setViewName("redirect:/login");
			logger.info("session is null!");
			return modelAndView;
		}
		logger.info("session confirm!");
		SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionTAJdbc=initSecondRefereeUitOpinionTA();
		SecondRefereeUnitOpinionTA secondRefereeUnitOpinionTA=secondRefereeUnitOpinionTAJdbc.getSecondRefereeUnitOpinionTA(applierUid);
		modelAndView.setViewName("editSecondRefereeOpinion");
		modelAndView.addObject("secondForm",secondRefereeUnitOpinionTA);
		}
		catch(Exception e){
			logger.info("Exception");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		return modelAndView;
	}
}