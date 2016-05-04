package com.dicipulus.app.formController;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * ��һ��ҳ�棺��Ŀ�������������
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit-first-project-basic-situation",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info("editFirstProjectBasicSituationGet()");
		try{
			logger.info("authentication confirmed!");
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
			Person person = FormControllerUlti.getPersonInRequest(request);
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
	 * ��һ��ҳ�棺��Ŀ�����������ύ����
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
		Person personSession =FormControllerUlti.getPersonInRequest(request);
		ApplierJdbc applierJdbc= InitJdbc.initApplierJdbc();
		Applier applier=applierJdbc.getApplierByUid(personSession.getUid());
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		RefereeJdbc refereeJdbc=InitJdbc.initRefereeJdbc();
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
}
