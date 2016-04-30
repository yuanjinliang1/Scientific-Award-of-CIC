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
public class CreateFormsJdbc{
	private static final Logger logger=LoggerFactory.getLogger(CreateFormsJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public void createAllForms(Applier applier){
		
		createProjectMajor(applier);
		createSecondRefereeUnitOpinion(applier);
	}
	
	private void createProjectMajor(Applier applier){
		String nameAndCodeOFPlansOrFundations="������300�֡�Ӧ����Ҫ�̶�������д���ȹ��Ҽƻ����������ƻ���������10��";
		String technicalReportNumber="ָ�ڹ��ҿƼ��ƻ���Ŀ�걨���ĳʽ��ĿƼ������ţ�δ�ʽ��Ŀɲ���";
		String sql="insert into project_major (applierUid, yearCreated, refereeString,secretLevel,NameAndCodeOFPlansOrFundations,technicalReportNumber) values(?,?,?,'����',?,?)";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		RefereeJdbc refereeJdbc=InitJdbc.initRefereeJdbc();
		Referee referee=refereeJdbc.getRefereeByUid(applier.getOwner());
		jdbcTemplateObject.update(sql,applier.getUid(),year,referee.getName(),nameAndCodeOFPlansOrFundations,technicalReportNumber);
		logger.info(sql);
	}
	
	private void createSecondRefereeUnitOpinion(Applier applier){
		String sql="insert into secondrefereeunitopinion (applierUid,refereeUnitName) values(?,?)";
		RefereeJdbc refereeJdbc= InitJdbc.initRefereeJdbc();
		Referee referee=refereeJdbc.getRefereeByUid(applier.getOwner());
		jdbcTemplateObject.update(sql,applier.getUid(),referee.getName());
		logger.info(sql);
	}
	
}





	
	
	