package com.dicipulus.app.formController;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.JDBC.SeventhIntellectualPropertyDocJdbc;
import com.dicipulus.app.JDBC.SeventhPaperCitedByOthersJdbc;
import com.dicipulus.app.applicationModel.Constants;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.applicationModel.SeventhIntellectualPropertyDoc;
import com.dicipulus.app.applicationModel.SeventhPaperCitedByOthers;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.validator.Validator;

@Controller
@SessionAttributes("person")
public class EditFormController {
	private static final Logger logger=LoggerFactory.getLogger(EditFormController.class);
	
	/**Edit the first form*/
	@RequestMapping(value="/edit-first-project-basic-situation",method=RequestMethod.GET)
	public ModelAndView editFirstProjectBasicSituationGet(ModelAndView modelAndView, HttpServletRequest request){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("firstForm", 
				InitJdbc.initFirstProjectBasicSituationJdbc().getFirstProjectBasicSituation(applierUid));
		modelAndView.setViewName("editform/editFirstProjectBasicSituation");
		return modelAndView;
	}
	
	/**Edit the second form by Referee*/
	@RequestMapping(value="/edit-referee-unit-opinion/{applierUid}",method=RequestMethod.GET)
	public ModelAndView editSecondRefereeUnitOpinionForm(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("applierUid") String applierUid){
		logger.info("editSecondRefereeUnitOpinionForm");
		Applier applier=InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
		if(false==Validator.validateSession(request, modelAndView)&&
				Validator.validateRole(request, modelAndView, "referee")&&
				Validator.validateUid(request, modelAndView, applier.getOwner())&&
				Validator.validateStatus(request, modelAndView, Arrays.asList("未提交","已提交"))){
			return modelAndView;
		}
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("secondForm", 
				InitJdbc.initSecondRefereeUnitOpinionJdbc().getSecondRefereeUnitOpinion(applierUid));
		modelAndView.addObject("nominatedAwards",Constants.NOMINATEDAWARDS);
		modelAndView.setViewName("editform/editSecondRefereeOpinion");
		return modelAndView;
	}

