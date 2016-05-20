package com.dicipulus.app.formController;

import java.util.Calendar;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FirstProjectBasicSituationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.RefereeJdbc;
import com.dicipulus.app.applicationModel.Constants;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.model.Referee;

@Controller
@SessionAttributes("person")
public class FirstFormController {
	private static final Logger logger = LoggerFactory.getLogger(FirstFormController.class);
	/**
	 * 第一个页面：项目基本情况表的编辑
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-first-project-basic-situation",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editFirstProjectBasicSituationGet()");
		try{
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
			Person person = FormUlti.getPersonInRequest(request);
			FirstProjectBasicSituation firstForm=firstProjectBasicSituationJdbc.getFirstProjectBasicSituation(person.getUid());
			modelAndView.setViewName("editform/editFirstProjectBasicSituation");
			modelAndView.addObject("applier",applierJdbc.getApplierByUid(person.getUid()));
			modelAndView.addObject("firstForm",firstForm);
			modelAndView.addObject("subjectCategories",Constants.SUBJECTCATEGORIES);
			modelAndView.addObject("economicFields",Constants.ECONOMICFIELDS);
			modelAndView.addObject("nationalFocusFields",Constants.NATIONALFOCUSFIELDS);
			modelAndView.addObject("technologicalFields",Constants.TECHNOLOGICALFIELDS);
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
	 * 第一个页面：项目基本情况表的提交处理
	 * @param request
	 * @param firstForm
	 * @return
	 */
	@RequestMapping(value="/save-first-project-basic-situation",method=RequestMethod.POST)
	public String saveFirstProjectBasicSituation(HttpServletRequest request,
			@ModelAttribute("firstFormAttr") FirstProjectBasicSituation firstForm){
		logger.info("saveFirstProjectBasicSituation()");
		/*
		 * 准备工作
		 */
		Person personSession =FormUlti.getPersonInRequest(request);
		ApplierJdbc applierJdbc= InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(personSession.getUid());
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		RefereeJdbc refereeJdbc=InitJdbc.initRefereeJdbc();
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
		firstProjectBasicSituationJdbc.setFirstProjectBasicSituation(firstForm, personSession.getUid());
		return "redirect:/edit-first-project-basic-situation";
	}
	
	@RequestMapping(value="/display-first-project-basic-situation/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displayFirstProjectBasicSituationGet(ModelAndView modelAndView, HttpServletRequest request,@PathVariable("applierUid") String applierUid){
		logger.info("displayFirstProjectBasicSituationGet()");
		try{
			FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
			Person person = FormUlti.getPersonInRequest(request);
			FirstProjectBasicSituation firstForm=firstProjectBasicSituationJdbc.getFirstProjectBasicSituation(applierUid);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			FormUlti.isAuthenticatedToRead(person, applierUid);
			
			modelAndView.addObject("firstForm",firstForm);
			modelAndView.addObject("subjectCategories",Constants.SUBJECTCATEGORIES);
			modelAndView.addObject("economicFields",Constants.ECONOMICFIELDS);
			modelAndView.addObject("nationalFocusFields",Constants.NATIONALFOCUSFIELDS);
			modelAndView.addObject("technologicalFields",Constants.TECHNOLOGICALFIELDS);
			modelAndView.addObject("taskSources",Constants.TASKSOURCES);
			
			modelAndView.setViewName("displayform/displayFirstProjectBasicSituation");
			return modelAndView;
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
		catch(AuthenticationException e){
			logger.info(e.toString());
			modelAndView.setViewName("redirect:/noAuthentication");
			return modelAndView;
		}
	}
}
