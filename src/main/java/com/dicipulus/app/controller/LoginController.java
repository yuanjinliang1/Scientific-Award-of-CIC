package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.download.CombineExcel;
import com.dicipulus.app.download.CombinePdf;
import com.dicipulus.app.model.*;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
import org.springframework.dao.EmptyResultDataAccessException;


@Controller
@SessionAttributes("person")
public class LoginController{
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	//ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	//private final SystemService systemService=(SystemService)context.getBean("systemService");
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String initLoginForm() throws DocumentException, IOException, RowsExceededException, WriteException{
		//CombinePdf.buildPdf("100116001");
		//CombineExcel.buildExcel(2016);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	
	public ModelAndView processLoginForm(Person person,ModelAndView modelAndView){
		logger.info("uid= "+person.getUid()+" password="+person.getPassword()+" role="+person.getRole());
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		try{
		if(person.getUid().contains("admin")||person.getUid().startsWith("0",0)){//admin:001
			AdminJdbc adminJdbc=(AdminJdbc)context.getBean("adminJdbc");
			String password= adminJdbc.getAdmin().getPassword();
			if(password.equals(person.getPassword())){
				modelAndView.setViewName("redirect:/referee-managed-by-admin/referee-view");
				modelAndView.addObject("person",adminJdbc.getAdmin());
			}
			else{
				modelAndView.setViewName("redirect:/login?message=wrong");
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
				modelAndView.setViewName("redirect:/login?message=wrong");
			}
		}
		
		else if(person.getUid().length()==9){
			ApplierJdbc applierJdbc=(ApplierJdbc)context.getBean("applierJdbc");
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			if(applier.getPassword().equals(person.getPassword())){
				String viewName="redirect:/self-managed-by-applier/"+person.getUid();
				modelAndView.setViewName(viewName);
				modelAndView.addObject("person", applier);
			}
			else {
				modelAndView.setViewName("redirect:/login?message=wrong");
			}
		}
		else{
			modelAndView.setViewName("redirect:/login?message=wrong");
		}
		
		return modelAndView;
		}
		catch(EmptyResultDataAccessException e){
			logger.info(e.toString());
			modelAndView.setViewName("redirect:/login?message=wrong");
			return modelAndView;
		}
	}
}