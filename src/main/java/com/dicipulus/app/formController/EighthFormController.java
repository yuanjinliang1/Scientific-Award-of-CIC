package com.dicipulus.app.formController;

import java.util.Arrays;
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

import com.dicipulus.app.JDBC.ApplicationJdbc;
import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.validator.Validator;
@Deprecated
@Controller
@SessionAttributes("person")
//TODO I think I need an abstract controller
public class EighthFormController {
	private static final Logger logger = LoggerFactory.getLogger(EighthFormController.class);
	
	/**
	 * 项目组编辑第八个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/manage-eighth-major-contributor",method=RequestMethod.GET)
//	public ModelAndView manageEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		
//		//TODO test this code
//		if(false==(Validator.validateSession(request, modelAndView)&&
//				Validator.validateRole(request, modelAndView, "applier")&&
//				Validator.validateStatus(request, modelAndView, Arrays.asList("未提交")))){	
//			return modelAndView;
//		}
//		//main work
//
//		String applierUid=FormUlti.getPersonInRequest(request).getUid();
//		Applier applier=InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
//		modelAndView.addObject("applier",applier);
//		
//		List<EighthMajorContributor> eighthMajorContributors=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid);
//		modelAndView.addObject("eighthForms", eighthMajorContributors);
//		
//		modelAndView.setViewName("editform/manageEighthMajorContributor");
//		return modelAndView;
//	}
	
	/**
	 * 项目组浏览第八个表LIST GET 
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-eighth-major-contributor/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		
//		//TODO extract validate session into super class: FormController-> FromEditController/ FromDisplayController
//		if(false==(Validator.validateSession(request, modelAndView)&&Validator.validateRead(request, modelAndView, applierUid))){
//			return modelAndView;
//		}
//		//main work
//		Applier applier=InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
//		modelAndView.addObject("applier",applier);
//		
//		List<EighthMajorContributor>  eighthMajorContributors = InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid);
//		modelAndView.addObject("eighthForms", eighthMajorContributors);
//		
//		modelAndView.setViewName("displayform/selectEighthMajorContributor");
//		return modelAndView;
//	}
	
	/**
	 * 项目组建立第八个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/create-eighth-major-contributor",method=RequestMethod.POST)
//	public String createEighthMajorContributor(HttpServletRequest request){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		String applierUid=FormUlti.getPersonInRequest(request).getUid();
//		
//		//TODO create validateData interface to encapsulate arguments, and practice polymorphism
//		String view="";
//		if(false==(Validator.validateSession(request,view )&&Validator.validateRole(request, view, "applier")&&
//				Validator.validateStatus(request, view, Arrays.asList("未提交")))){
//			return view;
//		}
//		
//		List<EighthMajorContributor>  eighthMajorContributors = InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid);
//		int idOfEighthForm =InitJdbc.initEighthMajorContributorJdbc().createEighthMajorContributor(applierUid, eighthMajorContributors.size()+1);
//		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
//		
//		return "redirect:edit-eighth-major-contributor/"+idOfEighthForm;
//	}
	
	/**
	 * 项目组删除第八个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/delete-eighth-major-contributor",method=RequestMethod.GET)
//	public String deleteEighthMajorContributor(HttpServletRequest request, int idOfEighthForm){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
//		String view="";
//		if(false==(Validator.validateSession(request, view)&&Validator.validateRole(request, view, "applier")&&
//				Validator.validateUid(request, view, applierUid)&&
//				Validator.validateStatus(request, view, Arrays.asList("未提交")))){
//			return view;
//		}
//		//main work
//		InitJdbc.initEighthMajorContributorJdbc().deleteEighthMajorContributor(idOfEighthForm);
//		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
//		
//		return "redirect:/manage-eighth-major-contributor";
//	}
	
	/**
	 * 项目组编辑第八个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/edit-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
//	public ModelAndView editEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
//		if(false==(Validator.validateSession(request, modelAndView)&&Validator.validateUid(request, modelAndView, applierUid)&&
//				Validator.validateStatus(request, modelAndView, Arrays.asList("未提交")))){
//			return modelAndView;
//		}
//		//main work
//		Applier applier=InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
//		modelAndView.addObject("applier",applier);
//
//		EighthMajorContributor eighthMajorContributor=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm);
//		modelAndView.addObject("eighthForm",eighthMajorContributor);
//		
//		modelAndView.setViewName("editform/editEighthMajorContributor");
//		return modelAndView;
//	}
	
	/**
	 * 项目组浏览第八个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
//	public ModelAndView displayEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
//		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
//		if(false==(Validator.validateSession(request, modelAndView)&&Validator.validateRead(request, modelAndView, applierUid))){
//			return modelAndView;
//		}
//		//main work
//		Applier applier=InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
//		modelAndView.addObject("applier",applier);
//		
//		EighthMajorContributor eighthMajorContributor=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm);
//		modelAndView.addObject("eighthForm",eighthMajorContributor);
//		
//		modelAndView.setViewName("displayform/displayEighthMajorContributor");
//		return modelAndView;
//	}
	
	/**
	 * 项目组编辑第八个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/save-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.POST)
//	public String saveEighthMajorContributor(HttpServletRequest request,@ModelAttribute("eighthFormAttr") EighthMajorContributor eighthForm,
//			@PathVariable("idOfEighthForm") int idOfEighthForm){
//		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
//		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
//		String view="";
//		if(false==(Validator.validateSession(request, view)&&Validator.validateRole(request, view, "applier")&&
//				Validator.validateUid(request, view, applierUid)&&
//				Validator.validateStatus(request, view, Arrays.asList("未提交")))){
//			return view;
//		}		
//		InitJdbc.initEighthMajorContributorJdbc().updateEighthMajorContributor(eighthForm);
//		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
//		return "redirect:/manage-eighth-major-contributor";
//	}
}
