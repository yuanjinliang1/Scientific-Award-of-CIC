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
		String sql="select c.*,dicipulus.major_org_contributor.addressOfOrg from "
				+ "(select b.*,dicipulus.secondrefereeunitopinion.postAddress, dicipulus.secondrefereeunitopinion.referingScienceTechnologyAwardRank from "
				+ "(select a.*,dicipulus.applier.applicationType from "
				+ "(select dicipulus.project_major.applierUid, dicipulus.project_major.yearCreated, dicipulus.project_major.projectStatus, dicipulus.project_major.commentOfAdmin, dicipulus.project_major.formalityExaminationResult,dicipulus.project_major.primaryExaminationResult,dicipulus.project_major.finalExaminationResult,dicipulus.project_major.projectName ,dicipulus.project_major.applierContactName,dicipulus.project_major.applierContactPhone,dicipulus.project_major.applierContactEmail,dicipulus.project_major.refereeContactName,dicipulus.project_major.refereeContactPhone,dicipulus.project_major.refereeString from dicipulus.project_major where dicipulus.project_major.yearCreated= ? ) a,"
				+ "dicipulus.applier where a.applierUid=dicipulus.applier.Uid) b,dicipulus.secondrefereeunitopinion where b.applierUid=dicipulus.secondrefereeunitopinion.applierUid) c,dicipulus.major_org_contributor where dicipulus.major_org_contributor.rankOfOrg='1' group by c.applierUid";
		List<Excel> excel=jdbcTemplate.query(sql,new Object[]{year} ,BeanPropertyRowMapper.newInstance(Excel.class));
		for(Excel ex:excel){
			System.out.print(ex.getAddressOfOrg()+"#$%#$%");
		}
		return excel;
	}
}
