package com.dicipulus.app.JDBC;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
		//logger.info("uid:"+uid);
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
		//logger.info("uid:"+uid+", password:"+password);
	}
	
	public void createApplierForReferee(String refereeUid){
		List<Applier> appliers=getAppliers(refereeUid);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int yearTwoDigit=year-2000;
		String newUidString;
		if(appliers.isEmpty()){
			newUidString= refereeUid+yearTwoDigit+"001";
			logger.info("applier is empty!");
		}
		else{//newUid is one number bigger than the biggest applier uid. *note that applier uids under the same referee are continuous
			int newUidInt=0;
			for(Applier applier:appliers){
				if(Integer.parseInt(applier.getUid())>newUidInt){
					newUidInt=Integer.parseInt(applier.getUid());
				}
			}
			newUidInt++;
			newUidString=""+newUidInt;
			logger.info("newUidString;"+newUidString);
		}
		
		String sql="insert into applier (uid, password, name, owner,year) values(?,?,?,?,?)";
		String yearString=""+year;
		String password=getRandomPassword();
		
		jdbcTemplateObject.update(sql, newUidString, password, "ÏîÄ¿×é", refereeUid, yearString);
		logger.info("SQL: "+sql);
		//logger.info("uid:"+newUidString+", password:"+password+"refereeUid:"+refereeUid+"year:"+yearString);
	}
	
	private String getRandomPassword(){
		Random rand= new Random();
		int passwordInt=rand.nextInt(99999999)+100000000;
		String password=Integer.toString(passwordInt).substring(1);//subString(1) is for delete the first "1" char, in order to get "00209976" sort of strings 
		return password;
	}
	
	public void deleteApplier(String uid){
		String sql="delete from applier where uid=?";
		jdbcTemplateObject.update(sql,uid);
		logger.info("SQL: "+sql);
		//logger.info("uid:"+uid);
	}
	
	public void resetPassword(String uid){
		String newPassword=getRandomPassword();
		String sql="update applier set password=? where uid=?";
		jdbcTemplateObject.update(sql, newPassword ,uid);
		logger.info("SQL: "+sql);
		//logger.info("uid:"+uid+", password:"+newPassword);
	}
	
	public void changeName(String uid, String name){
		String sql="update applier set name=? where uid=?";
		jdbcTemplateObject.update(sql,name,uid);
		logger.info("SQL: "+sql);
		//logger.info("uid:"+uid+", new name:"+name);
	}
}