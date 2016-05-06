package com.dicipulus.app.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.applicationModel.SecondRefereeUnitOpinion;
import com.dicipulus.app.model.Application;
import com.dicipulus.app.model.Applications;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationJdbc {
	private static final Logger logger=LoggerFactory.getLogger(FirstProjectBasicSituationJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}

	public Applications getApplicationsByAdmin(String yearCreated){
		String sql="select * from project_major where yearCreated=?";
		List<Application> applicationList=jdbcTemplateObject.query(sql,new Object[]{yearCreated},BeanPropertyRowMapper.newInstance(Application.class));
		Applications applications=new Applications();
		applications.setApplicationList(applicationList);
		
		logger.info("SQL:"+sql);
		return applications;
	}
	
	public Applications getApplicationsByAdmin() throws DataAccessException{
		String sql="SELECT * FROM dicipulus.project_major "
				+ "join secondrefereeunitopinion on project_major.applierUid=secondrefereeunitopinion.applierUid "
				+ "join applier on project_major.applierUid=applier.uid;";
		List<Application> applicationList=jdbcTemplateObject.query(sql,BeanPropertyRowMapper.newInstance(Application.class));
		Applications applications=new Applications();
		applications.setApplicationList(applicationList);
		
		logger.info("SQL:"+sql);
		logger.info(applicationList.toString());
		return applications;
	}
	
	public Applications getApplicationsByReferee(String refereeUid){
		String sql="select * from project_major "
				+ "join secondrefereeunitopinion on project_major.applierUid=secondrefereeunitopinion.applierUid "
				+ "join applier on project_major.applierUid=applier.uid "
				+ "where owner=? ";
		List<Application> applicationList=jdbcTemplateObject.query(sql,new Object[]{refereeUid},BeanPropertyRowMapper.newInstance(Application.class));
		Applications applications=new Applications();
		applications.setApplicationList(applicationList);
		
		logger.info("SQL:"+sql);
		return applications;
	}

	public Applications getApplicationsByReferee(String  yearCreated,String refereeUid){
		String sql="select * from project_major where (yearCreated=? and owner=?)";
		List<Application> applicationList=jdbcTemplateObject.query(sql,new Object[]{yearCreated,refereeUid},BeanPropertyRowMapper.newInstance(Application.class));
		Applications applications=new Applications();
		applications.setApplicationList(applicationList);
		
		logger.info("SQL:"+sql);
		return applications;
	}
	
	public Application getApplicationByApplier(String applierUid){
		String sql="select * from project_major where applierUid=?";
		Application application=jdbcTemplateObject.queryForObject(sql,new Object[]{applierUid},BeanPropertyRowMapper.newInstance(Application.class));
		
		logger.info("SQL:"+sql);
		return application;
	}
	
	//single update
	public void setApplicationByAdmin(Application application) throws DataAccessException {
		String sql="update project_major set formalityExaminationResult=?, primaryExaminationResult=?, finalExaminationResult=?, commentOfAdmin=? where applierUid=?";
		jdbcTemplateObject.update(sql,application.getFormalityExaminationResult(),application.getPrimaryExaminationResult(),application.getFinalExaminationResult(),
				application.getCommentOfAdmin(),application.getApplierUid());
		logger.info("SQL:"+sql);
	}
	
	//not a good practice for multiple update
	public void setApplicationsByAdmin(List<Application> applicationList) throws DataAccessException {
		for(Application application:applicationList){
			String sql="update project_major set formalityExaminationResult=?, primaryExaminationResult=?, finalExaminationResult=?, commentOfAdmin=? where applierUid=?";
			jdbcTemplateObject.update(sql,application.getFormalityExaminationResult(),application.getPrimaryExaminationResult(),application.getFinalExaminationResult(),
					application.getCommentOfAdmin(),application.getApplierUid());
			logger.info("SQL:"+sql);
		}
	}
	
	public void setApplicationsByAdminAtomic(final Applications applications) throws SQLException {
		String sql="update project_major set formalityExaminationResult=?, primaryExaminationResult=?, finalExaminationResult=?, commentOfAdmin=? where applierUid=?";
		
		//keep SQL transaction atomic
		BatchPreparedStatementSetter pss=new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
			    Application application = applications.getApplicationList().get(i);
			    ps.setString(1, application.getFormalityExaminationResult());
			    logger.info(application.getFormalityExaminationResult());
			    ps.setString(2, application.getPrimaryExaminationResult());
			    ps.setString(3, application.getFinalExaminationResult());
			    ps.setString(4, application.getCommentOfAdmin());
			    ps.setString(5, application.getApplierUid());
			}

			@Override
			public int getBatchSize() {
				logger.info(""+applications.getApplicationList().size());
			    return  applications.getApplicationList().size();
			}
		};
		int[] batchReturn= jdbcTemplateObject.batchUpdate(sql, pss);
		logger.info("batchReturn: "+batchReturn);
		
	}
}
