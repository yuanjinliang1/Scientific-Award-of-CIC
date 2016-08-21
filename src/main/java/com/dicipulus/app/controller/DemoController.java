package com.dicipulus.app.controller;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FifthObjectiveEvaluationJdbc;
import com.dicipulus.app.JDBC.FourthFormJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.formController.FormUlti;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value="/demo",method=RequestMethod.GET)
	@Deprecated
	public ModelAndView getDemoFifthForm(HttpServletRequest request, ModelAndView modelAndView){
		logger.info("demoForm");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			modelAndView.setViewName("test");
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
	 * 项目组编辑第四个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/demo2",method=RequestMethod.GET)
	@Deprecated
	public ModelAndView getDemoFourthForm(HttpServletRequest request, ModelAndView modelAndView){
		logger.info("initFourthForm");
		try{
			Person person= FormUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
			FourthForm fourthForm=fourthFormJdbc.getFourthForm(person.getUid());
			modelAndView.addObject("fourthForm",fourthForm);
			
			modelAndView.setViewName("test2");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("get exception!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
}
