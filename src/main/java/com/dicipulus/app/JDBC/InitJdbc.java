package com.dicipulus.app.JDBC;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.*;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;
import com.dicipulus.app.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * 返回Jdbc实例的静态方法
 * @author dy
 *
 */
@Repository
public final class InitJdbc{
	private static final Logger logger=LoggerFactory.getLogger(InitJdbc.class);
	
	public static ApplierJdbc initApplierJdbc() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		ApplierJdbc applierJdbc = (ApplierJdbc) context.getBean("applierJdbc");
		context.registerShutdownHook();
		return applierJdbc;
	}
	public static RefereeJdbc initRefereeJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		RefereeJdbc refereeJdbc=(RefereeJdbc)context.getBean("refereeJdbc");
		context.registerShutdownHook();
		return refereeJdbc;
	}
	private static AdminJdbc initAdminJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AdminJdbc adminJdbc=(AdminJdbc)context.getBean("adminJdbc");
		context.registerShutdownHook();//shutdown application context, from tutorialpoints.com
		return adminJdbc;
	}
	
	public static CreateFormsJdbc initCreateFormsJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		CreateFormsJdbc createFormsJdbc = (CreateFormsJdbc) context.getBean("createFormsJdbc");
		context.registerShutdownHook();
		return createFormsJdbc;
	}
	
	public static DeleteFormsJdbc initDeleteFormsJdbc(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		DeleteFormsJdbc deleteFormsJdbc =(DeleteFormsJdbc) context.getBean("deleteFormsJdbc");
		context.registerShutdownHook();
		return deleteFormsJdbc;
	}
	
	public static FirstProjectBasicSituationJdbc initFirstProjectBasicSituationJdbc() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		FirstProjectBasicSituationJdbc firstProjectBasicSituationJdbc = (FirstProjectBasicSituationJdbc) context.getBean("firstProjectBasicSituationJdbc");
		context.registerShutdownHook();
		return firstProjectBasicSituationJdbc;
	}
	
	public static SecondRefereeUnitOpinionJdbc initSecondRefereeUitOpinionJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=(SecondRefereeUnitOpinionJdbc) context.getBean("secondRefereeUnitOpinionJdbc");
		context.registerShutdownHook();
		return secondRefereeUnitOpinionJdbc;
	}
	
	public static ThirdProjectBriefIntroductionJdbc initThirdProjectBriefIntroductioJdbcn(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		ThirdProjectBriefIntroductionJdbc thirdProjectBriefIntroductionJdbc=(ThirdProjectBriefIntroductionJdbc) context.getBean("thirdProjectBriefIntroductionJdbc");
		context.registerShutdownHook();
		return thirdProjectBriefIntroductionJdbc;
	}
	
	public static FourthFormJdbc initFourthFormJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		FourthFormJdbc fourthFormJdbc=(FourthFormJdbc) context.getBean("fourthFormJdbc");
		context.registerShutdownHook();
		return fourthFormJdbc;
	}
	
	public static FifthObjectiveEvaluationJdbc initFifthObjectiveEvaluationJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		FifthObjectiveEvaluationJdbc fifthObjectiveEvaluationJdbc=(FifthObjectiveEvaluationJdbc) context.getBean("fifthObjectiveEvaluationJdbc");
		context.registerShutdownHook();
		return fifthObjectiveEvaluationJdbc;
	}
}