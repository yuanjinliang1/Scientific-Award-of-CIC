package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.FirstProjectBasicSituation;
import com.dicipulus.app.applicationModel.NinethMajorOrgContributor;
import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Referee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NinethMajorOrgContributorJdbc{
	private static final Logger logger=LoggerFactory.getLogger(NinethMajorOrgContributorJdbc.class);
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
		
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public NinethMajorOrgContributor getNinethMajorOrgContributor(int idOfNinethForm){
		String sql="select * from major_org_contributor where idOfNinethForm=?;";
		NinethMajorOrgContributor ninethMajorOrgContributor= 
				jdbcTemplateObject.queryForObject(sql, new Object[]{idOfNinethForm},BeanPropertyRowMapper.newInstance(NinethMajorOrgContributor.class));
		return ninethMajorOrgContributor;
	}
	
	public List<NinethMajorOrgContributor> getNinethMajorOrgContributors(String applierUid){
		String sql="select * from major_org_contributor where applierUid=?;";
		List<NinethMajorOrgContributor> ninethMajorContributors= 
				jdbcTemplateObject.query(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(NinethMajorOrgContributor.class));
		logger.info(sql);
		return ninethMajorContributors;
	}
	
	public void createNinethMajorOrgContributor(String applierUid,int rankOfOrg){
		String sql="insert into major_org_contributor (applierUid,nameOfOrg,rankOfOrg,contributionToProject) values(?,'未命名单位',?,?);";
		jdbcTemplateObject.update(sql,applierUid,rankOfOrg,"不超过600字。");
		logger.info(sql);
	}
	
	public void updateNinethMajorOrgContributor(int idOfNinethForm,NinethMajorOrgContributor ninethForm){
		String sql="UPDATE `dicipulus`.`major_org_contributor` SET `nameOfOrg`=?, `rankOfOrg`=?, "
				+ "`legalRepresentative`=?, `locationOfOrg`=?, `typeOfOrg`=?, `faxOfOrg`=?, "
				+ "`zipCodeOfOrg`=?, `isOrgMemberOfCIC`=?, `OrgMemberIDOfCIC`=?, `addressOfOrg`=?, "
				+ "`contactNameOfOrg`=?, `contactPhoneOfOrg`=?, `mobileOfOrg`=?, `emailOfOrg`=?, "
				+ "`contributionToProject`=? "
				+ "WHERE `idOfNinethForm`=?;";
		jdbcTemplateObject.update(sql,ninethForm.getNameOfOrg(),ninethForm.getRankOfOrg(),
				ninethForm.getLegalRepresentative(),ninethForm.getLocationOfOrg(),ninethForm.getTypeOfOrg(),ninethForm.getFaxOfOrg(),
				ninethForm.getZipCodeOfOrg(),ninethForm.getIsOrgMemberOfCIC(),ninethForm.getOrgMemberIDOfCIC(),ninethForm.getAddressOfOrg(),
				ninethForm.getContactNameOfOrg(),ninethForm.getContactPhoneOfOrg(),ninethForm.getMobileOfOrg(),ninethForm.getEmailOfOrg(),
				ninethForm.getContributionToProject(),ninethForm.getIdOfNinethForm());
		logger.info(sql);
		logger.info(ninethForm.toString());
	}
}