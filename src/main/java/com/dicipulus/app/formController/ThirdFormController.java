package com.dicipulus.app.formController;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.ThirdProjectBriefIntroductionJdbc;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.dicipulus.app.model.Applier;
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
//	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.GET)
//	public ModelAndView initThirdProjectBriefIntroduction(HttpServletRequest request,ModelAndView modelAndView){
//		logger.info("initThirdProjectBriefIntroduction");
//		try{
//			Person person=(Person) request.getSession().getAttribute("person");
//			String applierUid=person.getUid();
//			if(applierUid.equals("")){
//				modelAndView.setViewName("redirect:/login");
//				return modelAndView;
//			}
//			logger.info("applierUid confirm!");
//			ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
//			ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=thirdProjectBriefIntroductionJdbc.getThirdProjectBriefIntroduction(person.getUid());
//			modelAndView.setViewName("editform/editThirdBriefIntroduction");
//			modelAndView.addObject("briefIntroductionForm", thirdProjectBriefIntroduction);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//		}
//		catch(NullPointerException e){
//			logger.info("get exception!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//		return modelAndView;
//	}
	
	/**
	 * 浏览第三个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-brief-introduction/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView displayuThirdProjectBriefIntroduction(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("displayThirdProjectBriefIntroduction");
//		try{
//			Person person=(Person) request.getSession().getAttribute("person");
//			
//			ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
//			ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=thirdProjectBriefIntroductionJdbc.getThirdProjectBriefIntroduction(applierUid);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/displayThirdBriefIntroduction");
//			modelAndView.addObject("briefIntroductionForm", thirdProjectBriefIntroduction);
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("get exception!");
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
	 * 项目组编辑第三个表POST
	 * @param thirdProjectBriefIntroduction
	 * @param request
	 * @param nodel
	 * @return
	 */
//	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.POST)
//	public String editThirdProjectBriefIntroduction(@ModelAttribute("briefIntroduction")ThirdProjectBriefIntroduction thirdProjectBriefIntroduction,HttpServletRequest request,Model nodel){
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			ThirdProjectBriefIntroductionJdbc thirdProjectBriefInroductionJdbc=InitJdbc.initThirdProjectBriefIntroductionJdbc();
//			logger.info(thirdProjectBriefIntroduction.getBriefIntroduction()+"!!!");
//			thirdProjectBriefInroductionJdbc.updateThirdProjectBriefIntroduction(thirdProjectBriefIntroduction, person.getUid());
//		}
//		catch(NullPointerException e){
//			logger.info("edit briefIntroduction exception!");
//			return "redirect:/login";
//		}
//		return "redirect:/edit-fourth-form";
//	}
	
}
