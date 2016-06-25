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
import com.dicipulus.app.formController.FormUlti;
import com.dicipulus.app.model.*;

@Controller
@SessionAttributes("person")
public class ApplicationManagedByApplierController {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationManagedByApplierController.class);
	
	@RequestMapping(value="/application-managed-by-applier",method=RequestMethod.GET)
	public ModelAndView getApplicationManagedByReferee(HttpServletRequest request,ModelAndView modelAndView,Person person){
		logger.info("getApplicationManagedByReferee");
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
		if(person.getRole().equals("applier")==false){
			logger.info("non-applier access denied");
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
		modelAndView.setViewName("applicationManagedByApplier");
		return modelAndView;
	}
}
