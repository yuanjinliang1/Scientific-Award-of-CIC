package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinthMajorOrgContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NinthMajorOrgContributorJdbc{
	private static final Logger logger=LoggerFactory.getLogger(NinthMajorOrgContributorJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public NinthMajorOrgContributor getNinthMajorOrgContributor(int idOfNinthForm){
		String sql="select * from major_org_contributor where idOfNinthForm=?;";
		NinthMajorOrgContributor ninthMajorOrgContributor= 
				jdbcTemplateObject.queryForObject(sql, new Object[]{idOfNinthForm},BeanPropertyRowMapper.newInstance(NinthMajorOrgContributor.class));
		return ninthMajorOrgContributor;
	}
	
	public List<NinthMajorOrgContributor> getNinthMajorOrgContributors(String applierUid){
		String sql="select * from major_org_contributor where applierUid=?;";
		List<NinthMajorOrgContributor> ninthMajorContributors= 
				jdbcTemplateObject.query(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(NinthMajorOrgContributor.class));
		logger.info(sql);
		return ninthMajorContributors;
	}
	
	public int createNinthMajorOrgContributor(String applierUid,int rankOfOrg){
		String sql="insert into major_org_contributor (applierUid,nameOfOrg,rankOfOrg,contributionToProject) values(?,'Î´ÃüÃûµ¥Î»',?,?);";
		jdbcTemplateObject.update(sql,applierUid,rankOfOrg,"");
		logger.info(sql);
		
		String sql2="select idOfNinthForm from major_org_contributor where(applierUid=? and rankOfOrg=?)";
		int idOfNinthForm= jdbcTemplateObject.queryForObject(sql2, new Object[]{applierUid,rankOfOrg},Integer.class);
		logger.info(""+idOfNinthForm);
		return idOfNinthForm;
	}
	
	public void createNinthMajorOrgContributor(String applierUid,int rankOfOrg,String nameOfOrg){
		String sql="insert into major_org_contributor (applierUid,nameOfOrg,rankOfOrg,contributionToProject) values(?,?,?,?);";
		jdbcTemplateObject.update(sql,applierUid,nameOfOrg,rankOfOrg,"");
		logger.info(sql);
	}
	
	public void updateNinthMajorOrgContributor(NinthMajorOrgContributor ninthForm){
		String sql="UPDATE `dicipulus`.`major_org_contributor` SET `nameOfOrg`=?, `rankOfOrg`=?, "
				+ "`legalRepresentative`=?, `locationOfOrg`=?, `typeOfOrg`=?, `faxOfOrg`=?, "
				+ "`zipCodeOfOrg`=?, `isOrgMemberOfCIC`=?, `orgMemberIDOfCIC`=?, `addressOfOrg`=?, "
				+ "`contactNameOfOrg`=?, `contactPhoneOfOrg`=?, `mobileOfOrg`=?, `emailOfOrg`=?, "
				+ "`contributionToProject`=? "
				+ "WHERE `idOfNinthForm`=?;";
		jdbcTemplateObject.update(sql,ninthForm.getNameOfOrg(),ninthForm.getRankOfOrg(),
				ninthForm.getLegalRepresentative(),ninthForm.getLocationOfOrg(),ninthForm.getTypeOfOrg(),ninthForm.getFaxOfOrg(),
				ninthForm.getZipCodeOfOrg(),ninthForm.getIsOrgMemberOfCIC(),ninthForm.getorgMemberIDOfCIC(),ninthForm.getAddressOfOrg(),
				ninthForm.getContactNameOfOrg(),ninthForm.getContactPhoneOfOrg(),ninthForm.getMobileOfOrg(),ninthForm.getEmailOfOrg(),
				ninthForm.getContributionToProject(),ninthForm.getIdOfNinthForm());
		logger.info(sql);
		logger.info(ninthForm.toString());
	}
	
	public void deleteNinthMajorOrgContributor(int idOfNinthForm){
		String sql="DELETE FROM `dicipulus`.`major_org_contributor` WHERE `idOfNinthForm`=?;";
		jdbcTemplateObject.update(sql,idOfNinthForm);
		logger.info(sql);
	}
}