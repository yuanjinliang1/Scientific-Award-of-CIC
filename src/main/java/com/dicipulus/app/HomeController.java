package com.dicipulus.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("person")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	//what inside the "value" is the actual url (relative url)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value={"/gibclay","/poland"}, method = RequestMethod.GET)
	public ModelAndView student(){
		logger.info("GET Murica!");
		return new ModelAndView("cemetery","command2", new Dicipulus());
	}
	@RequestMapping(value="/takeme", method = RequestMethod.POST)
	public ModelAndView addstudent(Dicipulus student, Model model) {
		logger.info("POST Murica!"+student.getName());
		model.addAttribute("name", student.getName());
		model.addAttribute("phone", student.getPhone());
		return new ModelAndView("see-cemetery","command",  new Location());
	}
	@RequestMapping(value="/redirect", method=RequestMethod.POST)
	public String redirect(Location location){
		if(location.getLocation().equals("poland")){
			logger.info("redirect to poland");
			return "redirect:poland";
		}
		else {
			logger.info("not poland");
			return "home";
		}
	}
	
	@RequestMapping(value="/enroll",method=RequestMethod.POST)
	public ModelAndView enrollStudentPost(@ModelAttribute("studentAttr") Dicipulus student, ModelAndView mav){
		
		logger.info("processing enrollment of "+student.getName() );
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DicipulusJDBC studentJDBC =(DicipulusJDBC)context.getBean("studentJDBC");
		context.registerShutdownHook();
		studentJDBC.create(student.getName(),student.getId(),student.getPhone());
		mav.setViewName("createDicipulus");
		mav.addObject("freshman",student);
		return mav;
		//return new ModelAndView("createDicipulus","freshman",student);//(jsp location, view model name, view model itself)
	}
	@RequestMapping(value="/enroll",method=RequestMethod.GET	)
	public ModelAndView enrollStudentGet(ModelAndView mav){
		logger.info("initialize enrollment");
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DicipulusJDBC studentJDBC =(DicipulusJDBC)context.getBean("studentJDBC");
		
		mav.setViewName("createDicipulus");
		Dicipulus student= studentJDBC.getDicipulus(1848);
		Dicipulus student2= studentJDBC.getDicipulus(1999);
		mav.addObject("freshman",student);
		mav.addObject("freshman2",student2);
		return mav;
	}
}
