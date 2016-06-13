package com.dicipulus.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("person")
public class RootController {
	private static final Logger logger=LoggerFactory.getLogger(RootController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String root(){
		return "redirect:/login";
	}
}
