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
public class EditApplicationController<ModleAndView> {
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
	
	private SecondRefereeUnitOpinionJdbc initSecondRefereeUitOpinion(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=(SecondRefereeUnitOpinionJdbc) context.getBean("secondRefereeUnitOpinionJdbc");
		context.registerShutdownHook();
		return secondRefereeUnitOpinionJdbc;
	}
	private RefereeJdbc initRefereeJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		context.registerShutdownHook();
		return refereeJdbc;
	}
	private ThirdProjectBriefIntroductionJdbc initThirdProjectBriefInroductionJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		ThirdProjectBriefIntroductionJdbc thirdProjectBriefInroductionJdbc=(ThirdProjectBriefIntroductionJdbc)context.getBean("thirdProjectBriefIntroductionJdbc");
		return thirdProjectBriefInroductionJdbc;
		
	}
	
	private FifthObjectiveEvaluationJdbc initFifthObjectiveEvaluationJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		FifthObjectiveEvaluationJdbc fifthObjectiveEvaluation=(FifthObjectiveEvaluationJdbc) context.getBean("fifthObjectiveEvaluationJdbc");
		return fifthObjectiveEvaluation;
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
	/*
	 * WITHOUT TA
	 */
	/**
	 * 科技进步奖第一个页面：项目基本情况表的浏览
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-first-project-basic-situation",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editFirstProjectBasicSituationGet()");
		try{
			logger.info("authentication confirmed!");
			ApplierJdbc applierJdbc=initApplierJdbc();
			FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
			Person person = getPersonInRequest(request);
			FirstProjectBasicSituation firstForm=firstProjectBasicSituationJdbc.getFirstProjectBasicSituation(person.getUid());
			modelAndView.setViewName("editFirstProjectBasicSituation");
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
	 * 科技进步奖第一个页面：项目基本情况表的提交处理
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
		Person personSession =getPersonInRequest(request);
		ApplierJdbc applierJdbc= initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(personSession.getUid());
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
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
		firstProjectBasicSituationJdbc.setFirstProjectBasicSituation(firstForm, personSession.getUid());
		return "redirect:/edit-first-project-basic-situation";
	}
	
	
	
	
	
	/**
	 * 推荐人编辑某一项目的推荐书POST
	 * @param secondRefereeUnitOpinion
	 * @param applierUid
	 * @return
	 */
	@RequestMapping(value="/edit-referee-unit-opinion-post/{applierUid}",method=RequestMethod.POST)
	public String editSecondRefereeUnitOpinion(HttpServletRequest request,
			@ModelAttribute("secondFormAttri")SecondRefereeUnitOpinion secondRefereeUnitOpinion,@PathVariable("applierUid") String applierUid){
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
				SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=initSecondRefereeUitOpinion();
				secondRefereeUnitOpinionJdbc.updateSecondRefereeUnitOpinion(secondRefereeUnitOpinion, applierUid);
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
				SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUitOpinionJdbc();
				SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
				modelAndView.setViewName("editSecondRefereeOpinion");
				modelAndView.addObject("secondForm",secondRefereeUnitOpinion);
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
		catch(EmptyResultDataAccessException e2){
			logger.info("forms have not been created!");
			Person person=getPersonInRequest(request);
			modelAndView.setViewName("redirect:/applier-managed-by-referee/applier-view/"+person.getUid());
			return modelAndView;
		}
	}
	
	/**
	 * 项目组编辑第三个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.GET)
	public ModelAndView initThirdProjectBriefIntroduction(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("initThirdProjectBriefIntroduction");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			if(applierUid.equals("")){
				modelAndView.setViewName("redirect:/login");
				return modelAndView;
			}
			logger.info("applierUid confirm!");
			ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=initThirdProjectBriefInroductionJdbc();
			ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=thirdProjectBriefIntroductionJdbc.getThirdProjectBriefIntroduction(person.getUid());
			modelAndView.setViewName("editThirdBriefIntroduction");
			modelAndView.addObject("briefIntroductionForm", thirdProjectBriefIntroduction);
		}
		catch(NullPointerException e){
			logger.info("get exception!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		return modelAndView;
	}
	
	/**
	 * 项目组编辑第三个表POST
	 * @param thirdProjectBriefIntroduction
	 * @param request
	 * @param nodel
	 * @return
	 */
	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.POST)
	public String editThirdProjectBriefIntroduction(@ModelAttribute("briefIntroduction")ThirdProjectBriefIntroduction thirdProjectBriefIntroduction,HttpServletRequest request,Model nodel){
		try{
			Person person=getPersonInRequest(request);
			ThirdProjectBriefIntroductionJdbc thirdProjectBriefInroductionJdbc=initThirdProjectBriefInroductionJdbc();
			logger.info(thirdProjectBriefIntroduction.getBriefIntroduction()+"!!!");
			thirdProjectBriefInroductionJdbc.updateThirdProjectBriefIntroduction(thirdProjectBriefIntroduction, person.getUid());
		}
		catch(NullPointerException e){
			logger.info("edit briefIntroduction exception!");
			return "redirect:/login";
		}
		return "redirect:/edit-brief-introduction";
	}
	
	/**
	 * 项目组编辑第四个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-fourth-form",method=RequestMethod.GET)
	public ModelAndView getFourthForm(HttpServletRequest request, ModelAndView modelAndView){
		logger.info("initFourthForm");
		try{
			Person person= getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
			FourthForm fourthForm=fourthFormJdbc.getFourthForm(person.getUid());
			modelAndView.addObject("fourthForm",fourthForm);
			
			modelAndView.setViewName("editFourthForm");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("get exception!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组编辑第四个表POST
	 * @param fourthForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-fourth-form",method=RequestMethod.POST)
	public String editFourthForm(@ModelAttribute("fourthFormAttr")FourthForm fourthForm,HttpServletRequest request){
		logger.info("editFourthForm");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
			fourthFormJdbc.setFourthForm(fourthForm, person.getUid());
			return "redirect:/edit-fourth-form";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第五个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-objective-evaluation",method=RequestMethod.GET)
	public ModelAndView initFifthObjectiveEvaluation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("enter initFIfthObjectiveEvaluation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=initFifthObjectiveEvaluationJdbc();
			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
			logger.info(fifthObjectiveEvaluation.getObjectiveEvaluation());
			modelAndView.setViewName("editFifthObjectiveEvaluation");
			modelAndView.addObject("objectiveEvaluationForm", fifthObjectiveEvaluation);
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	
	/**
	 * 项目组编辑第五个表POST
	 * @param fifthObjectiveEvaluation
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-objective-evaluation",method=RequestMethod.POST)
	public String editFifthObjectiveEvaluation(@ModelAttribute("objectiveEvaluation")FifthObjectiveEvaluation fifthObjectiveEvaluation,HttpServletRequest request){
		logger.info("editFifthObjectiveEvaluation");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=initFifthObjectiveEvaluationJdbc();
			fifthObjectiveEvaluationJdbc.updateFifthObjective(fifthObjectiveEvaluation, applierUid);
			return "redirect:/edit-objective-evaluation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第八个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-eighth-major-contributor",method=RequestMethod.GET)
	public ModelAndView manageEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageEighthMajorContributor");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			List<EighthMajorContributor>  eighthMajorContributors = eighthMajorContributorJdbc.getEighthMajorContributors(person.getUid());
			modelAndView.addObject("eighthForms", eighthMajorContributors);
			
			modelAndView.setViewName("manageEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组建立第八个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-eighth-major-contributor",method=RequestMethod.POST)
	public String createEighthMajorContributor(HttpServletRequest request,EighthMajorContributor eighthMajorContributor){
		logger.info("createEighthMajorContributor");
		try{
			Person person=getPersonInRequest(request);
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			List<EighthMajorContributor>  eighthMajorContributors = eighthMajorContributorJdbc.getEighthMajorContributors(person.getUid());
			int rankOf =eighthMajorContributors.size()+1;
			eighthMajorContributorJdbc.createEighthMajorContributor(person.getUid(), rankOf);
			
			return "redirect:/manage-eighth-major-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组删除第八个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-eighth-major-contributor",method=RequestMethod.GET)
	public String deleteEighthMajorContributor(HttpServletRequest request, int idOfEighthForm){
		logger.info("deleteEighthMajorContributor");
		try{
			Person person=getPersonInRequest(request);
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			eighthMajorContributorJdbc.deleteEighthMajorContributor(idOfEighthForm);
			
			return "redirect:/manage-eighth-major-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第八个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
	public ModelAndView editEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("editEighthMajorContributor");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier", applier);
			
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			EighthMajorContributor eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributor(idOfEighthForm);
			modelAndView.addObject("eighthForm",eighthMajorContributor);
			
			modelAndView.setViewName("editEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组编辑第八个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.POST)
	public String saveEighthMajorContributor(HttpServletRequest request,@ModelAttribute("eighthFormAttr") EighthMajorContributor eighthForm,
			@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("saveEighthMajorContributor");
		try{
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			eighthMajorContributorJdbc.updateEighthMajorContributor(eighthForm);
			
			return "redirect:/edit-eighth-major-contributor/"+eighthForm.getIdOfEighthForm();
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}

	/**
	 * 项目组编辑第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-nineth-major-org-contributor",method=RequestMethod.GET)
	public ModelAndView manageNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageNinethMajorOrgContributor");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			List<NinethMajorOrgContributor>  ninethMajorOrgContributors = ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(person.getUid());
			modelAndView.addObject("ninethForms", ninethMajorOrgContributors);
			
			modelAndView.setViewName("manageNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组建立第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-nineth-major-org-contributor",method=RequestMethod.POST)
	public String createNinethMajorOrgContributor(HttpServletRequest request,NinethMajorOrgContributor ninethMajorOrgContributor){
		logger.info("createNinethMajorOrgContributor");
		try{
			Person person=getPersonInRequest(request);
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			List<NinethMajorOrgContributor>  ninethMajorOrgContributors = ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(person.getUid());
			int rankOfOrg =ninethMajorOrgContributors.size()+1;
			ninethMajorOrgContributorJdbc.createNinethMajorOrgContributor(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-nineth-major-org-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组删除第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-nineth-major-org-contributor",method=RequestMethod.GET)
	public String deleteNinethMajorOrgContributor(HttpServletRequest request, int idOfNinethForm){
		logger.info("deleteNinethMajorOrgContributor");
		try{
			Person person=getPersonInRequest(request);
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			ninethMajorOrgContributorJdbc.deleteNinethMajorOrgContributor(idOfNinethForm);
			
			return "redirect:/manage-nineth-major-org-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第九个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.GET)
	public ModelAndView editNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("editNinethMajorOrgContributor");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier", applier);
			
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			NinethMajorOrgContributor ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributor(idOfNinethForm);
			modelAndView.addObject("ninethForm",ninethMajorOrgContributor);
			
			modelAndView.setViewName("editNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组编辑第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.POST)
	public String saveNinethMajorOrgContributor(HttpServletRequest request,@ModelAttribute("ninethFormAttr") NinethMajorOrgContributor ninethForm,
			@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("saveNinethMajorOrgContributor");
		try{
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			ninethMajorOrgContributorJdbc.updateNinethMajorOrgContributor(ninethForm);
			
			return "redirect:/edit-nineth-major-org-contributor/"+ninethForm.getIdOfNinethForm();
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	
	
}
