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

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.SeventhIntellectualPropertyDocJdbc;
import com.dicipulus.app.JDBC.SeventhPaperCitedByOthersJdbc;
import com.dicipulus.app.applicationModel.SeventhIntellectualPropertyDoc;
import com.dicipulus.app.applicationModel.SeventhPaperCitedByOthers;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class SeventhFormController {
	private static final Logger logger = LoggerFactory.getLogger(SeventhFormController.class);
	
	/**
	 * 项目组编辑第七论文被引用LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/manage-seventh-paper-cited-by-others",method=RequestMethod.GET)
//	public ModelAndView manageSeventhPaperCitedByOthers(HttpServletRequest request,ModelAndView modelAndView){
//		logger.info("manageSeventhPaperCitedByOthers");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(person.getUid());
//			modelAndView.addObject("applier",applier);
//			
//			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
//			List<SeventhPaperCitedByOthers>  seventhPaperCitedByOtherss = seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(person.getUid());
//			modelAndView.addObject("seventhPaperForms", seventhPaperCitedByOtherss);
//			
//			modelAndView.setViewName("editform/manageSeventhPaperCitedByOthers");
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("session null pointer!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//	}
	
	/**
	 * 浏览第七论文被引用LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-seventh-paper-cited-by-others/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectSeventhPaperCitedByOthers(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("selectSeventhPaperCitedByOthers");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier",applier);
//			
//			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
//			List<SeventhPaperCitedByOthers>  seventhPaperCitedByOtherss = seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(applierUid);
//			modelAndView.addObject("seventhPaperForms", seventhPaperCitedByOtherss);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/selectSeventhPaperCitedByOthers");
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
	 * 项目组建立第七论文被引用表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-seventh-paper-cited-by-others",method=RequestMethod.POST)
	public String createSeventhPaperCitedByOthers(HttpServletRequest request,SeventhPaperCitedByOthers seventhPaperCitedByOthers){
		logger.info("createSeventhPaperCitedByOthers");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			List<SeventhPaperCitedByOthers>  seventhPaperCitedByOtherss = seventhPaperCitedByOthersJdbc.getSeventhPaperCitedByOtherss(person.getUid());
			int rankOfOrg =seventhPaperCitedByOtherss.size()+1;
			seventhPaperCitedByOthersJdbc.createSeventhPaperCitedByOthers(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组删除第七论文被引用表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-seventh-paper-cited-by-others",method=RequestMethod.GET)
	public String deleteSeventhPaperCitedByOthers(HttpServletRequest request, int idOfSeventhPaperForm){
		logger.info("deleteSeventhPaperCitedByOthers");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			seventhPaperCitedByOthersJdbc.deleteSeventhPaperCitedByOthers(idOfSeventhPaperForm);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第七论文被引用表POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-seventh-paper-cited-by-others/{idOfSeventhPaperForm}",method=RequestMethod.POST)
	public String saveSeventhPaperCitedByOthers(HttpServletRequest request,@ModelAttribute("seventhPaperFormAttr") SeventhPaperCitedByOthers seventhPaperForm,
			@PathVariable("idOfSeventhPaperForm") int idOfSeventhPaperForm){
		logger.info("saveSeventhPaperCitedByOthers");
		try{
			SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
			seventhPaperCitedByOthersJdbc.updateSeventhPaperCitedByOthers(seventhPaperForm);
			
			return "redirect:/manage-seventh-paper-cited-by-others";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	///////////////////////////////////////////////////////////////////
	/**
	 * 项目组编辑第七知识产权表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/manage-seventh-ip-doc",method=RequestMethod.GET)
//	public ModelAndView manageSeventhIntellectualPropertyDoc(HttpServletRequest request,ModelAndView modelAndView){
//		logger.info("manageSeventhIntellectualPropertyDoc");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(person.getUid());
//			modelAndView.addObject("applier",applier);
//			
//			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
//			List<SeventhIntellectualPropertyDoc>  seventhIntellectualPropertyDocs = seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(person.getUid());
//			modelAndView.addObject("seventhIPForms", seventhIntellectualPropertyDocs);
//			
//			modelAndView.setViewName("editform/manageSeventhIPDoc");
//			return modelAndView;
//		}
//		catch(NullPointerException e){
//			logger.info("session null pointer!");
//			modelAndView.setViewName("redirect:/login");
//			return modelAndView;
//		}
//	}
	
	/**
	 * 项目组浏览第七知识产权表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-seventh-ip-doc/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectSeventhIntellectualPropertyDoc(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("selectSeventhIntellectualPropertyDoc");
//		try{
//			Person person=FormUlti.getPersonInRequest(request);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier= applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier",applier);
//			
//			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
//			List<SeventhIntellectualPropertyDoc>  seventhIntellectualPropertyDocs = seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(applierUid);
//			modelAndView.addObject("seventhIPForms", seventhIntellectualPropertyDocs);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/selectSeventhIPDoc");
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
	 * 项目组建立第七知识产权表 POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-seventh-ip-doc",method=RequestMethod.POST)
	public String createSeventhIntellectualPropertyDoc(HttpServletRequest request,SeventhIntellectualPropertyDoc seventhIntellectualPropertyDoc){
		logger.info("createSeventhIntellectualPropertyDoc");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			List<SeventhIntellectualPropertyDoc>  seventhIntellectualPropertyDocs = seventhIntellectualPropertyDocJdbc.getSeventhIntellectualPropertyDocs(person.getUid());
			int rankOfOrg =seventhIntellectualPropertyDocs.size()+1;
			seventhIntellectualPropertyDocJdbc.createSeventhIntellectualPropertyDoc(person.getUid(), rankOfOrg);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组删除第七知识产权表LIST GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-seventh-ip-doc",method=RequestMethod.GET)
	public String deleteSeventhIntellectualPropertyDoc(HttpServletRequest request, int idOfSeventhIPForm){
		logger.info("deleteSeventhIntellectualPropertyDoc");
		try{
			Person person=FormUlti.getPersonInRequest(request);
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			seventhIntellectualPropertyDocJdbc.deleteSeventhIntellectualPropertyDoc(idOfSeventhIPForm);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
	/**
	 * 项目组编辑第七知识产权表POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-seventh-ip-doc/{idOfSeventhIPForm}",method=RequestMethod.POST)
	public String saveSeventhIntellectualPropertyDoc(HttpServletRequest request,@ModelAttribute("seventhIPFormAttr") SeventhIntellectualPropertyDoc seventhIPForm,
			@PathVariable("idOfSeventhIPForm") int idOfSeventhIPForm){
		logger.info("saveSeventhIntellectualPropertyDoc");
		try{
			SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
			seventhIntellectualPropertyDocJdbc.updateSeventhIntellectualPropertyDoc(seventhIPForm);
			
			return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
}
