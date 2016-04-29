package com.dicipulus.app.JDBC;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.model.*;

public class ReviewerJdbc{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Reviewer getReviewerByUid(String uid){
		String sql="select * from reviewer where uid= ?";
		
		Reviewer reviewer =jdbcTemplateObject.queryForObject(sql, new Object[]{uid},BeanPropertyRowMapper.newInstance(Reviewer.class));
		System.out.println(sql);
		return reviewer;
	}
	public void changePassword(String uid,String password){
		String sql="update reviewer set password=? where uid=?";
		
		jdbcTemplateObject.update(sql, password,uid);
		System.out.println(sql);
	}
}
