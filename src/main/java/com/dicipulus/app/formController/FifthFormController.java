package com.dicipulus.app.formController;

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
import com.dicipulus.app.JDBC.FifthObjectiveEvaluationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class FifthFormController {
	private static final Logger logger = LoggerFactory.getLogger(FifthFormController.class);
	
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
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			modelAndView.setViewName("editform/editFifthObjectiveEvaluation");
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
	 * 项目组浏览第五个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-objective-evaluation/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView displayFifthObjectiveEvaluation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("displayFIfthObjectiveEvaluation");
//		try{
//			Person person=(Person) request.getSession().getAttribute("person");
//			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
//			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/displayFifthObjectiveEvaluation");
//			modelAndView.addObject("objectiveEvaluationForm", fifthObjectiveEvaluation);
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("session null pointer!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//		catch(AuthenticationException e){
//			logger.info(e.toString());
//			modelAndView.setViewName("redirect:/noAuthentication");
//			return modelAndView;
//		}
//	}
	
	
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
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			fifthObjectiveEvaluationJdbc.updateFifthObjective(fifthObjectiveEvaluation, applierUid);
			return "redirect:/manage-apply-unit-situation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
}
