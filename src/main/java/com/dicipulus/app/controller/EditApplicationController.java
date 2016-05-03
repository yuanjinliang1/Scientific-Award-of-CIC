package com.dicipulus.app.controller;

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
	 * ѡ������������ҳ��
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-initialize-application",method=RequestMethod.GET)
	public ModelAndView editInitializeApplicationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editInitializeApplication()");
		try{
			ApplierJdbc applierJdbc=initApplierJdbc();
			modelAndView.setViewName("edit/editInitializeApplication");
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
	 * ѡ����������͵��ύ
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
			createFormsJdbc.createAllForms(applier);//��ʼ�����б�
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
	 * �Ƽ���������һ��ҳ�棺��Ŀ�������������
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
			modelAndView.setViewName("edit/editFirstProjectBasicSituation");
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
	 * �Ƽ���������һ��ҳ�棺��Ŀ�����������ύ����
	 * @param request
	 * @param firstForm
	 * @return
	 */
	@RequestMapping(value="/save-first-project-basic-situation",method=RequestMethod.POST)
	public String saveFirstProjectBasicSituation(HttpServletRequest request,
			@ModelAttribute("firstFormAttr") FirstProjectBasicSituation firstForm){
		logger.info("saveFirstProjectBasicSituation()");
		/*
		 * ׼������
		 */
		Person personSession =getPersonInRequest(request);
		ApplierJdbc applierJdbc= initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(personSession.getUid());
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		RefereeJdbc refereeJdbc=initRefereeJdbc();
		Referee referee=refereeJdbc.getRefereeByUid(applier.getOwner());
		/*
		 * ΪForm��ȫ����
		 */
		firstForm.setApplierUid(personSession.getUid());
		firstForm.setYearCreated(Calendar.getInstance().get(Calendar.YEAR));
		firstForm.setRefereeString(referee.getName());
		/*
		 * ����JDBC��Formд�����ݿ�
		 */
		firstProjectBasicSituationJdbc.setFirstProjectBasicSituation(firstForm, personSession.getUid());
		return "redirect:/edit-first-project-basic-situation";
	}
	
	
	
	
	
	/**
	 * �Ƽ��˱༭ĳһ��Ŀ���Ƽ���POST
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
	 * �Ƽ��˱༭ĳһ��Ŀ���Ƽ���GET
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
				modelAndView.setViewName("edit/editSecondRefereeOpinion");
				modelAndView.addObject("secondForm",secondRefereeUnitOpinion);
				modelAndView.addObject("applier",applier);//��ȷ�Ƽ���λ���ڱ༭���Ƽ�����˭��
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
	 * ��Ŀ��༭��������GET
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
			modelAndView.setViewName("edit/editThirdBriefIntroduction");
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
	 * ��Ŀ��༭��������POST
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
	 * ��Ŀ��༭���ĸ���GET
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
			
			modelAndView.setViewName("edit/editFourthForm");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("get exception!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ��༭���ĸ���POST
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
	 * ��Ŀ��༭�������GET
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
			modelAndView.setViewName("edit/editFifthObjectiveEvaluation");
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
	 * ��Ŀ��༭�������POST
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
	 * ��Ŀ��༭��������NT��List+������ GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-paper-monograph",method=RequestMethod.GET)
	public ModelAndView manageSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSixthPaperMonograph");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			List<SixthPaperMonographNT> sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
			modelAndView.setViewName("edit/manageSixthPaperMonographNT");
			modelAndView.addObject("sixthPaperMonographForms", sixthPaperMonographNT);
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	/**
	 * ��Ŀ�齨����������NT POST(������������ר��)
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-paper-monograph",method=RequestMethod.POST)
	public String createSixthPaperMonographNT(HttpServletRequest request){
		logger.info("createSixthPaperMonograph");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.createSixthPaperMonographNT(applierUid);
			return "redirect:/manage-paper-monograph";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * ��Ŀ��ɾ����������(����ר��)NT GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-sixth-paper-monograph",method=RequestMethod.GET)
	public String deleteSixthPaperMonographNT(HttpServletRequest request,int idOfPaperMonograph){
		logger.info("deleteSixthPaperMonographNT");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.deleteSixthPaperMonographNT(idOfPaperMonograph);
			return "redirect:/manage-paper-monograph";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * ��Ŀ��༭��������NT GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.GET)
	public ModelAndView editSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfPaperMonograph")int idOfPaperMonograph){
		logger.info("editSixthPaperMonographNT");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			SixthPaperMonographNT sixthPaperMonographNT=sixthPaperMonographNTJdbc.getSixthPaperMonographNT(idOfPaperMonograph);
			modelAndView.setViewName("edit/editSixthPaperMonographNT");
			modelAndView.addObject("sixthPaperMonograph", sixthPaperMonographNT);
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	/**
	 * ��Ŀ��༭��������NT POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.POST)
	public String saveSixthPaperMonographNT(HttpServletRequest request,@ModelAttribute("paperMonographFormAttr")SixthPaperMonographNT sixthPaperMonographNT,@PathVariable("idOfPaperMonograph") int idOfPaperMonograph){
		logger.info("saveSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.updateSixthPaperMonographNT(sixthPaperMonographNT, idOfPaperMonograph);
			return "redirect:/edit-sixth-paper-monograph/"+idOfPaperMonograph;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * ��Ŀ��༭��������List+������ GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-apply-unit-situation",method=RequestMethod.GET)
	public ModelAndView manageSixthAppliedUnitSituation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageApplyUnitSituation");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			
			
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
			List<SixthApplyUnitSituation> sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getAllSixthApplyUnitSituation(applierUid);
			SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=sixthEconomicAndSocialBenefitJdbc.getSixthEconomicAndSocialBenefit(applierUid);
			modelAndView.addObject("sixthEconomicAndSocialBenefitForms", sixthEconomicAndSocialBenefit);
			modelAndView.addObject("sixthApplyUnitSituationForms", sixthApplyUnitSituation);
			modelAndView.addObject("person", person);
			modelAndView.setViewName("edit/manageSixthApplyUnitSituation");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ��༭�������������Ч���POST
	 * 
	 */
	@RequestMapping(value="/edit-economic-social-benefit",method=RequestMethod.POST)
	public String editSixthEconomicAndSocialBenefit(@ModelAttribute("sixthFormAttr")SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit,HttpServletRequest request){
		logger.info("editSixthEconomicAndSocialBenefit");
		try{
		Person person=(Person)request.getSession().getAttribute("person");
		String applierUid=person.getUid();
		SixthEconomicAndSocialBenefitJdbc  sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
		sixthEconomicAndSocialBenefitJdbc.updateSixthEconomicAndSocialBenefitJdbc(sixthEconomicAndSocialBenefit, applierUid);
		return "redirect:/manage-apply-unit-situation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * ��Ŀ�齨����������(Ӧ�õ�λ�����) POST(�������µ�λ)
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-apply-unit-situation",method=RequestMethod.POST)
	public String createSixthApplyUnitApplication(HttpServletRequest request){
		logger.info("createApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.createSixthApplyUnitSituation(applierUid);
			return "redirect:/manage-apply-unit-situation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
		
	}
	/**
	 * ��Ŀ��ɾ����������(Ӧ�õ�λ�����) GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-sixth-apply-unit-situation",method=RequestMethod.GET)
	public String deleteSixthApplyUnitSituation(HttpServletRequest request,int idOfApplyUnit){
		logger.info("deleteSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.deleteSixthAppliedUnitSituation(idOfApplyUnit);
			return "redirect:/manage-apply-unit-situation";
			
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * ��Ŀ��༭��������(Ӧ�õ�λ�����) GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.GET)
	public ModelAndView editSixthApplyUnitSituation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfApplyUnit")int idOfApplyUnit){
		logger.info("editSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			SixthApplyUnitSituation sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getSixthApplyUnitSituation(idOfApplyUnit);
			modelAndView.addObject("sixthApplyUnitSituationForm", sixthApplyUnitSituation);
			modelAndView.setViewName("edit/editSixthApplyUnitSituation");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	/**
	 * ��Ŀ��༭��������(Ӧ�õ�λ�����) POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.POST)
	public String saveSixthApplyUnitSituation(HttpServletRequest request,@ModelAttribute("sixthFormAttr")SixthApplyUnitSituation sixthApplyUnitSituation,@PathVariable("idOfApplyUnit") int idOfApplyUnit){
		logger.info("saveSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.updateSixthApplyUnitSituation(sixthApplyUnitSituation, idOfApplyUnit);
			return "redirect:/edit-sixth-apply-unit-situation/"+sixthApplyUnitSituation.getIdOfApplyUnit();
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}

	}
	
	/**
	 * ��Ŀ��༭����֪ʶ��Ȩ��LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-seventh-paper-cited-by-others",method=RequestMethod.GET)
	public ModelAndView manageSeventhPaperCitedByOthers(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSeventhPaperCitedByOthers");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			List<SeventhPaperCitedByOthers>  seventhPaperCitedByOtherss = seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(person.getUid());
			modelAndView.addObject("seventhPaperForms", seventhPaperCitedByOtherss);
			
			modelAndView.setViewName("edit/manageSeventhPaperCitedByOthers");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ�齨���������ı����ñ� POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-seventh-paper-cited-by-others",method=RequestMethod.POST)
	public String createSeventhPaperCitedByOthers(HttpServletRequest request,SeventhPaperCitedByOthers seventhPaperCitedByOthers){
		logger.info("createSeventhPaperCitedByOthers");
		try{
			Person person=getPersonInRequest(request);
			
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			List<SeventhPaperCitedByOthers>  seventhPaperCitedByOtherss = seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(person.getUid());
			int rankOfOrg =seventhPaperCitedByOtherss.size()+1;
			seventhPaperCitedByOthersJdbc.createSeventhPaperCitedByOthers(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��ɾ���������ı����ñ�LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-seventh-paper-cited-by-others",method=RequestMethod.GET)
	public String deleteSeventhPaperCitedByOthers(HttpServletRequest request, int idOfSeventhPaperForm){
		logger.info("deleteSeventhPaperCitedByOthers");
		try{
			Person person=getPersonInRequest(request);
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			seventhPaperCitedByOthersJdbc.deleteSeventhPaperCitedByOthers(idOfSeventhPaperForm);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��༭�������ı����ñ�POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-seventh-paper-cited-by-others/{idOfSeventhPaperForm}",method=RequestMethod.POST)
	public String saveSeventhPaperCitedByOthers(HttpServletRequest request,@ModelAttribute("seventhPaperFormAttr") SeventhPaperCitedByOthers seventhPaperForm,
			@PathVariable("idOfSeventhPaperForm") int idOfSeventhPaperForm){
		logger.info("saveSeventhPaperCitedByOthers");
		try{
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			seventhPaperCitedByOthersJdbc.updateSeventhPaperCitedByOthers(seventhPaperForm);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	///////////////////////////////////////////////////////////////////
	/**
	 * ��Ŀ��༭����֪ʶ��Ȩ��LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-seventh-ip-doc",method=RequestMethod.GET)
	public ModelAndView manageSeventhIntellectualPropertyDoc(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSeventhIntellectualPropertyDoc");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			List<SeventhIntellectualPropertyDoc>  seventhIntellectualPropertyDocs = seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(person.getUid());
			modelAndView.addObject("seventhIPForms", seventhIntellectualPropertyDocs);
			
			modelAndView.setViewName("edit/manageSeventhIPDoc");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ�齨������֪ʶ��Ȩ�� POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-seventh-ip-doc",method=RequestMethod.POST)
	public String createSeventhIntellectualPropertyDoc(HttpServletRequest request,SeventhIntellectualPropertyDoc seventhIntellectualPropertyDoc){
		logger.info("createSeventhIntellectualPropertyDoc");
		try{
			Person person=getPersonInRequest(request);
			
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			List<SeventhIntellectualPropertyDoc>  seventhIntellectualPropertyDocs = seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(person.getUid());
			int rankOfOrg =seventhIntellectualPropertyDocs.size()+1;
			seventhIntellectualPropertyDocJdbc.createSeventhIntellectualPropertyDoc(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��ɾ������֪ʶ��Ȩ��LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-seventh-ip-doc",method=RequestMethod.GET)
	public String deleteSeventhIntellectualPropertyDoc(HttpServletRequest request, int idOfSeventhIPForm){
		logger.info("deleteSeventhIntellectualPropertyDoc");
		try{
			Person person=getPersonInRequest(request);
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			seventhIntellectualPropertyDocJdbc.deleteSeventhIntellectualPropertyDoc(idOfSeventhIPForm);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��༭����֪ʶ��Ȩ��POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-seventh-ip-doc/{idOfSeventhIPForm}",method=RequestMethod.POST)
	public String saveSeventhIntellectualPropertyDoc(HttpServletRequest request,@ModelAttribute("seventhIPFormAttr") SeventhIntellectualPropertyDoc seventhIPForm,
			@PathVariable("idOfSeventhIPForm") int idOfSeventhIPForm){
		logger.info("saveSeventhIntellectualPropertyDoc");
		try{
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			seventhIntellectualPropertyDocJdbc.updateSeventhIntellectualPropertyDoc(seventhIPForm);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��༭�ڰ˸���LIST GET
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
			
			modelAndView.setViewName("edit/manageEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ�齨���ڰ˸��� POST
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
			int rankOfContributor =eighthMajorContributors.size()+1;
			eighthMajorContributorJdbc.createEighthMajorContributor(person.getUid(), rankOfContributor);
			
			return "redirect:/manage-eighth-major-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * ��Ŀ��ɾ���ڰ˸���LIST GET
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
	 * ��Ŀ��༭�ڰ˸��� GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
	public ModelAndView editEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("edit/editEighthMajorContributor");
		try{
			Person person=getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier", applier);
			
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			EighthMajorContributor eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributor(idOfEighthForm);
			modelAndView.addObject("eighthForm",eighthMajorContributor);
			
			modelAndView.setViewName("edit/editEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ��༭�ڰ˸��� POST
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
	 * ��Ŀ��༭�ھŸ���LIST GET
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
			
			modelAndView.setViewName("edit/manageNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ�齨���ھŸ��� POST
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
	 * ��Ŀ��ɾ���ھŸ���LIST GET
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
	 * ��Ŀ��༭�ھŸ��� GET
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
			
			modelAndView.setViewName("edit/editNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * ��Ŀ��༭�ھŸ��� POST
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
