package com.dicipulus.app.validator;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.formController.FormUlti;

@Deprecated
public class Validator {
	/** empty String means do not validate this parameter*/
	public String expectedRole;
	public String expectedUid;
	public List<String> expectedStatuses;
	public String ownerUid;
	
	

	public Validator(String expectedRole, String expectedUid,
			List<String> expectedStatuses, String ownerUid) {
		super();
		this.expectedRole = expectedRole;
		this.expectedUid = expectedUid;
		this.expectedStatuses = expectedStatuses;
		this.ownerUid = ownerUid;
	}
	
	
	
	public boolean validate(HttpServletRequest request, ModelAndView modelAndView){
		if(FormUlti.getPersonInRequest(request)==null){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("null-session"));
			return false;
		}else if(expectedRole!=null&&FormUlti.rightRole(request, expectedRole)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-role"));
			return false;
		}else if(expectedUid!=null&&FormUlti.isIdenticalPerson(request, expectedUid)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-role"));
			return false;
		}else if(ownerUid!=null&&FormUlti.isAuthenticatedToRead(request, ownerUid)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("no-authority"));
			return false;
		}else if(expectedStatuses!=null&&FormUlti.rightProjectStatus(FormUlti.getPersonInRequest(request).getUid(),expectedStatuses)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-status"));
			return false;
		}else{
			return true;
		}
	}
	public boolean validate(HttpServletRequest request, String view){
		if(FormUlti.getPersonInRequest(request)==null){
			view=FormUlti.redirectErrorMessage("null-session");
			return false;
		}else if(expectedRole!=null&&FormUlti.rightRole(request, expectedRole)==false){
			view=FormUlti.redirectErrorMessage("illegal-role");
			return false;
		}else if(expectedUid!=null&&FormUlti.isIdenticalPerson(request, expectedUid)==false){
			view=FormUlti.redirectErrorMessage("illegal-role");
			return false;
		}else if(ownerUid!=null&&FormUlti.isAuthenticatedToRead(request, ownerUid)==false){
			view=FormUlti.redirectErrorMessage("no-authority");
			return false;
		}else if(expectedStatuses!=null&&FormUlti.rightProjectStatus(FormUlti.getPersonInRequest(request).getUid(),expectedStatuses)==false){
			view=FormUlti.redirectErrorMessage("illegal-status");
			return false;
		}else{
			return true;
		}
	}
	
}
