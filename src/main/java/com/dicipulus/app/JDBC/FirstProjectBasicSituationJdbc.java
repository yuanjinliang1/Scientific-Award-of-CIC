package com.dicipulus.app.JDBC;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
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
	
	public List<FirstProjectBasicSituation> getFirstProjectBasicSituationByYear(int year){
		String sql="select * from project_major where yearCreated=?";
		List<FirstProjectBasicSituation> FirstProjectBasicSituations=jdbcTemplateObject.query(sql, BeanPropertyRowMapper.newInstance(FirstProjectBasicSituation.class));
		logger.info("SQL: "+sql);
		return FirstProjectBasicSituations;
	}
	
	public void setFirstProjectBasicSituation(FirstProjectBasicSituation firstForm, String applierUid){
		String sql="UPDATE `dicipulus`.`project_major` SET `yearCreated`=?, `refereeString`=?, `projectName`=?,"
				+ " `majorContributorNames`=?, `majorContributingOrgNames`=?, `secretLevel`=?, `subjectCategoryName1`=?,"
				+ " `subjectCategoryId1`=?, `subjectCategoryName2`=?, `subjectCategoryId2`=?, `subjectCategoryName3`=?,"
				+ " `subjectCategoryId3`=?, `economicField`=?, `nationalFocusField`=?, `technologicalField`=?, `taskSource`=?, "
				+ "`NameAndCodeOfPlansOrFundations`=?, `technicalReportNumber`=?, `numOfOtherIntellectualProperty`=?, "
				+ "`numOfInventionPatent`=?, `startDate`=?, `finishDate`=?, `refereeContactName`=?, `refereeContactPhone`=?,"
				+ " `refereeContactEmail`=?, `applierContactName`=?, `applierContactPhone`=?, `applierContactEmail`=? "
				+ "WHERE `applierUid`=?;";
		
		jdbcTemplateObject.update(sql,firstForm.getYearCreated(),firstForm.getRefereeString(),firstForm.getProjectName(),
				firstForm.getMajorContributorNames(),firstForm.getMajorContributingOrgNames(),firstForm.getSecretLevel(),
				firstForm.getSubjectCategoryName1(),firstForm.getSubjectCategoryId1(),
				firstForm.getSubjectCategoryName2(),firstForm.getSubjectCategoryId2(),
				firstForm.getSubjectCategoryName3(),firstForm.getSubjectCategoryId3(),
				firstForm.getEconomicField(),firstForm.getNationalFocusField(),firstForm.getTechnologicalField(),firstForm.getTaskSource(),
				firstForm.getNameAndCodeOfPlansOrFundations(),firstForm.getTechnicalReportNumber(),firstForm.getNumOfOtherIntellectualProperty(),
				firstForm.getNumOfInventionPatent(),firstForm.getStartDate(),firstForm.getFinishDate(),
				firstForm.getRefereeContactName(),firstForm.getRefereeContactPhone(),firstForm.getRefereeContactEmail(),
				firstForm.getApplierContactName(),firstForm.getApplierContactPhone(),firstForm.getApplierContactEmail(),
				firstForm.getApplierUid());
		logger.info(sql);
		logger.info(firstForm.toString());
	}
	
	public void setMajorContributorNames(String majorContributorNames,String applierUid ){
		String sql="update project_major set majorContributorNames=? where applierUid=?";
		jdbcTemplateObject.update(sql,majorContributorNames,applierUid);
		logger.info(sql);
		logger.info("majorContributorNames:"+majorContributorNames);
	}
	
	public void setMajorContributingOrgNames(String majorContributingOrgNames, String applierUid){
		String sql="update project_major set majorContributingOrgNames=? where applierUid=?";
		jdbcTemplateObject.update(sql,majorContributingOrgNames,applierUid);
		logger.info(sql);
		logger.info("majorContributingOrgNames:"+majorContributingOrgNames);
	}
	
	public void setRefereeInformation(SecondRefereeUnitOpinion secondForm,String applierUid){
		String sql="update project_major set refereeString=?,refereeContactName=?,refereeContactPhone=?,RefereeContactEmail=? where applierUid=?";
		jdbcTemplateObject.update(sql,secondForm.getRefereeUnitName(),secondForm.getContact(),secondForm.getPhoneNumber(),secondForm.getEmail(),applierUid);
		logger.info(sql);
	}
}