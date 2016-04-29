package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.applicationModel.*;
import com.dicipulus.app.form.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.format.annotation.DateTimeFormat;
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
	
	private CreateFormsJdbc initCreateFormsJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		CreateFormsJdbc createFormsJdbc = (CreateFormsJdbc) context.getBean("createFormsJdbc");
		context.registerShutdownHook();
		return createFormsJdbc;
	}
	
	private SecondRefereeUnitOpinionTAJdbc initSecondRefereeUitOpinionTA(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionTAJdbc=(SecondRefereeUnitOpinionTAJdbc) context.getBean("secondRefereeUnitOpinionTAJdbc");
		context.registerShutdownHook();
		return secondRefereeUnitOpinionTAJdbc;
	}
	private RefereeJdbc initRefereeJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		context.registerShutdownHook();
		return refereeJdbc;
	}
	private FirstProjectBasicSituationTAJdbc initFirstProjectBasicSituationTAJdbc() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		FirstProjectBasicSituationTAJdbc firstProjectBasicSituationTAJdbc = (FirstProjectBasicSituationTAJdbc) context.getBean("firstProjectBasicSituationTAJdbc");
		context.registerShutdownHook();// shutdown application context, from
										// tutorialpoints.com
		// ((ConfigurableApplicationContext)context).close();//close application
		// context
		return firstProjectBasicSituationTAJdbc;
	}
	private boolean isAuthenticated(Applier applier, Person refereePerson){
		if(applier.getOwner().equals(refereePerson.getUid())){
			return true;
		}
		else{
			return false;
		}
	}
	
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
			ApplierJdbc applierJdbc=initApplierJdbc();
			modelAndView.setViewName("editInitializeApplication");
			Person person = getPersonInRequest(request);
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
			Person person = getPersonInRequest(request);
			ApplierJdbc applierJdbc=initApplierJdbc();
			CreateFormsJdbc createFormsJdbc=initCreateFormsJdbc();
			applierJdbc.setApplicationType(person.getUid(),applicationType);
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			createFormsJdbc.createAllForms(applier);//初始化所有表
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
		catch(NullPointerException e){
			logger.info("null session!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 科技进步奖第一个页面：项目基本情况表的浏览
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-first-project-basic-situationTA",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationTAGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editFirstProjectBasicSituationTAGet()");
		try{
			logger.info("authentication confirmed!");
			ApplierJdbc applierJdbc=initApplierJdbc();
			FirstProjectBasicSituationTAJdbc firstProjectBasicSituationTAJdbc=initFirstProjectBasicSituationTAJdbc();
			Person person = getPersonInRequest(request);
			FirstProjectBasicSituationTA firstForm=firstProjectBasicSituationTAJdbc.getFirstProjectBasicSituationTA(person.getUid());
			modelAndView.setViewName("editFirstProjectBasicSituationTA");
			modelAndView.addObject("person2",applierJdbc.getApplierByUid(person.getUid()));
			modelAndView.addObject("firstForm",firstForm);
			modelAndView.addObject("subjectCategories",Constants.SUBJECTCATEGORIES);
			modelAndView.addObject("economicFields",Constants.ECONOMICFIELDS);
			modelAndView.addObject("nationalFocusFields",Constants.NATIONALFOCUSFIELDS);
			modelAndView.addObject("taskSources",Constants.TASKSOURCES);
			return modelAndView;
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}
	
	
	
	/**
	 * 科技进步奖第一个页面：项目基本情况表的提交处理
	 * @param request
	 * @param firstForm
	 * @return
	 */
	@RequestMapping(value="/save-first-project-basic-situation-TA",method=RequestMethod.POST)
	public String saveFirstProjectBasicSituationTA(HttpServletRequest request,
			@ModelAttribute("firstFormAttr") FirstProjectBasicSituationTA firstForm){
		logger.info("saveFirstProjectBasicSituationTA()");
		/*
		 * 准备工作
		 */
		Person personSession =getPersonInRequest(request);
		ApplierJdbc applierJdbc= initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(personSession.getUid());
		FirstProjectBasicSituationTAJdbc firstProjectBasicSituationTAJdbc=initFirstProjectBasicSituationTAJdbc();
		RefereeJdbc refereeJdbc=initRefereeJdbc();
		Referee referee=refereeJdbc.getRefereeByUid(applier.getOwner());
		/*
		 * 为Form补全属性
		 */
		firstForm.setApplierUid(personSession.getUid());
		firstForm.setYearCreated(Calendar.getInstance().get(Calendar.YEAR));
		firstForm.setRefereeString(referee.getName());
		/*
		 * 调用JDBC将Form写入数据库
		 */
		firstProjectBasicSituationTAJdbc.setFirstProjectBasicSituationTA(firstForm, personSession.getUid());
		return "redirect:/edit-first-project-basic-situationTA";
	}
	
	/**
	 * 推荐人编辑某一项目的推荐书POST
	 * @param secondRefereeUnitOpinionTA
	 * @param applierUid
	 * @return
	 */
	@RequestMapping(value="/edit-referee-unit-opinion-post/{applierUid}",method=RequestMethod.POST)
	public String editSecondRefereeUnitOpinion(HttpServletRequest request,
			@ModelAttribute("secondFormAttri")SecondRefereeUnitOpinionTA secondRefereeUnitOpinionTA,@PathVariable("applierUid") String applierUid){
		logger.info("editSecondRefereeUnitOpinionPost()");
		
		try{
			Person person=getPersonInRequest(request);
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			if(isAuthenticated(applier, person)==false){
				logger.info("No authentication to this applier!");
				return "redirect:/login";
			}
			else{
				SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionJdbc=initSecondRefereeUitOpinionTA();
				secondRefereeUnitOpinionJdbc.updateSecondRefereeUnitOpinionTA(secondRefereeUnitOpinionTA, applierUid);
				return "redirect:/edit-referee-unit-opinion/"+applier.getUid();
			}
		}
		catch(NullPointerException e){
			logger.info("null session!");
			return "redirect:/login";
		}
	}
	
	
	
	/**
	 * 推荐人编辑某一项目的推荐书GET
	 * @param request
	 * @param modelAndView
	 * @param ownerUid
	 * @return
	 */
	@RequestMapping(value="/edit-referee-unit-opinion/{applierUid}",method=RequestMethod.GET)
	public ModelAndView initSecondRefereeUnitOpinionForm(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("initSecondRefereeUnitOpinionForm");
		try{
			Person person=getPersonInRequest(request);
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			if(isAuthenticated(applier, person)==false){
				modelAndView.setViewName("redirect:/login");
				logger.info("No authentication to this applier!");
				return modelAndView;
			}
			else{
				SecondRefereeUnitOpinionTAJdbc secondRefereeUnitOpinionTAJdbc=initSecondRefereeUitOpinionTA();
				SecondRefereeUnitOpinionTA secondRefereeUnitOpinionTA=secondRefereeUnitOpinionTAJdbc.getSecondRefereeUnitOpinionTA(applierUid);
				modelAndView.setViewName("editSecondRefereeOpinion");
				modelAndView.addObject("secondForm",secondRefereeUnitOpinionTA);
				modelAndView.addObject("applier",applier);//明确推荐单位正在编辑的推荐书是谁的
				modelAndView.addObject("nominatedAwards",Constants.NOMINATEDAWARDS);
				return modelAndView;
			}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
	}
	
	
}
