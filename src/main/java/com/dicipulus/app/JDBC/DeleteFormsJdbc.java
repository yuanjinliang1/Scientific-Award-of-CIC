package com.dicipulus.app.JDBC;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.*;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DeleteFormsJdbc{
	private static final Logger logger=LoggerFactory.getLogger(CreateFormsJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public void deleteAllForms(Applier applier){
		deleteProjectMajor(applier);
		deleteSecondRefereeUnitOpinion(applier);
		deleteSeventhPaperCitedByOthers(applier);
		deleteSeventhIntellectualPropertyDoc(applier);
		deleteEighthMajorContributor(applier);
		deleteNinethMajorOrgContributor(applier);
	}
	
	public void deleteProjectMajor(Applier applier){
		String sql="delete from project_major where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
	
	public void deleteSecondRefereeUnitOpinion(Applier applier){
		String sql="delete from secondrefereeunitopinion where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
	
	public void deleteSeventhPaperCitedByOthers(Applier applier){
		String sql="delete from paper_cited_by_others where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
	
	public void deleteSeventhIntellectualPropertyDoc(Applier applier){
		String sql="delete from intellectual_property_doc where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
	
	public void deleteEighthMajorContributor(Applier applier){
		String sql="delete from major_contributor where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
	
	public void deleteNinethMajorOrgContributor(Applier applier){
		String sql="delete from major_org_contributor where applierUid=? ";
		jdbcTemplateObject.update(sql,applier.getUid());
		logger.info(sql);
	}
}
	