package com.dicipulus.app.formController;

import java.util.List;

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
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class NinethFormController {
	private static final Logger logger = LoggerFactory.getLogger(NinethFormController.class);
	
	/**
	 * 项目组编辑第九个表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-nineth-major-org-contributor",method=RequestMethod.GET)
	public ModelAndView manageNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageNinethMajorOrgContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier= applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier",applier);
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			List<NinethMajorOrgContributor>  ninethMajorOrgContributors = ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(person.getUid());
			modelAndView.addObject("ninethForms", ninethMajorOrgContributors);
			
			modelAndView.setViewName("editform/manageNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组建立第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-nineth-major-org-contributor",method=RequestMethod.POST)
	public String createNinethMajorOrgContributor(HttpServletRequest request,NinethMajorOrgContributor ninethMajorOrgContributor){
		logger.info("createNinethMajorOrgContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			List<NinethMajorOrgContributor>  ninethMajorOrgContributors = ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(person.getUid());
			int rankOfOrg =ninethMajorOrgContributors.size()+1;
			ninethMajorOrgContributorJdbc.createNinethMajorOrgContributor(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-nineth-major-org-contributor";
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
	@RequestMapping(value="/delete-nineth-major-org-contributor",method=RequestMethod.GET)
	public String deleteNinethMajorOrgContributor(HttpServletRequest request, int idOfNinethForm){
		logger.info("deleteNinethMajorOrgContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			ninethMajorOrgContributorJdbc.deleteNinethMajorOrgContributor(idOfNinethForm);
			
			return "redirect:/manage-nineth-major-org-contributor";
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
	@RequestMapping(value="/edit-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.GET)
	public ModelAndView editNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("editNinethMajorOrgContributor");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(person.getUid());
			modelAndView.addObject("applier", applier);
			
			
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			NinethMajorOrgContributor ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributor(idOfNinethForm);
			modelAndView.addObject("ninethForm",ninethMajorOrgContributor);
			
			modelAndView.setViewName("editform/editNinethOrgContributor");
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 项目组编辑第九个表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.POST)
	public String saveNinethMajorOrgContributor(HttpServletRequest request,@ModelAttribute("ninethFormAttr") NinethMajorOrgContributor ninethForm,
			@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("saveNinethMajorOrgContributor");
		try{
			NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
			ninethMajorOrgContributorJdbc.updateNinethMajorOrgContributor(ninethForm);
			
			return "redirect:/edit-nineth-major-org-contributor/"+ninethForm.getIdOfNinethForm();
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
}
