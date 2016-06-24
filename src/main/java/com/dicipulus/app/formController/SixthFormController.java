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
import com.dicipulus.app.JDBC.SixthApplyUnitSituationJdbc;
import com.dicipulus.app.JDBC.SixthEconomicAndSocialBenefitJdbc;
import com.dicipulus.app.JDBC.SixthPaperMonographNTJdbc;
import com.dicipulus.app.applicationModel.SixthApplyUnitSituation;
import com.dicipulus.app.applicationModel.SixthEconomicAndSocialBenefit;
import com.dicipulus.app.applicationModel.SixthPaperMonographNT;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

@Controller
@SessionAttributes("person")
public class SixthFormController {
	private static final Logger logger = LoggerFactory.getLogger(SixthFormController.class);
	
	/**
	 * 项目组编辑第六个表NT（List+其他） GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-paper-monograph",method=RequestMethod.GET)
	public ModelAndView manageSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSixthPaperMonograph");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			List<SixthPaperMonographNT> sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
			modelAndView.setViewName("editform/manageSixthPaperMonographNT");
			modelAndView.addObject("sixthPaperMonographForms", sixthPaperMonographNT);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 选择第六个表NT（List+其他） GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-paper-monograph/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("selectSixthPaperMonograph");
//		try{
//			Person person=(Person)request.getSession().getAttribute("person");
//			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
//			List<SixthPaperMonographNT> sixthPaperMonographNT=sixthPaperMonographNTJdbc.getAllSixthPaperMonographNT(applierUid);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/selectSixthPaperMonographNT");
//			modelAndView.addObject("sixthPaperMonographForms", sixthPaperMonographNT);
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
	 * 项目组建立第六个表NT POST(点击添加新论文专著)
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-paper-monograph",method=RequestMethod.POST)
	public String createSixthPaperMonographNT(HttpServletRequest request){
		logger.info("createSixthPaperMonograph");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.createSixthPaperMonographNT(applierUid);
			return "redirect:/manage-paper-monograph";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * 项目组删除第六个表(论文专著)NT GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-sixth-paper-monograph",method=RequestMethod.GET)
	public String deleteSixthPaperMonographNT(HttpServletRequest request,int idOfPaperMonograph){
		logger.info("deleteSixthPaperMonographNT");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.deleteSixthPaperMonographNT(idOfPaperMonograph);
			return "redirect:/manage-paper-monograph";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * 项目组编辑第六个表NT GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.GET)
	public ModelAndView editSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfPaperMonograph")int idOfPaperMonograph){
		logger.info("editSixthPaperMonographNT");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			SixthPaperMonographNT sixthPaperMonographNT=sixthPaperMonographNTJdbc.getSixthPaperMonographNT(idOfPaperMonograph);
			modelAndView.setViewName("editform/editSixthPaperMonographNT");
			modelAndView.addObject("sixthPaperMonograph", sixthPaperMonographNT);
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 浏览第六个表NT GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.GET)
//	public ModelAndView displaySixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfPaperMonograph")int idOfPaperMonograph){
//		logger.info("displaySixthPaperMonographNT");
//		try{
//			Person person=(Person)request.getSession().getAttribute("person");
//			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
//			SixthPaperMonographNT sixthPaperMonographNT=sixthPaperMonographNTJdbc.getSixthPaperMonographNT(idOfPaperMonograph);
//			
//			String applierUid=sixthPaperMonographNT.getApplierUid();
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);	
//			
//			modelAndView.setViewName("displayform/displaySixthPaperMonographNT");
//			modelAndView.addObject("sixthPaperMonograph", sixthPaperMonographNT);
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
	 * 项目组编辑第六个表NT POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.POST)
	public String saveSixthPaperMonographNT(HttpServletRequest request,@ModelAttribute("paperMonographFormAttr")SixthPaperMonographNT sixthPaperMonographNT,@PathVariable("idOfPaperMonograph") int idOfPaperMonograph){
		logger.info("saveSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
			sixthPaperMonographNTJdbc.updateSixthPaperMonographNT(sixthPaperMonographNT, idOfPaperMonograph);
			return "redirect:/manage-paper-monograph";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * 项目组编辑第六个表（List+其他） GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/manage-apply-unit-situation",method=RequestMethod.GET)
	public ModelAndView manageSixthAppliedUnitSituation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageApplyUnitSituation");
		try{
			Person person=(Person)request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			
			
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
			List<SixthApplyUnitSituation> sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getAllSixthApplyUnitSituation(applierUid);
			SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=sixthEconomicAndSocialBenefitJdbc.getSixthEconomicAndSocialBenefit(applierUid);
			modelAndView.addObject("sixthEconomicAndSocialBenefitForms", sixthEconomicAndSocialBenefit);
			modelAndView.addObject("sixthApplyUnitSituationForms", sixthApplyUnitSituation);
			modelAndView.addObject("person", person);
			modelAndView.setViewName("editform/manageSixthApplyUnitSituation");
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 浏览第六个表（List+其他） GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/select-apply-unit-situation/{applierUid}",method=RequestMethod.GET)
//	public ModelAndView selectSixthAppliedUnitSituation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
//		logger.info("selectApplyUnitSituation");
//		try{
//			Person person=(Person)request.getSession().getAttribute("person");
//			
//			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
//			SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
//			List<SixthApplyUnitSituation> sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getAllSixthApplyUnitSituation(applierUid);
//			SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=sixthEconomicAndSocialBenefitJdbc.getSixthEconomicAndSocialBenefit(applierUid);
//			modelAndView.addObject("sixthEconomicAndSocialBenefitForms", sixthEconomicAndSocialBenefit);
//			modelAndView.addObject("sixthApplyUnitSituationForms", sixthApplyUnitSituation);
//			modelAndView.addObject("person", person);
//			
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/selectSixthApplyUnitSituation");
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
	 * 项目组编辑第六个经济社会效益表POST
	 * 
	 */
	@RequestMapping(value="/edit-economic-social-benefit",method=RequestMethod.POST)
	public String editSixthEconomicAndSocialBenefit(@ModelAttribute("sixthFormAttr")SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit,HttpServletRequest request){
		logger.info("editSixthEconomicAndSocialBenefit");
		try{
		Person person=(Person)request.getSession().getAttribute("person");
		String applierUid=person.getUid();
		SixthEconomicAndSocialBenefitJdbc  sixthEconomicAndSocialBenefitJdbc=InitJdbc.initSixthEconomicAndSocialBenefitJdbc();
		sixthEconomicAndSocialBenefitJdbc.updateSixthEconomicAndSocialBenefitJdbc(sixthEconomicAndSocialBenefit, applierUid);
		return "redirect:/manage-seventh-ip-doc";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * 项目组建立第六个表(应用单位情况表) POST(点击添加新单位)
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/create-apply-unit-situation",method=RequestMethod.POST)
	public String createSixthApplyUnitApplication(HttpServletRequest request){
		logger.info("createApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.createSixthApplyUnitSituation(applierUid);
			return "redirect:/manage-apply-unit-situation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
		
	}
	/**
	 * 项目组删除第六个表(应用单位情况表) GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/delete-sixth-apply-unit-situation",method=RequestMethod.GET)
	public String deleteSixthApplyUnitSituation(HttpServletRequest request,int idOfApplyUnit){
		logger.info("deleteSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.deleteSixthAppliedUnitSituation(idOfApplyUnit);
			return "redirect:/manage-apply-unit-situation";
			
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	/**
	 * 项目组编辑第六个表(应用单位情况表) GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/edit-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.GET)
	public ModelAndView editSixthApplyUnitSituation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfApplyUnit")int idOfApplyUnit){
		logger.info("editSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			SixthApplyUnitSituation sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getSixthApplyUnitSituation(idOfApplyUnit);
			modelAndView.addObject("sixthApplyUnitSituationForm", sixthApplyUnitSituation);
			modelAndView.setViewName("editform/editSixthApplyUnitSituation");
			
			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
			Applier applier=applierJdbc.getApplierByUid(applierUid);
			modelAndView.addObject("applier", applier);
			
			return modelAndView;
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	/**
	 * 浏览第六个表(应用单位情况表) GET
	 * @param request
	 * @param modelAndView
	 * @return
	 */
//	@RequestMapping(value="/display-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.GET)
//	public ModelAndView displaySixthApplyUnitSituation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfApplyUnit")int idOfApplyUnit){
//		logger.info("displaySixthApplyUnitSituation");
//		try{
//			Person person=(Person) request.getSession().getAttribute("person");
//			
//			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
//			SixthApplyUnitSituation sixthApplyUnitSituation=sixthApplyUnitSituationJdbc.getSixthApplyUnitSituation(idOfApplyUnit);
//			modelAndView.addObject("sixthApplyUnitSituationForm", sixthApplyUnitSituation);		
//			
//			String applierUid=sixthApplyUnitSituation.getApplierUid();
//			ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
//			Applier applier=applierJdbc.getApplierByUid(applierUid);
//			modelAndView.addObject("applier", applier);
//			
//			FormUlti.isAuthenticatedToRead(person, applierUid);
//			
//			modelAndView.setViewName("displayform/displaySixthApplyUnitSituation");
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
	 * 项目组编辑第六个表(应用单位情况表) POST
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value="/save-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.POST)
	public String saveSixthApplyUnitSituation(HttpServletRequest request,@ModelAttribute("sixthFormAttr")SixthApplyUnitSituation sixthApplyUnitSituation,@PathVariable("idOfApplyUnit") int idOfApplyUnit){
		logger.info("saveSixthApplyUnitSituation");
		try{
			Person person=(Person) request.getSession().getAttribute("person");
			String applierUid=person.getUid();
			SixthApplyUnitSituationJdbc sixthApplyUnitSituationJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
			sixthApplyUnitSituationJdbc.updateSixthApplyUnitSituation(sixthApplyUnitSituation, idOfApplyUnit);
			//return "redirect:/edit-sixth-apply-unit-situation/"+sixthApplyUnitSituation.getIdOfApplyUnit();
			return "redirect:/manage-apply-unit-situation";
		}
		catch(NullPointerException e){
			logger.info("session null pointer!");
			return "redirect:/login";
		}
	}
	
}
