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
public class ConfirmApplicationController{
	private static final Logger logger = LoggerFactory
			.getLogger(ConfirmApplicationController.class);
	
	@Deprecated
	@RequestMapping(value="/confirm-referee-unit-opinion-by-referee", method=RequestMethod.GET)
	public String confirmRefereeUnitOpinionByReferee(HttpServletRequest request, String applierUid){
		logger.info("confirmRefereeUnitOpinionByReferee");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
			if(!applicationJdbc.getStatusOfApplication(person.getUid()).equals("已提交")){
				return "redirect:/error?message=status-prerequisite-not-fullfilled";
			}
			
			SecondRefereeUnitOpinion secondRefereeUnitOpinion=InitJdbc.initSecondRefereeUnitOpinionJdbc().getSecondRefereeUnitOpinion(applierUid);
			InitJdbc.initFirstProjectBasicSituationJdbc().setRefereeInformation(secondRefereeUnitOpinion, applierUid);
			
			applicationJdbc.setStatusOfApplication("已推荐", person.getUid());
			return "redirect:/display-referee-unit-opinion/"+applierUid;
		}
		catch(NullPointerException e){
			logger.info(e.toString());
			return "redirect:/login";
		}
	}
}