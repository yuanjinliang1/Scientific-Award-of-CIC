package com.dicipulus.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dicipulus.app.formController.FormUlti;

@Controller
@SessionAttributes("person")
public class ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public ModelAndView errorHandle(HttpServletRequest request,@RequestParam("message") String message,ModelAndView modelAndView){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() );
		
		if(message.contains("session")){
			modelAndView.setViewName(FormUlti.redirectLogin());
			return modelAndView;
		}
		if(message.equals("illegal-status")){
			message="状态错误，您无权进行此操作！";
		}
		if(message.equals("null-session")){
			message="您尚未登陆，请先登录！";
		}
		if(message.equals("illegal-role")){
			message="您的角色无权进行此操作！";
		}
		modelAndView.addObject("message",message);
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
