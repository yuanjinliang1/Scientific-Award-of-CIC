package com.dicipulus.app.controller;

import com.dicipulus.app.*;
import com.dicipulus.app.JDBC.*;
import com.dicipulus.app.formController.FormUlti;
import com.dicipulus.app.model.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Controller
@SessionAttributes("person")
public class ApplierManagedByRefereeController{
	private static final Logger logger=LoggerFactory.getLogger(ApplierManagedByRefereeController.class);
	
	private Person getPersonInRequest(HttpServletRequest request){
		HttpSession session= request.getSession();
		Person person =(Person) session.getAttribute("person");
		return person;
	}
	
	//only when session and ownerUid in url match with each other, authentication is granted
	private boolean isAuthenticated(HttpServletRequest request, String ownerUid){
		Person person = FormUlti.getPersonInRequest(request);
		logger.debug("session uid="+person.getUid()+", "+"ownerUid="+ownerUid);
		if(person.getUid().equals(ownerUid)){
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value="/applier-managed-by-referee/applier-view/{ownerUid}",method=RequestMethod.GET)
	public ModelAndView showApplierList(ModelAndView modelAndView,@PathVariable("ownerUid") String ownerUid,HttpServletRequest request){
		
		logger.info("showApplierList()");
		
		try{
			if(isAuthenticated(request, ownerUid)==false){
					modelAndView.setViewName("redirect:/login");
					logger.info("authentication denied!");
					return modelAndView;
				}
				else{
					logger.info("authentication confirmed!");
					ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
					
					modelAndView.setViewName("applierManagedByReferee");
					modelAndView.addObject("appliers",applierJdbc.getAppliers(ownerUid));
					return modelAndView;
				}
		}
		catch(NullPointerException e){
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/applier-managed-by-referee/applier-create", method=RequestMethod.POST)
	public String createApplier(HttpServletRequest request){
		logger.info("createApplier()");
		Person person =FormUlti.getPersonInRequest(request);
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		String applierUid=applierJdbc.createApplierForReferee(person.getUid());
		InitJdbc.initCreateFormsJdbc().createAllForms(applierJdbc.getApplierByUid(applierUid));//初始化所有表
		return "redirect:applier-view/"+person.getUid();
	}
	
	/**
	 * 删除项目组的同时也要删除所有数据表中该项目组的记录
	 * @param request
	 * @param uid
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/applier-managed-by-referee/delete-applier", method=RequestMethod.GET)
	public String deleteApplier(HttpServletRequest request, @RequestParam String uid) throws IOException{
		logger.info("deleteApplier()");
		Person person =FormUlti.getPersonInRequest(request);
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		DeleteFormsJdbc deleteFormsJdbc=InitJdbc.initDeleteFormsJdbc();
		deleteFormsJdbc.deleteAllForms(applierJdbc.getApplierByUid(uid));//先删表
		File dictionary = new File(MyProperties.getRootPath() + uid);
		if(dictionary.exists()==true){
			FileUtils.cleanDirectory(dictionary);//删除文件夹内文件
		}
		applierJdbc.deleteApplier(uid);//再删记录
		return "redirect:applier-view/"+person.getUid();
	}
	
	@RequestMapping(value="/applier-managed-by-referee/reset-password", method=RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, @RequestParam String uid){
		logger.info("resetPassword()");
		Person person =FormUlti.getPersonInRequest(request);
		ApplierJdbc applierJdbc=InitJdbc.initApplierJdbc();
		
		applierJdbc.resetPassword(uid);
		return "redirect:applier-view/"+person.getUid();
	}
}