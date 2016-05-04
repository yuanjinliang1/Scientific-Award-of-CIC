package com.dicipulus.app.formController;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.FifthObjectiveEvaluationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class FifthFormController {
	private static final Logger logger = LoggerFactory.getLogger(FifthFormController.class);
	
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
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
			logger.info(fifthObjectiveEvaluation.getObjectiveEvaluation());
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
	 * ��Ŀ������������GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/display-objective-evaluation",method=RequestMethod.GET)
	public ModelAndView displayFifthObjectiveEvaluation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("displayFIfthObjectiveEvaluation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			FifthObjectiveEvaluation fifthObjectiveEvaluation=fifthObjectiveEvaluationJdbc.getFifthObjectiveEvaluation(applierUid);
			logger.info(fifthObjectiveEvaluation.getObjectiveEvaluation());
			modelAndView.setViewName("displayform/displayFifthObjectiveEvaluation");
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
			FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=InitJdbc.initFifthObjectiveEvaluationJdbc();
			fifthObjectiveEvaluationJdbc.updateFifthObjective(fifthObjectiveEvaluation, applierUid);
			return "redirect:/edit-objective-evaluation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
}
