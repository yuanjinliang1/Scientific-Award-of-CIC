package com.dicipulus.app.formController;

import java.util.List;

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
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinthMajorOrgContributorJdbc;
import com.dicipulus.app.applicationModel.NinthMajorOrgContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class NinthFormController {
	private static final Logger logger = LoggerFactory.getLogger(NinthFormController.class);
	
	/**
	 * 项目组编辑第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	//"ninth" is ill spelled, embarrassing.
//	@RequestMapping(value="/manage-ninth-major-org-contributor",method=RequestMethod.GET)
//	public ModelAndView manageNinthMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView){
//		logger.info("manageNinthMajorOrgContributor");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(person.getUid());
//			modelAndView.addObject("applier",applier);
//			
//			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
//			List<NinthMajorOrgContributor>  ninthMajorOrgContributors = ninthMajorOrgContributorJdbc.getNinthMajorOrgContributors(person.getUid());
//			modelAndView.addObject("ninthForms", ninthMajorOrgContributors);
//			
//			modelAndView.setViewName("editform/manageNinthOrgContributor");
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("session null pointer!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//	}
	
	/**
	 * 项目组选择第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-ninth-major-org-contributor/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectNinthMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("manageNinthMajorOrgContributor");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier",applier);
//			
//			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
//			List<NinthMajorOrgContributor>  ninthMajorOrgContributors = ninthMajorOrgContributorJdbc.getNinthMajorOrgContributors(applierUid);
//			modelAndView.addObject("ninthForms", ninthMajorOrgContributors);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/selectNinthOrgContributor");
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
	 * 项目组建立第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-ninth-major-org-contributor",method=RequestMethod.POST)
	public String createNinthMajorOrgContributor(HttpServletRequest request,NinthMajorOrgContributor ninthMajorOrgContributor){
		logger.info("createNinthMajorOrgContributor");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			
			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
			List<NinthMajorOrgContributor>  ninthMajorOrgContributors = ninthMajorOrgContributorJdbc.getNinthMajorOrgContributors(person.getUid());
			int rankOfOrg =ninthMajorOrgContributors.size()+1;
			int idOfNinthForm =ninthMajorOrgContributorJdbc.createNinthMajorOrgContributor(person.getUid(), rankOfOrg);
			InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(person.getUid());
			
			return "redirect:/edit-ninth-major-org-contributor/"+idOfNinthForm;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组删除第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-ninth-major-org-contributor",method=RequestMethod.GET)
	public String deleteNinthMajorOrgContributor(HttpServletRequest request, int idOfNinthForm){
		logger.info("deleteNinthMajorOrgContributor");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
			ninthMajorOrgContributorJdbc.deleteNinthMajorOrgContributor(idOfNinthForm);
			InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(person.getUid());
			return "redirect:/manage-ninth-major-org-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第九个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/edit-ninth-major-org-contributor/{idOfNinthForm}",method=RequestMethod.GET)
//	public ModelAndView editNinthMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfNinthForm") int idOfNinthForm){
//		logger.info("editNinthMajorOrgContributor");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(person.getUid());
//			modelAndView.addObject("applier", applier);
//			
//			
//			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
//			NinthMajorOrgContributor ninthMajorOrgContributor=ninthMajorOrgContributorJdbc.getNinthMajorOrgContributor(idOfNinthForm);
//			modelAndView.addObject("ninthForm",ninthMajorOrgContributor);
//			
//			modelAndView.setViewName("editform/editNinthOrgContributor");
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("session null pointer!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//	}
	
	/**
	 * 项目组浏览第九个表 GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-ninth-major-org-contributor/{idOfNinthForm}",method=RequestMethod.GET)
//	public ModelAndView displayNinthMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfNinthForm") int idOfNinthForm){
//		logger.info("editNinthMajorOrgContributor");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
//			NinthMajorOrgContributor ninthMajorOrgContributor=ninthMajorOrgContributorJdbc.getNinthMajorOrgContributor(idOfNinthForm);
//			modelAndView.addObject("ninthForm",ninthMajorOrgContributor);
//			
//			String applierUid=ninthMajorOrgContributor.getApplierUid();
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applier);
//			
//			modelAndView.setViewName("displayform/displayNinthOrgContributor");
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
	 * 项目组编辑第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-ninth-major-org-contributor/{idOfNinthForm}",method=RequestMethod.POST)
	public String saveNinthMajorOrgContributor(HttpServletRequest request,@ModelAttribute("ninthFormAttr") NinthMajorOrgContributor ninthForm,
			@PathVariable("idOfNinthForm") int idOfNinthForm){
		logger.info("saveNinthMajorOrgContributor");
		Person person=FormUlti.getPersonInRequest(request);
		if(person==null){
			return "redirect:/error?message=null-session";
		}
		String applierUid=person.getUid();
		try{
			NinthMajorOrgContributorJdbc ninthMajorOrgContributorJdbc=InitJdbc.initNinthMajorOrgContributorJdbc();
			ninthMajorOrgContributorJdbc.updateNinthMajorOrgContributor(ninthForm);
			ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
			InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(applierUid);
			return "redirect:/manage-ninth-major-org-contributor";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
}
