package com.dicipulus.app.formController;

import java.util.Arrays;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.FifthObjectiveEvaluationJdbc;
import com.dicipulus.app.JDBC.FirstProjectBasicSituationJdbc;
import com.dicipulus.app.JDBC.FourthFormJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinthMajorOrgContributorJdbc;
import com.dicipulus.app.JDBC.SecondRefereeUnitOpinionJdbc;
import com.dicipulus.app.JDBC.SeventhIntellectualPropertyDocJdbc;
import com.dicipulus.app.JDBC.SeventhPaperCitedByOthersJdbc;
import com.dicipulus.app.JDBC.SixthApplyUnitSituationJdbc;
import com.dicipulus.app.JDBC.SixthEconomicAndSocialBenefitJdbc;
import com.dicipulus.app.JDBC.SixthPaperMonographNTJdbc;
import com.dicipulus.app.JDBC.ThirdProjectBriefIntroductionJdbc;
import com.dicipulus.app.applicationModel.Constants;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.applicationModel.NinthMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.applicationModel.SeventhIntellectualPropertyDoc;
import com.dicipulus.app.applicationModel.SeventhPaperCitedByOthers;
import com.dicipulus.app.applicationModel.SixthApplyUnitSituation;
import com.dicipulus.app.applicationModel.SixthEconomicAndSocialBenefit;
import com.dicipulus.app.applicationModel.SixthPaperMonographNT;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.validator.Validator;

@Controller
@SessionAttributes("person")
public class ViewFromController {
	private static final Logger logger=LoggerFactory.getLogger(ViewFromController.class);
	
	/**View the first form*/
	@RequestMapping(value="/display-first-project-basic-situation/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displayFirstProjectBasicSituationGet(ModelAndView modelAndView, 
			HttpServletRequest request,@PathVariable("applierUid") String applierUid){
		logger.info("displayFirstProjectBasicSituationGet");
		
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("firstForm", InitJdbc.initFirstProjectBasicSituationJdbc().getFirstProjectBasicSituation(applierUid));
		modelAndView.setViewName("displayform/displayFirstProjectBasicSituation");
		return modelAndView;
	}
	
	/**View the second form*/
	@RequestMapping(value="/display-referee-unit-opinion/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displaySecondRefereeUnitOpinionForm(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("displaySecondRefereeUnitOpinionForm");
		
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("secondForm", InitJdbc.initSecondRefereeUnitOpinionJdbc().getSecondRefereeUnitOpinion(applierUid));
		modelAndView.setViewName("displayform/displaySecondRefereeOpinion");
		return modelAndView;
	}
	
	/**View the second form while editing the whole form*/
	@RequestMapping(value="/display-second-form-when-applier-editing",method=RequestMethod.GET)
	public ModelAndView displaySecondFormWhenApplierEditing(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("displaySecondFormWhenApplierEditing");
		String applierUid=FormUlti.getPersonInRequest(request).getUid();
		if(false==Validator.validateSession(request, modelAndView)&&
				Validator.validateRole(request, modelAndView, "applier")&&
				Validator.validateUid(request, modelAndView, applierUid)&&
				Validator.validateStatus(request, modelAndView, Arrays.asList("δ�ύ"))){
			return modelAndView;
		}
		
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("secondForm", InitJdbc.initSecondRefereeUnitOpinionJdbc().getSecondRefereeUnitOpinion(applierUid));
		modelAndView.setViewName("editform/displaySecondRefereeOpinionWhenApplierEditing");
		return modelAndView;		
	}
	
