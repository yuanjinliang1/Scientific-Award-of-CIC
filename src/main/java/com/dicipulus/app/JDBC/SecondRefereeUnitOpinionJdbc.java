package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;

@Repository
public class SecondRefereeUnitOpinionJdbc {
	private static final Logger logger=LoggerFactory.getLogger(SecondRefereeUnitOpinionJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject=new JdbcTemplate(dataSource);
	}
	
	public void updateSecondRefereeUnitOpinion(SecondRefereeUnitOpinion secondRefereeUnitOpinion,String applierUid){
		String sql="update secondrefereeunitopinion set refereeUnitName=?, postAddress=?,zipCode=?,contact=?,phoneNumber=?,email=?,fax=?,recommendOpinion=?,referingScienceTechnologyAwardRank=? where applierUid=? ";
		jdbcTemplateObject.update(sql, new Object[] {secondRefereeUnitOpinion.getRefereeUnitName(),secondRefereeUnitOpinion.getPostAddress(),secondRefereeUnitOpinion.getZipCode(),
				secondRefereeUnitOpinion.getContact(),secondRefereeUnitOpinion.getPhoneNumber(),secondRefereeUnitOpinion.getEmail(),secondRefereeUnitOpinion.getFax(),
				secondRefereeUnitOpinion.getRecommendOpinion(),secondRefereeUnitOpinion.getReferingScienceTechnologyAwardRank(),applierUid});
		logger.info(sql);
	}
	public SecondRefereeUnitOpinion getSecondRefereeUnitOpinion(String applierUid){
		String sql="select * from secondrefereeunitopinion where applierUid=?";
		SecondRefereeUnitOpinion secondRefereeUnitOpinion=jdbcTemplateObject.queryForObject(sql, new Object[] {applierUid},BeanPropertyRowMapper.newInstance(SecondRefereeUnitOpinion.class));
		logger.info(sql);
		return secondRefereeUnitOpinion;
	}
}
