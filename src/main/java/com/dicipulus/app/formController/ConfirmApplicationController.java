package com.dicipulus.app.formController;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.applicationModel.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;

@Controller
@SessionAttributes("person")
public class ConfirmApplicationController{
	private static final Logger logger = LoggerFactory
			.getLogger(ConfirmApplicationController.class);
	
	private Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}
	
	private void setMajorContributorsForFirstForm(String applierUid){
		EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
		List<EighthMajorContributor> eighthMajorContributors=eighthMajorContributorJdbc.getEighthMajorContributors(applierUid);//根据添加顺序（主键大小）排序
		
		String majorContributorNames="";
		for(EighthMajorContributor eighthForm:eighthMajorContributors){
			if(majorContributorNames.equals("")){
				majorContributorNames=majorContributorNames+eighthForm.getNameOfContributor();
			}
			else{
				majorContributorNames=majorContributorNames+","+eighthForm.getNameOfContributor();
			}
		}
		
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		firstProjectBasicSituationJdbc.setMajorContributorNames(majorContributorNames, applierUid);
	}
	
	private void setMajorContributingOrgNamesForFirstForm(String applierUid){
		NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		List<NinethMajorOrgContributor> ninethMajorOrgContributors=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(applierUid);
		
		String majorContributingOrgNames="";
		for(NinethMajorOrgContributor ninethForm:ninethMajorOrgContributors){
			if(majorContributingOrgNames.equals("")){
				majorContributingOrgNames=majorContributingOrgNames+ninethForm.getNameOfOrg();
			}
			else{
				majorContributingOrgNames=majorContributingOrgNames+","+ninethForm.getNameOfOrg();
			}
		}
		
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		firstProjectBasicSituationJdbc.setMajorContributingOrgNames(majorContributingOrgNames, applierUid);
	}
	
	private void setRefereeInformationForFirstForm(String applierUid){
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
		SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
		
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		firstProjectBasicSituationJdbc.setRefereeInformation(secondRefereeUnitOpinion, applierUid);
	}
	
	@RequestMapping(value="/confirm-whole-application-by-applier",method=RequestMethod.GET)
	public String confirmWholeApplicationByApplier(HttpServletRequest request){
		logger.info("confirmWholeApplicationByApplier");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
			if(!applicationJdbc.getStatusOfApplication(person.getUid()).equals("未提交")){
				return "redirect:/error?message=status-prerequisite-not-fullfilled";
			}
			setMajorContributorsForFirstForm(person.getUid());
			setMajorContributingOrgNamesForFirstForm(person.getUid());
			applicationJdbc.setStatusOfApplication("已提交", person.getUid());
			return "redirect:/display-first-project-basic-situation/"+person.getUid();
		}
		catch(NullPointerException e){
			logger.info(e.toString());
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/confirm-referee-unit-opinion-by-referee", method=RequestMethod.GET)
	public String confirmRefereeUnitOpinionByReferee(HttpServletRequest request, String applierUid){
		logger.info("confirmRefereeUnitOpinionByReferee");
		try{
			Person person=FormControllerUlti.getPersonInRequest(request);
			ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
			if(!applicationJdbc.getStatusOfApplication(person.getUid()).equals("已提交")){
				return "redirect:/error?message=status-prerequisite-not-fullfilled";
			}
			setRefereeInformationForFirstForm(applierUid);
			applicationJdbc.setStatusOfApplication("已推荐", person.getUid());
			return "redirect:/display-referee-unit-opinion/"+applierUid;
		}
		catch(NullPointerException e){
			logger.info(e.toString());
			return "redirect:/login";
		}
	}
}