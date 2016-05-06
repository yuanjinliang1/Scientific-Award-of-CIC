package com.dicipulus.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplicationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.formController.FifthFormController;
import com.dicipulus.app.formController.FormControllerUlti;
import com.dicipulus.app.model.*;

@Controller
@SessionAttributes("person")
public class ApplicationManagedByAdminController {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationManagedByAdminController.class);
	
	@RequestMapping(value="/application-managed-by-admin",method=RequestMethod.GET)
	public ModelAndView getApplicationManagedByAdmin(HttpServletRequest request,ModelAndView modelAndView,Person person){
		logger.info("getApplicationManagedByAdmin");
		
		// guard clause
		if(request==null){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		try{
			person=FormControllerUlti.getPersonInRequest(request);
		}
		//though catching runtime exception is not a good practice, here we just do it.
		catch(NullPointerException e){
			logger.info("session null pointer!\n Bad Clause in:\"Person person=FormControllerUlti.getPersonInRequest(request);\" ");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		
		if(person==null){
			logger.info("person==null ");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		//guard clause
		if(person.getRole().equals("admin")==false){
			logger.info("non-admin access denied");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();

		Applications applications=new Applications();
		try{
			applications=applicationJdbc.getApplicationsByAdmin();
		}
		catch(DataAccessException e){
			logger.info(e.getLocalizedMessage());
			modelAndView.setViewName("redirect:/data-access-exception");
			return modelAndView;
		}
		
		modelAndView.addObject("applications", applications);
		modelAndView.setViewName("applicationManagedByAdmin");
		return modelAndView;
	}
	
	/*
	 * NOT WORKING
	 */
	@RequestMapping(value="/application-managed-by-admin/post",method=RequestMethod.POST)
	public String postApplicationsManagedByAdmin(HttpServletRequest request,@ModelAttribute("applicationsAttr") Applications applications, Person person){
		logger.info("postApplicationManagedByAdmin");
		// guard clause
		if(request==null){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
		try{
			person=FormControllerUlti.getPersonInRequest(request);
		}
		//though catching runtime exception is not a good practice, here we just do it.
		catch(NullPointerException e){
			logger.info("session null pointer!\n Bad Clause in:\"Person person=FormControllerUlti.getPersonInRequest(request);\" ");
			return"redirect:/login";
		}
		
		if(person==null){
			logger.info("person==null ");
			return "redirect:/login";
		}
		//guard clause
		if(person.getRole().equals("admin")==false){
			logger.info("non-admin access denied");
			return "redirect:/login";
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		logger.info("listSize="+applications.getApplicationList().size());
		try{
			applicationJdbc.setApplicationsByAdminAtomic(applications);
		}
		catch(SQLException e){
			logger.info(e.getLocalizedMessage());
			return "redirect:/data-access-exception";
		}
		
		return "redirect:/application-managed-by-admin";
	}
	
	@RequestMapping(value="/application-managed-by-admin/post/{applierUid}",method=RequestMethod.POST)
	public String postApplicationManagedByAdmin(HttpServletRequest request,@ModelAttribute("applicationAttr") Application application, 
			Person person,@PathVariable String applierUid){
		logger.info("postApplicationManagedByAdmin");
		// guard clause
		if(request==null){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
		try{
			person=FormControllerUlti.getPersonInRequest(request);
		}
		//though catching runtime exception is not a good practice, here we just do it.
		catch(NullPointerException e){
			logger.info("session null pointer!\n Bad Clause in:\"Person person=FormControllerUlti.getPersonInRequest(request);\" ");
			return"redirect:/login";
		}
		
		if(person==null){
			logger.info("person==null ");
			return "redirect:/login";
		}
		//guard clause
		if(person.getRole().equals("admin")==false){
			logger.info("non-admin access denied");
			return "redirect:/login";
		}
		
		if(!application.getApplierUid().equals(applierUid)){
			return "redirect:/login";
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		
		try{
			applicationJdbc.setApplicationByAdmin(application);
		}
		catch(DataAccessException e){
			logger.info(e.getLocalizedMessage());
			return "redirect:/data-access-exception";
		}
		
		return "redirect:/application-managed-by-admin";
	}
}
