package com.dicipulus.app.ajax;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicipulus.app.JDBC.ApplicationJdbc;
import com.dicipulus.app.JDBC.InitJdbc;
import com.dicipulus.app.model.Application;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	List<Application> applications;
	
	@JsonView(AjaxViews.Public.class)
	@RequestMapping(value="/ajax-test")
	public @ResponseBody AjaxResponseBody updateApplicationViaAjax(@RequestBody Application application){
		logger.info("updateApplicationViaAjax");
		logger.info(application.toString());
		AjaxResponseBody result= new AjaxResponseBody();
		
		ApplicationJdbc applicationJdbc=InitJdbc.initApplicationJdbc();
		applicationJdbc.setApplicationByAdmin(application);
		Application newApplicaiton =applicationJdbc.getApplicationByApplier(application.getApplierUid());
		
		if(newApplicaiton==null){
			result.setCode("400");
			result.setMsg("database error");
		}
		else {
			result.setCode("200");
			result.setMsg("");
			result.setResult(newApplicaiton);
		}
		return result;
	}
	
	@RequestMapping(value="/ajax")
	public String ajax(){
		
		return "ajax";
	}
}
