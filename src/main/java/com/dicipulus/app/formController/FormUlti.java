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
import com.dicipulus.app.JDBC.NinthMajorOrgContributorJdbc;
import com.dicipulus.app.JDBC.SecondRefereeUnitOpinionJdbc;
import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.NinthMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;
import com.dicipulus.app.validator.Validator;

public final class FormUlti {
	public static Person getPersonInRequest(HttpServletRequest request) {
		return (Person)request.getSession().getAttribute("person");
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
	//self, referee, admin
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
	public static boolean isAuthenticatedToRead(HttpServletRequest request,String applierUid ) {
		Person self=FormUlti.getPersonInRequest(request);
		Applier applier= InitJdbc.initApplierJdbc().getApplierByUid(applierUid);
		
		if(self!=null&&(
				self.getRole().contains("admin")||
				self.getUid().equals(applier.getOwner())||
				self.getUid().equals(applier.getUid())
						)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean rightRole(HttpServletRequest request, String role){
		if(getPersonInRequest(request)!=null&&getPersonInRequest(request).getRole().equals(role)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean rightProjectStatus(String applierUid, List<String> projectStatuses){
		String projectStatusNow= InitJdbc.initApplicationJdbc().getApplicationByApplier(applierUid).getProjectStatus();
		for(String status:projectStatuses){
			if(projectStatusNow!=null&&projectStatusNow.equals(status)){
				return true;
			}
		}
		return false;

	}
	
	public static String redirectErrorMessage(String message){
		return "redirect:/error?message="+message;
	}
	
	public static String redirectPrevious(HttpServletRequest request){
		return "redirect:"+request.getHeader("Referer");
	}
	
	public static String redirectLogin(){
		return "redirect:/login";
	}
	
	public static boolean isIdenticalPerson(HttpServletRequest request, String personUid){
		if(getPersonInRequest(request)!=null&&getPersonInRequest(request).getUid().equals(personUid)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
