package com.dicipulus.app.JDBC;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.DAO.RefereeDao;
import com.dicipulus.app.model.*;

@Repository
public class RefereeJdbc implements RefereeDao {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public Referee getRefereeByUid(String uid){
		String sql="select * from referee where uid = ?";
		
		Referee referee=jdbcTemplateObject.queryForObject(sql, new Object[]{uid}, BeanPropertyRowMapper.newInstance(Referee.class));
		
		loadApplier(referee);
		System.out.println(sql);
		return referee;
	}
	
	public void changePassword(String uid,String password){
		String sql="update Referee set password=? where uid=?";
		
		jdbcTemplateObject.update(sql, password,uid);
		System.out.println(sql);
	}
	
	public void loadApplier(Referee referee){
		Map<String, Object> params =new HashMap<>();
		params.put("uid",referee.getUid());
		String sql="select applier.id from applier where owner=?";
		List<applierJdbc> appliers=jdbcTemplateObject.query(sql, referee.getUid(),);
	}
}