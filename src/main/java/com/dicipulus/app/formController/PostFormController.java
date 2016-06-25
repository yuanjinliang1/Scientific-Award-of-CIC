package com.dicipulus.app.formController;

import java.util.Arrays;
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

import com.dicipulus.app.JDBC.ApplicationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.JDBC.SeventhIntellectualPropertyDocJdbc;
import com.dicipulus.app.JDBC.SeventhPaperCitedByOthersJdbc;
import com.dicipulus.app.JDBC.SixthApplyUnitSituationJdbc;
import com.dicipulus.app.JDBC.SixthPaperMonographNTJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.FourthForm;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.applicationModel.SeventhIntellectualPropertyDoc;
import com.dicipulus.app.applicationModel.SeventhPaperCitedByOthers;
import com.dicipulus.app.applicationModel.SixthApplyUnitSituation;
import com.dicipulus.app.applicationModel.SixthEconomicAndSocialBenefit;
import com.dicipulus.app.applicationModel.SixthPaperMonographNT;
import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.validator.Validator;

@Controller
@SessionAttributes("person")
public class PostFormController {
	private static final Logger logger=LoggerFactory.getLogger(PostFormController.class);
	
	/**Post the first form*/
	@RequestMapping(value="/save-first-project-basic-situation",method=RequestMethod.POST)
	public String saveFirstProjectBasicSituation(HttpServletRequest request,
			@ModelAttribute("firstFormAttr") FirstProjectBasicSituation firstForm){
		logger.info("saveFirstProjectBasicSituation()");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		
		InitJdbc.initFirstProjectBasicSituationJdbc().setFirstProjectBasicSituation(firstForm, applierUid);
		return "redirect:/display-second-form-when-applier-editing";
	}

	
	/**Post the second form*/
	@RequestMapping(value="/edit-referee-unit-opinion-post/{applierUid}",method=RequestMethod.POST)
	public String editSecondRefereeUnitOpinion(HttpServletRequest request,
			@ModelAttribute("secondFormAttri")SecondRefereeUnitOpinion secondRefereeUnitOpinion,
			@PathVariable("applierUid") String applierUid){
		logger.info("editSecondRefereeUnitOpinionPost()");
		String view="";
		if(false==(Validator.validateSession(request, view)&&
				Validator.validateRole(request, view, "referee")&&
				Validator.validateRead(request, view, applierUid)&&
				Validator.validateStatus(request, view, Arrays.asList("未提交","已提交")))){
			return view;
		}
		InitJdbc.initSecondRefereeUnitOpinionJdbc().updateSecondRefereeUnitOpinion(secondRefereeUnitOpinion, applierUid);
		InitJdbc.initFirstProjectBasicSituationJdbc().setRefereeInformation(secondRefereeUnitOpinion, applierUid);
		//return "redirect:/edit-referee-unit-opinion/"+applier.getUid();
		return "redirect:/application-managed-by-referee";
	}
	
	/**Post the third form*/
	@RequestMapping(value="/edit-brief-introduction",method=RequestMethod.POST)
	public String editThirdProjectBriefIntroduction(
			@ModelAttribute("briefIntroduction")ThirdProjectBriefIntroduction thirdProjectBriefIntroduction,
			HttpServletRequest request){
		logger.info("editThirdProjectBriefIntroduction");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initThirdProjectBriefIntroductionJdbc().updateThirdProjectBriefIntroduction(thirdProjectBriefIntroduction, applierUid);
		return "redirect:/edit-fourth-form";
	}
	
	/**Post the fourth form*/
	@RequestMapping(value="/edit-fourth-form",method=RequestMethod.POST)
	public String editFourthForm(@ModelAttribute("fourthFormAttr")FourthForm fourthForm,
			HttpServletRequest request){
		logger.info("editFourthForm");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initFourthFormJdbc().setFourthForm(fourthForm, applierUid);
		return "redirect:/edit-objective-evaluation";
	}
	
