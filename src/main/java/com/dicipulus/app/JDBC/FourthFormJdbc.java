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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FourthFormJdbc{
	private static final Logger logger=LoggerFactory.getLogger(RefereeJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public FourthForm getFourthForm(String applierUid){
		String sql="select * from project_major where applierUid=?;";
		FourthForm fourthForm=jdbcTemplateObject.queryForObject(sql, new Object[]{applierUid},
				BeanPropertyRowMapper.newInstance(FourthForm.class));
		logger.info("SQL: "+sql);
		return fourthForm;
	}
	
	public void setFourthForm(FourthForm fourthForm, String applierUid){
		String sql="update project_major set fourthForm1=?, fourthForm2=? where applierUid=?;";
		jdbcTemplateObject.update(sql,fourthForm.getFourthForm1(),fourthForm.getFourthForm2(),applierUid);
		logger.info("SQL: "+sql);
	}
}