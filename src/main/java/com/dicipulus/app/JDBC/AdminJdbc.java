package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dicipulus.app.model.Admin;

public class AdminJdbc {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Admin getAdmin(){
		String sql="select * from admin where uid = ?";
		
		Admin admin=jdbcTemplateObject.queryForObject(sql, new Object[]{"admin"}, new AdminMapper());
		System.out.println(sql);
		return admin;
	}
	
	public void changePassword(String password){
		String sql="update admin set password=? where uid='admin'";
		
		jdbcTemplateObject.update(sql, password);
		System.out.println(sql);
	}
}