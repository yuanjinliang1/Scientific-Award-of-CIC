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
public class InitFormController {
	private static final Logger logger = LoggerFactory.getLogger(InitFormController.class);
	
	/**
	 * 选择申请表的类型页面
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-initialize-application",method=RequestMethod.GET)
	public ModelAndView editInitializeApplicationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editInitializeApplication()");
		try{
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			modelAndView.setViewName("editform/editInitializeApplication");
			Person person = FormControllerUlti.getPersonInRequest(request);
			modelAndView.addObject("person",applierJdbc.getApplierByUid(person.getUid()));
			return modelAndView;
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}

	/**
	 * 选择申请表类型的提交
	 * @param request
	 * @param applicationType
	 * @return
	 */
	@RequestMapping(value="/edit-initialize-application",method=RequestMethod.POST)
	public String editInitializeApplicationPost(HttpServletRequest request, String applicationType){
		logger.info("editInitializeApplicationPost()");
		try{
			Person person = FormControllerUlti.getPersonInRequest(request);
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			CreateFormsJdbc createFormsJdbc=InitJdbc.initCreateFormsJdbc();
			applierJdbc.setApplicationType(person.getUid(),applicationType);
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			createFormsJdbc.createAllForms(applier);//初始化所有表
			return "redirect:/edit-first-project-basic-situation";
		}
		catch(NullPointerException e){
			logger.info(e.toString());
			return "redirect:/login";
		}
		catch(DuplicateKeyException e){
			logger.info("already have forms!");
			return "redirect:/edit-first-project-basic-situation";

		}
	}
}