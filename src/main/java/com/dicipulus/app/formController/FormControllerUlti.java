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
}
