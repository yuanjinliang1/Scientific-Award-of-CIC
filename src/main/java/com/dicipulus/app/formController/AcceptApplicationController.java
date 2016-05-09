package com.dicipulus.app.formController;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.applicationModel.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;

@Controller
@SessionAttributes("person")
public class AcceptApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(AcceptApplicationController.class);
	
	@RequestMapping(value="/submit-application-by-applier",method=RequestMethod.GET)
	public String submitApplicationByApplier(HttpServletRequest request){
		logger.info("submitApplicationByApplier");
		Person person=FormControllerUlti.getPersonInRequest(request);
		if(person==null){
			return "redirect:/error?message=null-session";
		}
		String applierUid=person.getUid();
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		logger.info(applicationJdbc.getStatusOfApplication(applierUid));
		if(applicationJdbc.getStatusOfApplication(applierUid).contains("未提交")==false){
				return "redirect:/error?message=prerequisite-status-wrong";	
		}
		applicationJdbc.setStatusOfApplication("已提交", applierUid);	

		String previousPage =request.getHeader("Referer");
		return "redirect:"+previousPage;
	}
	
	@RequestMapping(value="/submit-application-by-referee/{applierUid}", method=RequestMethod.GET)
	public String submitApplicationByReferee(HttpServletRequest request, @PathVariable("applierUid") String applierUid) throws AuthenticationException{
		logger.info("submitApplicationByReferee");
		Person person=FormControllerUlti.getPersonInRequest(request);
		if(person==null){
			return "redirect:/error?message=null-session";
		}
		
		if(FormControllerUlti.isAuthenticatedToRead(person, applierUid)==false||person.getRole().equals("referee")==false){
			return "redirect:/error?message=no-authentication";
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		if(applicationJdbc.getStatusOfApplication(applierUid).equals("已提交")==false){
				return "redirect:/error?message=prerequisite-status-wrong";	
		}
		applicationJdbc.setStatusOfApplication("已推荐", applierUid);	
		
		String previousPage =request.getHeader("Referer");
		return "redirect:"+previousPage;
	}
	
	@RequestMapping(value="/accept-application-by-admin/{applierUid}", method=RequestMethod.GET)
	public String acceptApplicationByAdmin(HttpServletRequest request, @PathVariable("applierUid") String applierUid) throws AuthenticationException{
		logger.info("acceptApplicationByAdmin");
		Person person=FormControllerUlti.getPersonInRequest(request);
		if(person==null){
			return "redirect:/error?message=null-session";
		}
		
		if(FormControllerUlti.isAuthenticatedToRead(person, applierUid)==false||person.getUid().contains("admin")==false){
			return "redirect:/error?message=no-authentication";
		}
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		if(applicationJdbc.getStatusOfApplication(applierUid).equals("已推荐")==false){
				return "redirect:/error?message=prerequisite-status-wrong";	
		}
		applicationJdbc.setStatusOfApplication("已接收", applierUid);
		
		String previousPage =request.getHeader("Referer");
		return "redirect:"+previousPage;
	}
}
