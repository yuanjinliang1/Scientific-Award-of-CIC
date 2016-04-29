package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.applicationModel.FifthObjectiveEvaluation;
@Repository
public class FifthObjectiveEvaluationJdbc {
	private static final Logger logger=LoggerFactory.getLogger(FifthObjectiveEvaluationJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void updateFifthObjective(FifthObjectiveEvaluation fifthObjectiveEvaluation,String applierUid){
		String sql="update project_major set objectiveEvaluation=? where applierUid=?";
		jdbcTemplate.update(sql, new Object[] {fifthObjectiveEvaluation.getObjectiveEvaluation(),applierUid});
	}
	
	public FifthObjectiveEvaluation getFifthObjectiveEvaluation(String applierUid){
		String sql="select objectiveEvaluation from project_major where applierUid=?";
		FifthObjectiveEvaluation fifthObjectiveEvaluation=new FifthObjectiveEvaluation();
		String temp=jdbcTemplate.queryForObject(sql, new Object[] {applierUid}, java.lang.String.class);
		fifthObjectiveEvaluation.setObjectiveEvaluation(temp);
		return fifthObjectiveEvaluation;
	}
}
