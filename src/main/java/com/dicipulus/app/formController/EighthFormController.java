package com.dicipulus.app.formController;

import java.util.List;
import java.io.*;

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
import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class EighthFormController {
	private static final Logger logger = LoggerFactory.getLogger(EighthFormController.class);
	
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
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);

			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			List<EighthMajorContributor>  eighthMajorContributors = eighthMajorContributorJdbc.getEighthMajorContributors(person.getUid());
			modelAndView.addObject("eighthForms", eighthMajorContributors);
			
			modelAndView.setViewName("editform/manageEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组浏览第八个表LIST GET 
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/select-eighth-major-contributor/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("selectEighthMajorContributor");
		try{
			/*
			 * 编辑的时候，session中的person不仅用于判断用户是谁，还用来判断要编辑哪个用户
			 * 浏览的时候，session中的person仅用于判断用户是谁，浏览哪个用户则由url参数来判断
			 */
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier",applier);
			/*
			 * 编辑的时候，因为编辑的对象是从用户session里取的，所以如果能编辑那么必定是有权限这么做的。
			 * 但浏览的时候，浏览的对象是从url里面取的，所以需要判断是否有阅读权限!
			 */
			FormControllerUlti.isAuthenticatedToRead(person, applier);
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			List<EighthMajorContributor>  eighthMajorContributors = eighthMajorContributorJdbc.getEighthMajorContributors(applierUid);
			modelAndView.addObject("eighthForms", eighthMajorContributors);
			
			modelAndView.setViewName("displayform/selectEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		catch(AuthenticationException e){
			logger.info(e.toString());
			modelAndView.setViewName("redirect:/noAuthentication");
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
			Person person=FormControllerUlti.getPersonInRequest(request);
			
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
	 * 项目组删除第八个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-eighth-major-contributor",method=RequestMethod.GET)
	public String deleteEighthMajorContributor(HttpServletRequest request, int idOfEighthForm){
		logger.info("deleteEighthMajorContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
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
		logger.info("editform/editEighthMajorContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier", applier);
			
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			EighthMajorContributor eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributor(idOfEighthForm);
			modelAndView.addObject("eighthForm",eighthMajorContributor);
			
			modelAndView.setViewName("editform/editEighthMajorContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组浏览第八个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/display-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
	public ModelAndView displayEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("displayEighthMajorContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
			EighthMajorContributor eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributor(idOfEighthForm);
			modelAndView.addObject("eighthForm",eighthMajorContributor);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(eighthMajorContributor.getApplierUid());
			modelAndView.addObject("applier", applier);
			
			FormControllerUlti.isAuthenticatedToRead(person, applier);
			
			logger.info("auth confirmed");
			modelAndView.setViewName("displayform/displayEighthMajorContributor");
			return modelAndView;
			
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		catch(AuthenticationException e){
			logger.info(e.toString());
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

}