	/**View the third form*/
	@RequestMapping(value="/display-brief-introduction/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displayuThirdProjectBriefIntroduction(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("displayThirdProjectBriefIntroduction");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("briefIntroductionForm",
				InitJdbc.initThirdProjectBriefIntroductionJdbc().getThirdProjectBriefIntroduction(applierUid));
		modelAndView.setViewName("displayform/displayThirdBriefIntroduction");
		return modelAndView;
	}
	
	/**View the fourth form*/
	@RequestMapping(value="/display-fourth-form/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displayFourthForm(HttpServletRequest request, ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("displayFourthForm");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("fourthForm", InitJdbc.initFourthFormJdbc().getFourthForm(applierUid));
		modelAndView.setViewName("displayform/displayFourthForm");
		return modelAndView;
	}
	
	/**View the fifth form*/
	@RequestMapping(value="/display-objective-evaluation/{applierUid}",method=RequestMethod.GET)
	public ModelAndView displayFifthObjectiveEvaluation(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("displayFIfthObjectiveEvaluation");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("objectiveEvaluationForm", 
				InitJdbc.initFifthObjectiveEvaluationJdbc().getFifthObjectiveEvaluation(applierUid));
		modelAndView.setViewName("displayform/displayFifthObjectiveEvaluation");
		return modelAndView;
	}
	
	/**View Paper Monograph List, part of the sixth form list*/
	@RequestMapping(value="/select-paper-monograph/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectSixthPaperMonographNT(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("selectSixthPaperMonograph");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthPaperMonographForms", 
				InitJdbc.initSixthPaperMonographNTJdbc().getAllSixthPaperMonographNT(applierUid));
		modelAndView.setViewName("displayform/selectSixthPaperMonographNT");
		return modelAndView;
	}
	
	/**View Apply Unit Situation List, part of the sixth form list*/
	@RequestMapping(value="/select-apply-unit-situation/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectSixthAppliedUnitSituation(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("selectSixthAppliedUnitSituation");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthEconomicAndSocialBenefitForms", 
				InitJdbc.initSixthEconomicAndSocialBenefitJdbc().getSixthEconomicAndSocialBenefit(applierUid));
		modelAndView.addObject("sixthApplyUnitSituationForms", 
				InitJdbc.initSixthApplyUnitSituationJdbc().getAllSixthApplyUnitSituation(applierUid));
		modelAndView.setViewName("displayform/selectSixthApplyUnitSituation");
		return modelAndView;
	}
	
	/**View Paper Monograph, part of the sixth form*/
	@RequestMapping(value="/display-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.GET)
	public ModelAndView displaySixthPaperMonographNT(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfPaperMonograph")int idOfPaperMonograph){
		logger.info("displaySixthPaperMonographNT");
		SixthPaperMonographNT sixthPaperMonographNT=
				InitJdbc.initSixthPaperMonographNTJdbc().getSixthPaperMonographNT(idOfPaperMonograph);
		String applierUid=sixthPaperMonographNT.getApplierUid();
		
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthPaperMonograph", sixthPaperMonographNT);
		modelAndView.setViewName("displayform/displaySixthPaperMonographNT");
		return modelAndView;
	}
	
	/**View Apply Unit Situation, part of the sixth form*/
	@RequestMapping(value="/display-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.GET)
	public ModelAndView displaySixthApplyUnitSituation(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfApplyUnit")int idOfApplyUnit){
		logger.info("displaySixthApplyUnitSituation");
		SixthApplyUnitSituation sixthApplyUnitSituation=
				InitJdbc.initSixthApplyUnitSituationJdbc().getSixthApplyUnitSituation(idOfApplyUnit);
		String applierUid=sixthApplyUnitSituation.getApplierUid();
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthApplyUnitSituationForm", sixthApplyUnitSituation);
		modelAndView.setViewName("displayform/displaySixthApplyUnitSituation");
		return modelAndView;
	}
	//TODO �����ж�����ֹ�鿴��Ȼ��ѧ����Ŀ��֪ʶ��Ȩ֤�����������
	/**View Paper Cited By Others List, part of the seventh form list*/
	@RequestMapping(value="/select-seventh-paper-cited-by-others/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectSeventhPaperCitedByOthers(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("selectSeventhPaperCitedByOthers");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("seventhPaperForms", 
				InitJdbc.initSeventhPaperCitedByOthersJdbc().getSeventhPaperCitedByOtherss(applierUid));
		modelAndView.setViewName("displayform/selectSeventhPaperCitedByOthers");
		return modelAndView;
	}
	/**View Intellectual Property List, part of the seventh form list*/
	@RequestMapping(value="/select-seventh-ip-doc/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectSeventhIntellectualPropertyDoc(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("selectSeventhIntellectualPropertyDoc");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("seventhIPForms", 
				InitJdbc.initSeventhIntellectualPropertyDocJdbc().getSeventhIntellectualPropertyDocs(applierUid));
		modelAndView.setViewName("displayform/selectSeventhIPDoc");
		return modelAndView;
	}
	
	/**View the eighth form list*/
	@RequestMapping(value="/select-eighth-major-contributor/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectEighthMajorContributor(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("eighthForms", 
				InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid));
		modelAndView.setViewName("displayform/selectEighthMajorContributor");
		return modelAndView;
	}
	
	/**View the eighth form*/
	@RequestMapping(value="/display-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
	public ModelAndView displayEighthMajorContributor(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("eighthForm",
				InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm));
		modelAndView.setViewName("displayform/displayEighthMajorContributor");
		return modelAndView;
	}
	
	/**View the ninth form list*/
	@RequestMapping(value="/select-ninth-major-org-contributor/{applierUid}",method=RequestMethod.GET)
	public ModelAndView selectNinthMajorOrgContributor(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("manageNinthMajorOrgContributor");
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("ninthForms", 
				InitJdbc.initNinthMajorOrgContributorJdbc().getNinthMajorOrgContributors(applierUid));
		modelAndView.setViewName("displayform/selectNinthOrgContributor");
		return modelAndView;
	}
	
	/**View the ninth form*/
	@RequestMapping(value="/display-ninth-major-org-contributor/{idOfNinthForm}",method=RequestMethod.GET)
	public ModelAndView displayNinthMajorOrgContributor(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfNinthForm") int idOfNinthForm){
		logger.info("editNinthMajorOrgContributor");
		NinthMajorOrgContributor ninthMajorOrgContributor=
				InitJdbc.initNinthMajorOrgContributorJdbc().getNinthMajorOrgContributor(idOfNinthForm);
		String applierUid=ninthMajorOrgContributor.getApplierUid();
		if(notValid(modelAndView, request, applierUid)){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("ninthForm", ninthMajorOrgContributor);
		modelAndView.setViewName("displayform/displayNinthOrgContributor");
		return modelAndView;
	}
	
	/*
	 * Inner methods
	 */
	private boolean notValid(ModelAndView modelAndView,
			HttpServletRequest request, String applierUid) {
		return !(Validator.validateSession(request, modelAndView)
				&&Validator.validateRead(request, modelAndView, applierUid));
	}

	private void addApplier(ModelAndView modelAndView, String applierUid) {
		modelAndView.addObject("applier", InitJdbc.initApplierJdbc().getApplierByUid(applierUid));
	}
}