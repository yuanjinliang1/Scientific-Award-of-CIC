package com.dicipulus.app.formController;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplicationJdbc;
import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.SecondRefereeUnitOpinionJdbc;
import com.dicipulus.app.applicationModel.Constants;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class SecondFormController {
	private static final Logger logger = LoggerFactory.getLogger(SecondFormController.class);
	
	private boolean isAuthenticated(Applier applier, Person refereePerson){
		if(applier.getOwner().equals(refereePerson.getUid())){
			return true;
		}
		else{
			return false;
		}
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
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			if(isAuthenticated(applier, person)==false){
				logger.info("No authentication to this applier!");
				return "redirect:/login";
			}
			ApplicationJdbc applicatoinJdbc=InitJdbc.initApplicationJdbc();
			if(applicatoinJdbc.getStatusOfApplication(applierUid)=="已接收"){
				return "redirect:/error?message=prerequisite-status-wrong";
			}
			SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
			secondRefereeUnitOpinionJdbc.updateSecondRefereeUnitOpinion(secondRefereeUnitOpinion, applierUid);
			FormControllerUlti.setRefereeInformationForFirstForm(applierUid);
			return "redirect:/edit-referee-unit-opinion/"+applier.getUid();
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
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			if(isAuthenticated(applier, person)==false){
				modelAndView.setViewName("redirect:/login");
				logger.info("No authentication to this applier!");
				return modelAndView;
			}
			ApplicationJdbc applicatoinJdbc=InitJdbc.initApplicationJdbc();
			if(applicatoinJdbc.getStatusOfApplication(applierUid).equals("已接收")){
				String previousPage =request.getHeader("Referer");
				modelAndView.setViewName("redirect:"+previousPage+"?message=prerequisite-status-wrong");
				return modelAndView;
			}
			SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
			SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
			modelAndView.setViewName("editform/editSecondRefereeOpinion");
			modelAndView.addObject("secondForm",secondRefereeUnitOpinion);
			modelAndView.addObject("applier",applier);//明确推荐单位正在编辑的推荐书是谁的
			modelAndView.addObject("nominatedAwards",Constants.NOMINATEDAWARDS);
			return modelAndView;
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
		catch(EmptyResultDataAccessException e2){
			logger.info("forms have not been created!");
			Person person=FormControllerUlti.getPersonInRequest(request);
			modelAndView.setViewName("redirect:/applier-managed-by-referee/applier-view/"+person.getUid());
			return modelAndView;
		}
	}
	
	/**
	 * 浏览某一项目的推荐书GET
	 * @param request
	 * @param modelAndView
	 * @param ownerUid
	 * @return
	 */
	@RequestMapping(value="/display-referee-unit-opinion/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displaySecondRefereeUnitOpinionForm(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("displaySecondRefereeUnitOpinionForm");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			
			SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
			SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
			modelAndView.addObject("secondForm",secondRefereeUnitOpinion);
			modelAndView.addObject("applier",applier);//明确推荐单位正在编辑的推荐书是谁的
			modelAndView.addObject("nominatedAwards",Constants.NOMINATEDAWARDS);
			
			FormControllerUlti.isAuthenticatedToRead(person, applierUid);

			modelAndView.setViewName("displayform/displaySecondRefereeOpinion");
			return modelAndView;
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			logger.info("null session!");
			return modelAndView;
		}
		catch(EmptyResultDataAccessException e2){
			logger.info("forms have not been created!");
			Person person=FormControllerUlti.getPersonInRequest(request);
			modelAndView.setViewName("redirect:/applier-managed-by-referee/applier-view/"+person.getUid());
			return modelAndView;
		}
		catch(AuthenticationException e){
			logger.info(e.toString());
			modelAndView.setViewName("redirect:/noAuthentication");
			return modelAndView;
		}
	}
}
