package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplierJdbc extends Applier {
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Applier getApplierByUid(String uid){
		String sql="select * from applier where uid=?";
		
		Applier applier=jdbcTemplateObject.queryForObject(sql, new Object[]{uid}, BeanPropertyRowMapper.newInstance(Applier.class));
		System.out.println(sql);
		return applier;
	}
	public void changePassword(String uid,String password){
		String sql="update applier set password=? where uid=?";
		
		jdbcTemplateObject.update(sql, password,uid);
		System.out.println(sql);
	}
}