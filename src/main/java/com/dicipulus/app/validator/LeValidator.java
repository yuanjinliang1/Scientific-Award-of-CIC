package com.dicipulus.app.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.formController.FormUlti;

public final class LeValidator {
	public static boolean validateSession(HttpServletRequest request, ModelAndView modelAndView){
		if(FormUlti.getPersonInRequest(request)==null){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("null-session"));
			return false;
		}else{
			return true;
		}
	}
	public static boolean validateSession(HttpServletRequest request, String view){
		if(FormUlti.getPersonInRequest(request)==null){
			view=FormUlti.redirectErrorMessage("null-session");
			return false;
		}else{
			return true;
		}
	}
	public static boolean validateRole(HttpServletRequest request, ModelAndView modelAndView,
			String expectedRole){
		if(FormUlti.rightRole(request, expectedRole)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-role"));
			return false;
		}else{
			return true;
		}
	}
	public static boolean validateRole(HttpServletRequest request, String view,
			String expectedRole){
		if(FormUlti.rightRole(request, expectedRole)==false){
			view=FormUlti.redirectErrorMessage("illegal-role");
			return false;
		}else{
			return true;
		}
	}
	public static boolean validateUid(HttpServletRequest request, ModelAndView modelAndView,
			String expectedUid){
		if(FormUlti.isIdenticalPerson(request, expectedUid)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-role"));
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateUid(HttpServletRequest request, String view,
			String expectedUid){
		if(FormUlti.isIdenticalPerson(request, expectedUid)==false){
			view=FormUlti.redirectErrorMessage("illegal-role");
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateRead(HttpServletRequest request, ModelAndView modelAndView,
			String ownerUid){
		if(FormUlti.isAuthenticatedToRead(request, ownerUid)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("no-authority"));
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateRead(HttpServletRequest request, String view,
			String ownerUid){
		if(FormUlti.isAuthenticatedToRead(request, ownerUid)==false){
			view=FormUlti.redirectErrorMessage("no-authority");
			return false;
		}else {
			return true;
		}
	}
	public static boolean validateStatus(HttpServletRequest request, ModelAndView modelAndView,
			List<String> expectedStatuses){
		if(FormUlti.rightProjectStatus(FormUlti.getPersonInRequest(request)
				.getUid(),expectedStatuses)==false){
			modelAndView.setViewName(FormUlti.redirectErrorMessage("illegal-status"));
			return false;
		}else{
			return true;
		}
	}
	public static boolean validateStatus(HttpServletRequest request, String view,
			List<String> expectedStatuses){
		if(FormUlti.rightProjectStatus(FormUlti.getPersonInRequest(request)
				.getUid(),expectedStatuses)==false){
			view=FormUlti.redirectErrorMessage("illegal-status");
			return false;
		}else{
			return true;
		}
	}
}
