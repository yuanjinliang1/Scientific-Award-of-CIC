package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dicipulus.app.form.NaturalScienceForm;
import com.dicipulus.app.form.Unit;

public class ProjectJdbc {
	private static final Logger logger=LoggerFactory.getLogger(ProjectJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public void updateProject(NaturalScienceForm form){//修改表project，把主键改成name，不用id
		String sql="insert into project values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
	}
	public void updateMainFinishedUnit(Unit[] unit,int projectId){
		for(int i=0;i<unit.length;i++){
			String sql="insert into unit (rank,legalRepresentative,place,fax,zipCode,isMemberOfCic,address,"
				+ "identifierOfCic,contact,moblie,email,contrubution,finishUnitDeclarations,belongToProject) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplateObject.update(sql, new Object[] {unit[i].getRank(),unit[i].getLegalRepresentative(),
				unit[i].getPlace(),unit[i].getFax(),unit[i].getZipCode(),unit[i].isMemberOfCic(),unit[i].getAddress(),
				unit[i].getIdentifierOfCic(),unit[i].getContact(),unit[i].getMoblie(),unit[i].getEmail(),unit[i].getContrubution(),
				unit[i].getFinishUnitDeclarations(),projectId});
		}
		
	}
}
