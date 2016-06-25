package com.dicipulus.app.JDBC;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.EighthMajorContributor;
import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FirstProjectBasicSituationJdbc{
	private static final Logger logger=LoggerFactory.getLogger(FirstProjectBasicSituationJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public FirstProjectBasicSituation getFirstProjectBasicSituation(String applierUid){
		String sql="select * from dicipulus.project_major where applierUid=?";
		FirstProjectBasicSituation firstProjectBasicSituation=
				jdbcTemplateObject.queryForObject(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(FirstProjectBasicSituation.class));
		logger.info("SQL: "+sql);
		logger.info(firstProjectBasicSituation.toString());
		return firstProjectBasicSituation;
	}
	

	@Deprecated
	public FirstProjectBasicSituation completeFirstProjectBasicSituation (FirstProjectBasicSituation firstProjectBasicSituation){
        firstProjectBasicSituation.setMajorContributingOrgNames(getMajorContributorOrgNames(firstProjectBasicSituation.getApplierUid()));
        firstProjectBasicSituation.setMajorContributorNames(getMajorContributorNames(firstProjectBasicSituation.getApplierUid()));
        return firstProjectBasicSituation;
	}
	/*
	 * majorContributorNames 和 majorContributingOrgNames 先留着，但不用
	 */

	public List<FirstProjectBasicSituation> getFirstProjectBasicSituationByYear(int year){
		String sql="select * from project_major where yearCreated=?";
		List<FirstProjectBasicSituation> FirstProjectBasicSituations=jdbcTemplateObject.query(sql, BeanPropertyRowMapper.newInstance(FirstProjectBasicSituation.class));
		logger.info("SQL: "+sql);
		return FirstProjectBasicSituations;
	}

	public void setFirstProjectBasicSituation(FirstProjectBasicSituation firstForm, String editThirdProjectBriefIntroductioneditThirdProjectBriefIntroduction){
		String sql="UPDATE `dicipulus`.`project_major` SET `projectName`=?,"
				+ " `subjectCategoryName1`=?,"
				+ " `subjectCategoryId1`=?, `subjectCategoryName2`=?, `subjectCategoryId2`=?, `subjectCategoryName3`=?,"
				+ " `subjectCategoryId3`=?, `economicField`=?, `nationalFocusField`=?, `technologicalField`=?, `taskSource1`=?, `taskSource2`=?,`taskSource3`=?,"
				+ "`NameAndCodeOfPlansOrFundations`=?, `technicalReportNumber`=?, `numOfOtherIntellectualProperty`=?, "
				+ "`numOfInventionPatent`=?, `startDate`=?, `finishDate`=?, "
				+ "`applierContactName`=?, `applierContactPhone`=?, `applierContactEmail`=? "
				+ "WHERE `applierUid`=?;";
		
		jdbcTemplateObject.update(sql,firstForm.getProjectName(),
				firstForm.getSubjectCategoryName1(),firstForm.getSubjectCategoryId1(),
				firstForm.getSubjectCategoryName2(),firstForm.getSubjectCategoryId2(),
				firstForm.getSubjectCategoryName3(),firstForm.getSubjectCategoryId3(),
				firstForm.getEconomicField(),firstForm.getNationalFocusField(),firstForm.getTechnologicalField(),firstForm.getTaskSource1(),firstForm.getTaskSource2(),firstForm.getTaskSource3(),
				firstForm.getNameAndCodeOfPlansOrFundations(),firstForm.getTechnicalReportNumber(),firstForm.getNumOfOtherIntellectualProperty(),
				firstForm.getNumOfInventionPatent(),firstForm.getStartDate(),firstForm.getFinishDate(),
				
				firstForm.getApplierContactName(),firstForm.getApplierContactPhone(),firstForm.getApplierContactEmail(),
				firstForm.getApplierUid());
		logger.info(sql);
		logger.info(firstForm.toString());
	}
	
	public void setMajorContributorNamesForFirstForm(String applierUid ){
		String sql="update project_major set majorContributorNames=? where applierUid=?";
		jdbcTemplateObject.update(sql,getMajorContributorNames(applierUid),applierUid);
		logger.info(sql);
		logger.info("majorContributorNames:"+getMajorContributorNames(applierUid));
	}
	
	public void setMajorContributingOrgNamesForFirstForm(String applierUid){
		String sql="update project_major set majorContributingOrgNames=? where applierUid=?";
		jdbcTemplateObject.update(sql,getMajorContributorOrgNames(applierUid),applierUid);
		logger.info(sql);
		logger.info("majorContributingOrgNames:"+getMajorContributorOrgNames(applierUid));
	}
	
	public void setRefereeInformation(SecondRefereeUnitOpinion secondForm,String applierUid){
		String sql="update project_major set refereeString=?,refereeContactName=?,refereeContactPhone=?,RefereeContactEmail=? where applierUid=?";
		jdbcTemplateObject.update(sql,secondForm.getRefereeUnitName(),secondForm.getContact(),secondForm.getPhoneNumber(),secondForm.getEmail(),applierUid);
		logger.info(sql);
	}
	
	private String getMajorContributorNames(String applierUid){
		EighthMajorContributorJdbc eighthMajorContributorJdbc=InitJdbc.initEighthMajorContributorJdbc();
		List<EighthMajorContributor> eighthMajorContributor=eighthMajorContributorJdbc.getEighthMajorContributors(applierUid);	
		Collections.sort(eighthMajorContributor,new Comparator<EighthMajorContributor>(){
			public int compare(EighthMajorContributor eighthMajorContributor1, EighthMajorContributor eighthMajorContributor2){
				return eighthMajorContributor1.getRankOfContributor().compareTo(eighthMajorContributor2.getRankOfContributor());
			}
		});
		
		StringBuffer majorContributors=new StringBuffer();
        for(EighthMajorContributor Contributors:eighthMajorContributor){
        	majorContributors.append(",").append(Contributors.getNameOfContributor());
        }
        if(majorContributors==null||majorContributors.toString().isEmpty()){
        	return "";
        }
        return majorContributors.toString().substring(1);
	}
	
	private String getMajorContributorOrgNames(String applierUid){
		NinethMajorOrgContributorJdbc ninethMajorOrgContributorJdbc=InitJdbc.initNinethMajorOrgContributorJdbc();
		List<NinethMajorOrgContributor> ninethMajorOrgContributor=ninethMajorOrgContributorJdbc.getNinethMajorOrgContributors(applierUid);
		Collections.sort(ninethMajorOrgContributor, new Comparator<NinethMajorOrgContributor>(){
			public int compare(NinethMajorOrgContributor ninethMajorOrgContributor1,NinethMajorOrgContributor ninethMajorOrgContributor2){
				return ninethMajorOrgContributor1.getRankOfOrg()-(ninethMajorOrgContributor2.getRankOfOrg());
			}
		});
		
		StringBuffer majorOrgContributors=new StringBuffer();
        for(NinethMajorOrgContributor orgContributors:ninethMajorOrgContributor){
        	majorOrgContributors.append(",").append(orgContributors.getNameOfOrg());
        }
        if(majorOrgContributors==null||majorOrgContributors.toString().isEmpty()){
        	return "";
        }
		return majorOrgContributors.toString().substring(1);
	}
	
}
