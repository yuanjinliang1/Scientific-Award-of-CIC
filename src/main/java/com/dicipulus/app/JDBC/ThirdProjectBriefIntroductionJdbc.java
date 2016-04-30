package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.applicationModel.ThirdProjectBriefIntroduction;

@Repository
public class ThirdProjectBriefIntroductionJdbc {
	private static final Logger logger=LoggerFactory.getLogger(ThirdProjectBriefIntroductionJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void updateThirdProjectBriefIntroduction(ThirdProjectBriefIntroduction thirdProjectBriefIntroduction,String applierUid){
		String sql="update project_major set projectBriefIntroduction=? where applierUid=?";
		jdbcTemplate.update(sql, new Object[] {thirdProjectBriefIntroduction.getBriefIntroduction(),applierUid});
	}
	
	public ThirdProjectBriefIntroduction getThirdProjectBriefIntroduction(String applierUid){
		String sql="select projectBriefIntroduction from project_major where applierUid=?";		
		ThirdProjectBriefIntroduction thirdProjectBriefIntroduction=new ThirdProjectBriefIntroduction();
		String briefIntroduction=jdbcTemplate.queryForObject(sql,new Object[] {applierUid},java.lang.String.class);
		thirdProjectBriefIntroduction.setBriefIntroduction(briefIntroduction);
		logger.info(thirdProjectBriefIntroduction.getBriefIntroduction()+"!!!");
		return thirdProjectBriefIntroduction;
	}
}
