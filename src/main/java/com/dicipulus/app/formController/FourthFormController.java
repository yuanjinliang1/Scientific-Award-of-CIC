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

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FourthFormJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class FourthFormController {
	private static final Logger logger = LoggerFactory.getLogger(FourthFormController.class);
	
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
			Person person= FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
			FourthForm fourthForm=fourthFormJdbc.getFourthForm(person.getUid());
			modelAndView.addObject("fourthForm",fourthForm);
			
			modelAndView.setViewName("editform/editFourthForm");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("get exception!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组浏览第四个表GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/display-fourth-form",method=RequestMethod.GET)
	public ModelAndView displayFourthForm(HttpServletRequest request, ModelAndView modelAndView){
		logger.info("initFourthForm");
		try{
			Person person= FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			FourthFormJdbc fourthFormJdbc=InitJdbc.initFourthFormJdbc();
			FourthForm fourthForm=fourthFormJdbc.getFourthForm(person.getUid());
			modelAndView.addObject("fourthForm",fourthForm);
			
			modelAndView.setViewName("displayform/displayFourthForm");
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
	
}
