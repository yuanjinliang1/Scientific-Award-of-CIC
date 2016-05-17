package com.dicipulus.app.formController;

import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

import com.dicipulus.app.JDBC.ApplierJdbc;
import com.dicipulus.app.JDBC.EighthMajorContributorJdbc;
import com.dicipulus.app.JDBC.FirstProjectBasicSituationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.JDBC.NinethMajorOrgContributorJdbc;
import com.dicipulus.app.JDBC.SecondRefereeUnitOpinionJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

public final class FormControllerUlti {
	public static Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}
	
	public static boolean isAuthenticatedToRead(Person self,Applier applier ) throws AuthenticationException {
		if(self.getRole().contains("admin")){
			return true;
		}
		else if(self.getUid().equals(applier.getOwner())){
			return true;
		}
		else if(self.getUid().equals(applier.getUid())){
			return true;
		}
		else {
			throw new AuthenticationException("no authentication to read");
		}
	}
	
	public static boolean isAuthenticatedToRead(Person self,String applierUid ) throws AuthenticationException {
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		Applier applier= applierJdbc.getApplierByUid(applierUid);
		
		if(self.getRole().contains("admin")){
			return true;
		}
		else if(self.getUid().equals(applier.getOwner())){
			return true;
		}
		else if(self.getUid().equals(applier.getUid())){
			return true;
		}
		else {
			throw new AuthenticationException("no authentication to read");
		}
	}
	public static void setMajorContributorsForFirstForm(String applierUid){
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
	
	public static void setMajorContributingOrgNamesForFirstForm(String applierUid){
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
	
	public static void setRefereeInformationForFirstForm(String applierUid){
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=InitJdbc.initSecondRefereeUnitOpinionJdbc();
		SecondRefereeUnitOpinion secondRefereeUnitOpinion=secondRefereeUnitOpinionJdbc.getSecondRefereeUnitOpinion(applierUid);
		
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc=InitJdbc.initFirstProjectBasicSituationJdbc();
		firstProjectBasicSituationJdbc.setRefereeInformation(secondRefereeUnitOpinion, applierUid);
	}
}
