package com.dicipulus.app.JDBC;

import java.util.*;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;





import com.dicipulus.app.model.*;

@Repository
public class RefereeJdbc {
	private static final Logger logger=LoggerFactory.getLogger(RefereeJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Referee getRefereeByUid(String uid){
		String sql="select * from referee where uid = ?";
		
		Referee referee=jdbcTemplateObject.queryForObject(sql, new Object[]{uid}, BeanPropertyRowMapper.newInstance(Referee.class));
		System.out.println(sql);
		return referee;
	}
	
	public void changePassword(String uid,String password){
		String sql="update referee set password=? where uid=?";
		
		jdbcTemplateObject.update(sql, password,uid);
		
	}
	
	public void resetPassword(String uid){
		String newPassword=getRandomPassword();
		String sql="update referee set password=? where uid=?";
		jdbcTemplateObject.update(sql, newPassword ,uid);
		logger.info("SQL: "+sql);
		logger.info("uid:"+uid+", password:"+newPassword);
	}
	
	public List<Referee> getReferees(){
		String sql="Select * from referee";
		List<Referee> referees=jdbcTemplateObject.query(sql, BeanPropertyRowMapper.newInstance(Referee.class));
		return referees;
	}
	
	public void createReferee(String uid, String name){//for admin
		
		String password=getRandomPassword();
		String category=""+uid.charAt(0);
		
		String sql="insert into referee (uid, password, name, category) values (?,?,?,?)";
		jdbcTemplateObject.update(sql, uid, password, name, category);
		
		logger.info("SQL: "+sql);
		logger.info("uid:"+uid+", name:"+name);
	}
	
	public void deleteReferee(String uid){
		String sql="delete from referee where uid=?";
		jdbcTemplateObject.update(sql,uid);
		logger.info("SQL: "+sql);
		logger.info("uid:"+uid);
	}
	
	private String getRandomPassword(){
		Random rand= new Random();
		int passwordInt=rand.nextInt(99999999)+100000000;
		String password=Integer.toString(passwordInt).substring(1);//subString(1) is for delete the first "1" char, in order to get "00209976" sort of strings 
		return password;
	}
}