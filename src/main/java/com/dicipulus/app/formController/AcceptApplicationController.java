package com.dicipulus.app.formController;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.model.*;
import com.dicipulus.app.applicationModel.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
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
public class AcceptApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(AcceptApplicationController.class);
	
	@RequestMapping(value="/submit-application-by-applier",method=RequestMethod.GET)
	public String submitApplicationByApplier(HttpServletRequest request){
		//logger
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		//guard null-session
		if(FormUlti.getPersonInRequest(request)==null){
			return FormUlti.redirectErrorMessage("null-session");
		}
		//guard illegal-role
		if(FormUlti.rightRole(request, "applier")==false){
			return FormUlti.redirectErrorMessage("illegal-role");
		}
		logger.info(((Person)request.getSession().getAttribute("person")).getRole());
		logger.info((""+FormUlti.rightRole(request, "applier")));
		//set applierUid in different context
		String applierUid=FormUlti.getPersonInRequest(request).getUid();
		//guard illegal-status
		if(FormUlti.rightProjectStatus(applierUid, Arrays.asList("未提交"))==false){
			return FormUlti.redirectErrorMessage("illegal-status");
		}
		//do the main work
		InitJdbc.initApplicationJdbc().setStatusOfApplication("已提交", applierUid);	
		//return redirect
		return FormUlti.redirectPrevious(request);
	}
	
	@RequestMapping(value="/submit-application-by-referee/{applierUid}", method=RequestMethod.GET)
	public String submitApplicationByReferee(HttpServletRequest request, @PathVariable("applierUid") String applierUid){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		if(FormUlti.getPersonInRequest(request)==null){
			return FormUlti.redirectErrorMessage("null-session");
		}
		if(!FormUlti.isAuthenticatedToRead(request, applierUid)
				||!FormUlti.rightRole(request, "referee")){
			return FormUlti.redirectErrorMessage("illegal-role");
		}
		if(FormUlti.rightProjectStatus(applierUid, Arrays.asList("已提交"))==false){
			return FormUlti.redirectErrorMessage("illegal-status");
		}
		//main work
		InitJdbc.initApplicationJdbc().setStatusOfApplication("已推荐", applierUid);

		return FormUlti.redirectPrevious(request);
	}
	
	@RequestMapping(value="/accept-application-by-admin/{applierUid}", method=RequestMethod.GET)
	public String acceptApplicationByAdmin(HttpServletRequest request, @PathVariable("applierUid") String applierUid){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		if(FormUlti.getPersonInRequest(request)==null){
			return FormUlti.redirectErrorMessage("null-session");
		}
		if(!FormUlti.rightRole(request, "admin")){
			return FormUlti.redirectErrorMessage("illegal-role");
		}
		if(FormUlti.rightProjectStatus(applierUid, Arrays.asList("已推荐"))==false){
			return FormUlti.redirectErrorMessage("illegal-status");
		}
		//main work
		InitJdbc.initApplicationJdbc().setStatusOfApplication("已接收", applierUid);
		
		return FormUlti.redirectPrevious(request);
	}
}
