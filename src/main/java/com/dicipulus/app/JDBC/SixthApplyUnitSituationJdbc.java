package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.applicationModel.SixthApplyUnitSituation;
@Repository
public class SixthApplyUnitSituationJdbc {
	private static final Logger logger=LoggerFactory.getLogger(SixthApplyUnitSituationJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	
	public void createSixthApplyUnitSituation(String applierUid){
		String sql="insert into apply_unit_situation(applierUid) values(?)";
		jdbcTemplate.update(sql, new Object[] {applierUid});
		logger.info("insert success!!");
	}
	
	public List<SixthApplyUnitSituation> getAllSixthApplyUnitSituation(String applierUid){
		String sql="select * from apply_unit_situation where applierUid=?";
		List<SixthApplyUnitSituation> list=jdbcTemplate.query(sql,new Object[] {applierUid},BeanPropertyRowMapper.newInstance(SixthApplyUnitSituation.class));
		
		logger.info("getAllSixthAppliedUnitSituation success!");
		return list;
	}
	
	public SixthApplyUnitSituation getSixthApplyUnitSituation(int idOfSixthAppliedUnitSituation){
		String sql="select * from apply_unit_situation where idOfApplyUnit=?";
		SixthApplyUnitSituation sixthApplyUnitSituation=jdbcTemplate.queryForObject(sql, new Object[] {idOfSixthAppliedUnitSituation},BeanPropertyRowMapper.newInstance(SixthApplyUnitSituation.class));
		return sixthApplyUnitSituation;
		
	}
	public void updateSixthApplyUnitSituation(SixthApplyUnitSituation sixthApplyUnitSituation,int idOfApplyUnit){
		String sql="update apply_unit_situation set applyTechnology=?,startDate=?,contactAndPhoneNumber=?,applySituation=? where idOfApplyUnit=?";
		jdbcTemplate.update(sql, new Object[] {sixthApplyUnitSituation.getApplyTechnology(),sixthApplyUnitSituation.getStartDate(),sixthApplyUnitSituation.getContactAndPhoneNumber(),sixthApplyUnitSituation.getApplySituation(),idOfApplyUnit});
		logger.info(sql+" success!");
	}
	
	public void deleteSixthAppliedUnitSituation(int idOfSixthAppliedUnitSituation){
		logger.info(String.valueOf(idOfSixthAppliedUnitSituation));
		String sql="delete from apply_unit_situation where idOfApplyUnit=?";
		jdbcTemplate.update(sql, new Object[] {idOfSixthAppliedUnitSituation});
		logger.info(sql+" finish!");
	}
}