	/**Post the fifth form*/
	@RequestMapping(value="/edit-objective-evaluation",method=RequestMethod.POST)
	public String editFifthObjectiveEvaluation(
			@ModelAttribute("objectiveEvaluation")FifthObjectiveEvaluation fifthObjectiveEvaluation,
			HttpServletRequest request){
		logger.info("editFifthObjectiveEvaluation");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initFifthObjectiveEvaluationJdbc().updateFifthObjective(fifthObjectiveEvaluation, applierUid);
		return "redirect:/manage-apply-unit-situation";
	}
	/**Create Sixth Paper Monograph*/
	@RequestMapping(value="/create-paper-monograph",method=RequestMethod.POST)
	public String createSixthPaperMonographNT(HttpServletRequest request){
		logger.info("createSixthPaperMonograph");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initSixthPaperMonographNTJdbc().createSixthPaperMonographNT(applierUid);
		return "redirect:/manage-paper-monograph";
	}
	/**Create Sixth Apply Unit Situation*/
	@RequestMapping(value="/create-apply-unit-situation",method=RequestMethod.POST)
	public String createSixthApplyUnitApplication(HttpServletRequest request){
		logger.info("createApplyUnitSituation");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initSixthApplyUnitSituationJdbc().createSixthApplyUnitSituation(applierUid);
		return "redirect:/manage-apply-unit-situation";
	}
	/**Delete Sixth Paper Monograph*/
	@RequestMapping(value="/delete-sixth-paper-monograph",method=RequestMethod.GET)
	public String deleteSixthPaperMonographNT(HttpServletRequest request,int idOfPaperMonograph){
		logger.info("deleteSixthPaperMonographNT");
		String view="";
		SixthPaperMonographNTJdbc sixthJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
		String applierUid=sixthJdbc.getSixthPaperMonographNT(idOfPaperMonograph).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSixthPaperMonographNTJdbc().deleteSixthPaperMonographNT(idOfPaperMonograph);
		return "redirect:/manage-paper-monograph";
	}
	/**Delete Sixth Apply unit Situation*/
	@RequestMapping(value="/delete-sixth-apply-unit-situation",method=RequestMethod.GET)
	public String deleteSixthApplyUnitSituation(HttpServletRequest request,int idOfApplyUnit){
		logger.info("deleteSixthApplyUnitSituation");
		String view="";
		SixthApplyUnitSituationJdbc sixthJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
		String applierUid=sixthJdbc.getSixthApplyUnitSituation(idOfApplyUnit).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSixthApplyUnitSituationJdbc().deleteSixthAppliedUnitSituation(idOfApplyUnit);
		return "redirect:/manage-apply-unit-situation";
	}
	/**Save Sixth Paper Monograph*/
	@RequestMapping(value="/save-sixth-paper-monograph/{idOfPaperMonograph}",method=RequestMethod.POST)
	public String saveSixthPaperMonographNT(HttpServletRequest request,
			@ModelAttribute("paperMonographFormAttr")SixthPaperMonographNT sixthPaperMonographNT,
			@PathVariable("idOfPaperMonograph") int idOfPaperMonograph){
		logger.info("saveSixthApplyUnitSituation");
		String view="";
		SixthPaperMonographNTJdbc sixthJdbc=InitJdbc.initSixthPaperMonographNTJdbc();
		String applierUid=sixthJdbc.getSixthPaperMonographNT(idOfPaperMonograph).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSixthPaperMonographNTJdbc().updateSixthPaperMonographNT(sixthPaperMonographNT, idOfPaperMonograph);
		return "redirect:/manage-paper-monograph";
	}
	/**Save Sixth Economic Social Benefit*/
	@RequestMapping(value="/edit-economic-social-benefit",method=RequestMethod.POST)
	public String saveSixthEconomicAndSocialBenefit(
			@ModelAttribute("sixthFormAttr")SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit,
			HttpServletRequest request){
		logger.info("saveSixthEconomicAndSocialBenefit");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		InitJdbc.initSixthEconomicAndSocialBenefitJdbc().updateSixthEconomicAndSocialBenefitJdbc(sixthEconomicAndSocialBenefit, applierUid);
		return "redirect:/manage-seventh-ip-doc";
	}
	/**Save Sixth Apply Unit Situation*/
	@RequestMapping(value="/save-sixth-apply-unit-situation/{idOfApplyUnit}",method=RequestMethod.POST)
	public String saveSixthApplyUnitSituation(HttpServletRequest request,
			@ModelAttribute("sixthFormAttr")SixthApplyUnitSituation sixthApplyUnitSituation,
			@PathVariable("idOfApplyUnit") int idOfApplyUnit){
		logger.info("saveSixthApplyUnitSituation");
		String view="";
		SixthApplyUnitSituationJdbc sixthJdbc=InitJdbc.initSixthApplyUnitSituationJdbc();
		String applierUid=sixthJdbc.getSixthApplyUnitSituation(idOfApplyUnit).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSixthApplyUnitSituationJdbc().updateSixthApplyUnitSituation(sixthApplyUnitSituation, idOfApplyUnit);
		return "redirect:/manage-apply-unit-situation";
	}
	/**Create Seventh Paper Cited By Others*/
	@RequestMapping(value="/create-seventh-paper-cited-by-others",method=RequestMethod.POST)
	public String createSeventhPaperCitedByOthers(HttpServletRequest request,SeventhPaperCitedByOthers seventhPaperCitedByOthers){
		logger.info("createSeventhPaperCitedByOthers");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		int rankOfPaper =InitJdbc.initSeventhPaperCitedByOthersJdbc().getSeventhPaperCitedByOtherss(applierUid).size()+1;
		InitJdbc.initSeventhPaperCitedByOthersJdbc().createSeventhPaperCitedByOthers(applierUid, rankOfPaper);
		return "redirect:/manage-seventh-paper-cited-by-others";
	}
	/**Create Seventh Intellectual Property*/
	@RequestMapping(value="/create-seventh-ip-doc",method=RequestMethod.POST)
	public String createSeventhIntellectualPropertyDoc(HttpServletRequest request,SeventhIntellectualPropertyDoc seventhIntellectualPropertyDoc){
		logger.info("createSeventhIntellectualPropertyDoc");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		int rankOfIP =InitJdbc.initSeventhIntellectualPropertyDocJdbc().getSeventhIntellectualPropertyDocs(applierUid).size()+1;
		InitJdbc.initSeventhIntellectualPropertyDocJdbc().createSeventhIntellectualPropertyDoc(applierUid, rankOfIP);
		return "redirect:/manage-seventh-ip-doc";
	}
	/**Delete Seventh Paper Cited By Others*/
	@RequestMapping(value="/delete-seventh-paper-cited-by-others",method=RequestMethod.GET)
	public String deleteSeventhPaperCitedByOthers(HttpServletRequest request, int idOfSeventhPaperForm){
		logger.info("deleteSeventhPaperCitedByOthers");
		String view="";
		SeventhPaperCitedByOthersJdbc seventhJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
		String applierUid=seventhJdbc.getSeventhPaperCitedByOthers(idOfSeventhPaperForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSeventhPaperCitedByOthersJdbc().deleteSeventhPaperCitedByOthers(idOfSeventhPaperForm);
		return "redirect:/manage-seventh-paper-cited-by-others";
	}
	/**Delete Seventh Intellectual Property*/
	@RequestMapping(value="/delete-seventh-ip-doc",method=RequestMethod.GET)
	public String deleteSeventhIntellectualPropertyDoc(HttpServletRequest request, int idOfSeventhIPForm){
		logger.info("deleteSeventhIntellectualPropertyDoc");
		String view="";
		SeventhIntellectualPropertyDocJdbc seventhJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
		String applierUid=seventhJdbc.getSeventhIntellectualPropertyDoc(idOfSeventhIPForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		seventhJdbc.deleteSeventhIntellectualPropertyDoc(idOfSeventhIPForm);
		return "redirect:/manage-seventh-ip-doc";
	}
	/**Save Seventh Paper Cited By Others*/
	@RequestMapping(value="/save-seventh-paper-cited-by-others/{idOfSeventhPaperForm}",method=RequestMethod.POST)
	public String saveSeventhPaperCitedByOthers(HttpServletRequest request,
			@ModelAttribute("seventhPaperFormAttr") SeventhPaperCitedByOthers seventhPaperForm,
			@PathVariable("idOfSeventhPaperForm") int idOfSeventhPaperForm){
		logger.info("saveSeventhPaperCitedByOthers");
		String view="";
		SeventhPaperCitedByOthersJdbc seventhJdbc=InitJdbc.initSeventhPaperCitedByOthersJdbc();
		String applierUid=seventhJdbc.getSeventhPaperCitedByOthers(idOfSeventhPaperForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		seventhJdbc.updateSeventhPaperCitedByOthers(seventhPaperForm);
		return "redirect:/manage-seventh-paper-cited-by-others";
	}
	/**Save Seventh Intellectual Property*/
	@RequestMapping(value="/save-seventh-ip-doc/{idOfSeventhIPForm}",method=RequestMethod.POST)
	public String saveSeventhIntellectualPropertyDoc(HttpServletRequest request,
			@ModelAttribute("seventhIPFormAttr") SeventhIntellectualPropertyDoc seventhIPForm,
			@PathVariable("idOfSeventhIPForm") int idOfSeventhIPForm){
		logger.info("saveSeventhIntellectualPropertyDoc");
		String view="";
		SeventhIntellectualPropertyDocJdbc seventhJdbc=InitJdbc.initSeventhIntellectualPropertyDocJdbc();
		String applierUid=seventhJdbc.getSeventhIntellectualPropertyDoc(idOfSeventhIPForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initSeventhIntellectualPropertyDocJdbc().updateSeventhIntellectualPropertyDoc(seventhIPForm);
		return "redirect:/manage-seventh-ip-doc";
	}
	/**Create the eighth form*/
	@RequestMapping(value="/create-eighth-major-contributor",method=RequestMethod.POST)
	public String createEighthMajorContributor(HttpServletRequest request){
		logger.info("createEighthMajorContributor");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		List<EighthMajorContributor>  eighthMajorContributors = InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributors(applierUid);
		int idOfEighthForm =InitJdbc.initEighthMajorContributorJdbc().createEighthMajorContributor(applierUid, eighthMajorContributors.size()+1);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
		return "redirect:edit-eighth-major-contributor/"+idOfEighthForm;
	}
	
	/**Delete the eighth form*/
	@RequestMapping(value="/delete-eighth-major-contributor",method=RequestMethod.GET)
	public String deleteEighthMajorContributor(HttpServletRequest request, int idOfEighthForm){
		logger.info("deleteEighthMajorContributor");
		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
		String view="";
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initEighthMajorContributorJdbc().deleteEighthMajorContributor(idOfEighthForm);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
		return "redirect:/manage-eighth-major-contributor";
	}
	/**Save the eighth form*/
	@RequestMapping(value="/save-eighth-major-contributor/{idOfEighthForm}",method=RequestMethod.POST)
	public String saveEighthMajorContributor(HttpServletRequest request,
			@ModelAttribute("eighthFormAttr") EighthMajorContributor eighthForm,
			@PathVariable("idOfEighthForm") int idOfEighthForm){
		logger.info("saveEighthMajorContributor");
		String applierUid=InitJdbc.initEighthMajorContributorJdbc().getEighthMajorContributor(idOfEighthForm).getApplierUid();
		String view="";
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}		
		InitJdbc.initEighthMajorContributorJdbc().updateEighthMajorContributor(eighthForm);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributorNamesForFirstForm(applierUid);
		return "redirect:/manage-eighth-major-contributor";
	}
	/**Create the nineth form*/
	@RequestMapping(value="/create-nineth-major-org-contributor",method=RequestMethod.POST)
	public String createNinethMajorOrgContributor(HttpServletRequest request,NinethMajorOrgContributor ninethMajorOrgContributor){
		logger.info("createNinethMajorOrgContributor");
		String view="";
		String applierUid=FormUlti.getPersonUidInRequest(request);
		if(notValid(request, view)){
			return view;
		}
		List<NinethMajorOrgContributor>  ninethMajorOrgContributors=InitJdbc.initNinethMajorOrgContributorJdbc().getNinethMajorOrgContributors(applierUid);
		int rankOfOrg =ninethMajorOrgContributors.size()+1;
		int idOfNinethForm =InitJdbc.initNinethMajorOrgContributorJdbc().createNinethMajorOrgContributor(applierUid, rankOfOrg);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(applierUid);
		return "redirect:/edit-nineth-major-org-contributor/"+idOfNinethForm;
	}
	
	/**Delete the nineth form*/
	@RequestMapping(value="/delete-nineth-major-org-contributor",method=RequestMethod.GET)
	public String deleteNinethMajorOrgContributor(HttpServletRequest request, int idOfNinethForm){
		logger.info("deleteNinethMajorOrgContributor");
		String view="";
		NinethMajorOrgContributorJdbc ninethJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		String applierUid=ninethJdbc.getNinethMajorOrgContributor(idOfNinethForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initNinethMajorOrgContributorJdbc().deleteNinethMajorOrgContributor(idOfNinethForm);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(applierUid);
		return "redirect:/manage-nineth-major-org-contributor";
	}
	/**Save the nineth form*/
	@RequestMapping(value="/save-nineth-major-org-contributor/{idOfNinethForm}",method=RequestMethod.POST)
	public String saveNinethMajorOrgContributor(HttpServletRequest request,@ModelAttribute("ninethFormAttr") NinethMajorOrgContributor ninethForm,
			@PathVariable("idOfNinethForm") int idOfNinethForm){
		logger.info("saveNinethMajorOrgContributor");
		String view="";
		NinethMajorOrgContributorJdbc ninethJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		String applierUid=ninethJdbc.getNinethMajorOrgContributor(idOfNinethForm).getApplierUid();
		if(notValidPlusUid(request, applierUid, view)){
			return view;
		}
		InitJdbc.initNinethMajorOrgContributorJdbc().updateNinethMajorOrgContributor(ninethForm);
		InitJdbc.initFirstProjectBasicSituationJdbc().setMajorContributingOrgNamesForFirstForm(applierUid);
		return "redirect:/manage-nineth-major-org-contributor";
	}

	
	
	/*
	 * Inner methods
	 */
	private boolean notValidPlusUid(HttpServletRequest request,
			String applierUid, String view) {
		return false==(Validator.validateSession(request, view)&&Validator.validateRole(request, view, "applier")&&
				Validator.validateUid(request, view, applierUid)&&
				Validator.validateStatus(request, view, Arrays.asList("未提交")));
	}
	
	private boolean notValid(HttpServletRequest request, String view) {
		return false==(Validator.validateSession(request, view)&&
				Validator.validateRole(request, view, "applier"))&&
				Validator.validateStatus(request, view, Arrays.asList("未提交"));
	}	
}
