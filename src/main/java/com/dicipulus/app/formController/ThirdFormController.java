package com.dicipulus.app.formController;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.ThirdProjectBriefIntroductionJdbc;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class ThirdFormController {
	private static final Logger logger = LoggerFactory.getLogger(ThirdFormController.class);
	
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
			ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
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
	 * 项目组编辑第三个表POST
	 * @param thirdProjectBriefIntroduction
	 * @param request
	 * @param nodel
	 * @return
	 */
	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.POST)
	public String editThirdProjectBriefIntroduction(@ModelAttribute("briefIntroduction")ThirdProjectBriefIntroduction thirdProjectBriefIntroduction,HttpServletRequest request,Model nodel){
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			ThirdProjectBriefIntroductionJdbc thirdProjectBriefInroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
			logger.info(thirdProjectBriefIntroduction.getBriefIntroduction()+"!!!");
			thirdProjectBriefInroductionJdbc.updateThirdProjectBriefIntroduction(thirdProjectBriefIntroduction, person.getUid());
		}
		catch(NullPointerException e){
			logger.info("edit briefIntroduction exception!");
			return "redirect:/login";
		}
		return "redirect:/edit-brief-introduction";
	}
	
}
