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
	public static AdminJdbc initAdminJdbc(){
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
	
	public static SecondRefereeUnitOpinionJdbc initSecondRefereeUnitOpinionJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SecondRefereeUnitOpinionJdbc secondRefereeUnitOpinionJdbc=(SecondRefereeUnitOpinionJdbc) context.getBean("secondRefereeUnitOpinionJdbc");
		context.registerShutdownHook();
		return secondRefereeUnitOpinionJdbc;
	}
	
	public static ThirdProjectBriefIntroductionJdbc initThirdProjectBriefIntroductionJdbc(){
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
	

	public static SixthApplyUnitSituationJdbc initSixthApplyUnitSituationJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SixthApplyUnitSituationJdbc sixthAppliedUnitSituation=(SixthApplyUnitSituationJdbc) context.getBean("sixthApplyUnitSituationJdbc");
		context.registerShutdownHook();
		return sixthAppliedUnitSituation;
	}
	
	public static SixthEconomicAndSocialBenefitJdbc initSixthEconomicAndSocialBenefitJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SixthEconomicAndSocialBenefitJdbc sixthEconomicAndSocialBenefitJdbc=(SixthEconomicAndSocialBenefitJdbc) context.getBean("sixthEconomicAndSocialBenefitJdbc");
		context.registerShutdownHook();
		return sixthEconomicAndSocialBenefitJdbc;
	}
	
	public static SixthPaperMonographNTJdbc initSixthPaperMonographNTJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SixthPaperMonographNTJdbc sixthPaperMonographNTJdbc=(SixthPaperMonographNTJdbc) context.getBean("sixthPaperMonographNTJdbc");
		context.registerShutdownHook();
		return sixthPaperMonographNTJdbc;
	}

	public static SeventhPaperCitedByOthersJdbc initSeventhPaperCitedByOthersJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SeventhPaperCitedByOthersJdbc seventhPaperCitedByOthersJdbc=(SeventhPaperCitedByOthersJdbc) context.getBean("seventhPaperCitedByOthersJdbc");
		context.registerShutdownHook();
		return seventhPaperCitedByOthersJdbc;
	}
	
	public static SeventhIntellectualPropertyDocJdbc initSeventhIntellectualPropertyDocJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		SeventhIntellectualPropertyDocJdbc seventhIntellectualPropertyDocJdbc=(SeventhIntellectualPropertyDocJdbc) context.getBean("seventhIntellectualPropertyDocJdbc");
		context.registerShutdownHook();
		return seventhIntellectualPropertyDocJdbc;
	}
	
	public static EighthMajorContributorJdbc initEighthMajorContributorJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		EighthMajorContributorJdbc eighthMajorContributorJdbc=(EighthMajorContributorJdbc) context.getBean("eighthMajorContributorJdbc");
		context.registerShutdownHook();
		return eighthMajorContributorJdbc;
	}
	
	public static NinethMajorOrgContributorJdbc initNinethMajorOrgContributorJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=(NinethMajorOrgContributorJdbc) context.getBean("ninethMajorOrgContributorJdbc");
		context.registerShutdownHook();
		return ninethMajorOrgContributorJdbc;
	}
	
	public static ApplicationJdbc initApplicationJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		ApplicationJdbc applicationJdbc=(ApplicationJdbc) context.getBean("applicationJdbc");
		context.registerShutdownHook();
		return applicationJdbc;
	}
	
	public static ManageExcelJdbc initManageExcelJdbc(){
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		ManageExcelJdbc manageExcelJdbc=(ManageExcelJdbc) context.getBean("manageExcelJdbc");
		context.registerShutdownHook();
		return manageExcelJdbc;
	}
}
