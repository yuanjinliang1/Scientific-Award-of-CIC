package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dicipulus.app.applicationModel.Excel;

public class ManageExcelJdbc {
	private static final Logger logger=LoggerFactory.getLogger(ManageExcelJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplate= new JdbcTemplate(dataSource);
	}
	public List<Excel> getExcelByYear(int year){
		String sql="select b.*,secondrefereeunitopinion.postAddress, secondrefereeunitopinion.referingScienceTechnologyAwardRank "
				+ "from (select a.*,applier.applicationType from "
				+ "(select project_major.applierUid,project_major.yearCreated,project_major.projectStatus,project_major.formalityExaminationResult,project_major.primaryExaminationResult,project_major.finalExaminationResult,project_major.projectName ,project_major.applierContactName,project_major.applierContactPhone,project_major.applierContactEmail,project_major.refereeContactName,project_major.refereeContactPhone,project_major.refereeString from "
				+ "project_major where yearCreated= ? ) a,applier where a.applierUid=applier.Uid) b,secondrefereeunitopinion where b.applierUid=secondrefereeunitopinion.applierUid";
		List<Excel> excel=jdbcTemplate.query(sql,new Object[]{year} ,BeanPropertyRowMapper.newInstance(Excel.class));
		logger.info(excel.toString());
		return excel;
	}
}
