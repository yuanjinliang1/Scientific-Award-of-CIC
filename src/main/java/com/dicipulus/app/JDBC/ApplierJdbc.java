package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplierJdbc extends Applier {
	private static final Logger logger=LoggerFactory.getLogger(RefereeJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Applier getApplierByUid(String uid){
		String sql="select * from applier where uid=?";
		
		Applier applier=jdbcTemplateObject.queryForObject(sql, new Object[]{uid}, BeanPropertyRowMapper.newInstance(Applier.class));
		logger.info("SQL: "+sql);
		logger.info("uid:"+uid);
		return applier;
	}
	
	public List<Applier> getAppliers( String refereeUid){
		String sql="Select * from applier where owner=?";
		List<Applier> appliers=jdbcTemplateObject.query(sql, new Object[]{refereeUid},BeanPropertyRowMapper.newInstance(Applier.class));
		return appliers;
	}
	
	public void changePassword(String uid,String password){
		String sql="update applier set password=? where uid=?";
		
		jdbcTemplateObject.update(sql, password,uid);
		logger.info("SQL: "+sql);
		logger.info("uid:"+uid+", password:"+password);
		System.out.println(sql);
	}
}