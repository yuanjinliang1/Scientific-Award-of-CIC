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

@Controller
@SessionAttributes("person")
public class RefereeManagedByAdminController{
	private static final Logger logger=LoggerFactory.getLogger(SelfController.class);
	@RequestMapping(value="/referee-managed-by-admin",method=RequestMethod.GET)
	public ModelAndView showRefereeList(ModelAndView modelAndView){
		logger.info("showRefereeList()");
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		modelAndView.setViewName("refereeManagedByAdmin");
		modelAndView.addObject("referees",refereeJdbc.getReferees());
		return modelAndView;
	}
}