	/**Edit the third form */
	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.GET)
	public ModelAndView initThirdProjectBriefIntroduction(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("initThirdProjectBriefIntroduction");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("briefIntroductionForm", 
				InitJdbc.initThirdProjectBriefIntroductionJdbc().getThirdProjectBriefIntroduction(applierUid));
		modelAndView.setViewName("editform/editThirdBriefIntroduction");
		return modelAndView;
	}
	
	/**Edit the fourth form */
	@RequestMapping(value="/edit-fourth-form",method=RequestMethod.GET)
	public ModelAndView getFourthForm(HttpServletRequest request, ModelAndView modelAndView){
		logger.info("getFourthForm");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("fourthForm", InitJdbc.initFourthFormJdbc().getFourthForm(applierUid));
		modelAndView.setViewName("editform/editFourthForm");
		return modelAndView;
	}
	
	/**Edit the fifth form*/
	@RequestMapping(value="/edit-objective-evaluation",method=RequestMethod.GET)
	public ModelAndView editFifthObjectiveEvaluation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("editFifthObjectiveEvaluation");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("objectiveEvaluationForm", 
				InitJdbc.initFifthObjectiveEvaluationJdbc().getFifthObjectiveEvaluation(applierUid));
		modelAndView.setViewName("editform/editFifthObjectiveEvaluation");
		return modelAndView;
	}
	
	/**Edit Paper Monograph List, part of the sixth form list*/
	@RequestMapping(value="/manage-paper-monograph",method=RequestMethod.GET)
	public ModelAndView manageSixthPaperMonographNT(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSixthPaperMonograph");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthPaperMonographForms", 
				InitJdbc.initSixthPaperMonographNTJdbc().getAllSixthPaperMonographNT(applierUid));
		modelAndView.setViewName("editform/manageSixthPaperMonographNT");
		return modelAndView;
	}
	
	/**Edit Apply Unit Situation List, part of the sixth form list*/
	@RequestMapping(value="/manage-apply-unit-situation",method=RequestMethod.GET)
	public ModelAndView manageSixthAppliedUnitSituation(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageApplyUnitSituation");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthEconomicAndSocialBenefitForms", 
				InitJdbc.initSixthEconomicAndSocialBenefitJdbc().getSixthEconomicAndSocialBenefit(applierUid));
		modelAndView.addObject("sixthApplyUnitSituationForms", 
				InitJdbc.initSixthApplyUnitSituationJdbc().getAllSixthApplyUnitSituation(applierUid));
		modelAndView.setViewName("editform/manageSixthApplyUnitSituation");
		return modelAndView;
	}
	
	/**Edit Paper Monograph List, part of the sixth form*/
	@RequestMapping(value="/edit-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.GET)
	public ModelAndView editSixthPaperMonographNT(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfPaperMonograph")int idOfPaperMonograph){
		logger.info("editSixthPaperMonographNT");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthPaperMonograph", 
				InitJdbc.initSixthPaperMonographNTJdbc().getSixthPaperMonographNT(idOfPaperMonograph));
		modelAndView.setViewName("editform/editSixthPaperMonographNT");
		return modelAndView;
	}
	
	/**Edit Apply Unit Situation, part of the sixth form*/
	@RequestMapping(value="/edit-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.GET)
	public ModelAndView editSixthApplyUnitSituation(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfApplyUnit")int idOfApplyUnit){
		logger.info("editSixthApplyUnitSituation");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("sixthApplyUnitSituationForm", 
				InitJdbc.initSixthApplyUnitSituationJdbc().getSixthApplyUnitSituation(idOfApplyUnit));
		modelAndView.setViewName("editform/editSixthApplyUnitSituation");
		return modelAndView;
	}
	
	/**Edit Paper Cited By Others, part of the seventh form*/
	@RequestMapping(value="/manage-seventh-paper-cited-by-others",method=RequestMethod.GET)
	public ModelAndView manageSeventhPaperCitedByOthers(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSeventhPaperCitedByOthers");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("seventhPaperForms", 
				InitJdbc.initSeventhPaperCitedByOthersJdbc().getSeventhPaperCitedByOtherss(applierUid));
		modelAndView.setViewName("editform/manageSeventhPaperCitedByOthers");
		return modelAndView;
	}
	
	/**Edit Intellectual Property, part of the seventh form*/
	@RequestMapping(value="/manage-seventh-ip-doc",method=RequestMethod.GET)
	public ModelAndView manageSeventhIntellectualPropertyDoc(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageSeventhIntellectualPropertyDoc");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("seventhIPForms", 
				InitJdbc.initSeventhIntellectualPropertyDocJdbc().getSeventhIntellectualPropertyDocs(applierUid));
		modelAndView.setViewName("editform/manageSeventhIPDoc");
		return modelAndView;
	}
	
	/**Edit eighth form list*/
	@RequestMapping(value="/manage-eighth-major-contributor",method=RequestMethod.GET)
	public ModelAndView manageEighthMajorContributor(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageEighthMajorContributor");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("eighthForms", 
				InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid));
		modelAndView.setViewName("editform/manageEighthMajorContributor");
		return modelAndView;
	}
	
	/**Edit eighth form*/
	@RequestMapping(value="/edit-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.GET)
	public ModelAndView editEighthMajorContributor(HttpServletRequest request,
			ModelAndView modelAndView,@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("editEighthMajorContributor");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("eighthForm",
				InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm));
		modelAndView.setViewName("editform/editEighthMajorContributor");
		return modelAndView;
	}
	
	/**Edit ninth form list*/
	@RequestMapping(value="/manage-nineth-major-org-contributor",method=RequestMethod.GET)
	public ModelAndView manageNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView){
		logger.info("manageNinethMajorOrgContributor");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("ninethForms", 
				InitJdbc.initNinethMajorOrgContributorJdbc().getNinethMajorOrgContributors(applierUid));
		modelAndView.setViewName("editform/manageNinethOrgContributor");
		return modelAndView;
	}
	
	/**Edit ninth form*/
	@RequestMapping(value="/edit-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.GET)
	public ModelAndView editNinethMajorOrgContributor(HttpServletRequest request,ModelAndView modelAndView,@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("editNinethMajorOrgContributor");
		if(notValid(modelAndView, request)){
			return modelAndView;
		}
		String applierUid=getApplierUid(request);
		addApplier(modelAndView, applierUid);
		modelAndView.addObject("ninethForm",InitJdbc.initNinethMajorOrgContributorJdbc().getNinethMajorOrgContributor(idOfNinethForm));
		modelAndView.setViewName("editform/editNinethOrgContributor");
		return modelAndView;
	}
	
	/*
	 * Inner methods
	 */
	private String getApplierUid(HttpServletRequest request) {
		return FormUlti.getPersonInRequest(request).getUid();
	}
	private void addApplier(ModelAndView modelAndView, String applierUid) {
		modelAndView.addObject("applier", InitJdbc.initApplierJdbc().getApplierByUid(applierUid));
	}
	private boolean notValid(ModelAndView modelAndView,
			HttpServletRequest request) {
		return !(Validator.validateSession(request, modelAndView)&&
				Validator.validateRole(request, modelAndView, "applier")&&
				Validator.validateStatus(request, modelAndView, Arrays.asList("未提交")));
	}
	
}
