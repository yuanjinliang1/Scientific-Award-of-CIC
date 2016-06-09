package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import com.dicipulus.app.applicationModel.*;
import com.dicipulus.app.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EighthMajorContributorJdbc{
	private static final Logger logger=LoggerFactory.getLogger(EighthMajorContributorJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public EighthMajorContributor getEighthMajorContributor(int idOfEighthForm){
		String sql="select * from major_contributor where idOfEighthForm=?;";
		EighthMajorContributor eighthMajorContributor= 
				jdbcTemplateObject.queryForObject(sql, new Object[]{idOfEighthForm},BeanPropertyRowMapper.newInstance(EighthMajorContributor.class));
		return eighthMajorContributor;
	}
	
	public List<EighthMajorContributor> getEighthMajorContributors(String applierUid){
		String sql="select * from major_contributor where applierUid=?;";
		List<EighthMajorContributor> eighthMajorContributors= 
				jdbcTemplateObject.query(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(EighthMajorContributor.class));
		logger.info(sql);
		return eighthMajorContributors;
	}
	
	public void createEighthMajorContributor(String applierUid,int rankOfContributor){
		String sql="insert into major_contributor (applierUid,nameOfContributor,rankOfContributor,contributionOfContributor,formerRewardOfCIC) values(?,'Î´ÃüÃûµ¥Î»',?,?,?);";
		String contributionOfContributor="";
		String formerRewardOfCIC="";
		jdbcTemplateObject.update(sql,applierUid,rankOfContributor,contributionOfContributor,formerRewardOfCIC);
		logger.info(sql);
	}
	
	public void updateEighthMajorContributor(EighthMajorContributor eighthForm){
		String sql="UPDATE `dicipulus`.`major_contributor` SET `nameOfContributor`=?, "
				+ "`genderOfContributor`=?, `rankOfContributor`=?, `nationalityOfContributor`=?, "
				+ "`isMemberOfCIC`=?, `memberIdOfCIc`=?, `birthdayOfContributor`=?, `birthPlaceOfContributor`=?, "
				+ "`nationOfContributor`=?, `citizenIdOfContributor`=?, `isReturnedFormOverseas`=?, `returnDate`=?, "
				+ "`technicalTitleOfContributor`=?, `highestEducationOfContributor`=?, `highestDegreeOfContributor`=?, "
				+ "`universityOfContributor`=?, `graduateDateOfContributor`=?, `majorOfContributor`=?, `emailOfContributor`=?, "
				+ "`officePhoneOfContributor`=?, `mobileOfContributor`=?, `addressOfContributor`=?, `zipCodeOfContributor`=?, "
				+ "`workUnitOfContributor`=?, `administrativePositionOfContributor`=?, `subWorkUnitOfContributor`=?, "
				+ "`policitalPartyOfContributor`=?, `completeUnitOfContributor`=?, `locationOfContributor`=?, `typeOfUnit`=?, "
				+ "`startDateOfParticipation`=?, `endDateOfParticipation`=?, `contributionOfContributor`=?, `formerRewardOfCIC`=? "
				+ "WHERE `idOfEighthForm`=?;";
		jdbcTemplateObject.update(sql,eighthForm.getNameOfContributor(),
				eighthForm.getGenderOfContributor(),eighthForm.getRankOfContributor(),eighthForm.getNationalityOfContributor(),
				eighthForm.getIsMemberOfCIC(),eighthForm.getMemberIdOfCIc(),eighthForm.getBirthdayOfContributor(),eighthForm.getBirthPlaceOfContributor(),
				eighthForm.getNationOfContributor(),eighthForm.getCitizenIdOfContributor(),eighthForm.getIsReturnedFormOverseas(),eighthForm.getReturnDate(),
				eighthForm.getTechnicalTitleOfContributor(),eighthForm.getHighestEducationOfContributor(),eighthForm.getHighestDegreeOfContributor(),
				eighthForm.getUniversityOfContributor(),eighthForm.getGraduateDateOfContributor(),eighthForm.getMajorOfContributor(),eighthForm.getEmailOfContributor(),
				eighthForm.getOfficePhoneOfContributor(),eighthForm.getMobileOfContributor(),eighthForm.getAddressOfContributor(),eighthForm.getZipCodeOfContributor(),
				eighthForm.getWorkUnitOfContributor(),eighthForm.getAdministrativePositionOfContributor(),eighthForm.getSubWorkUnitOfContributor(),
				eighthForm.getPolicitalPartyOfContributor(),eighthForm.getCompleteUnitOfContributor(),eighthForm.getLocationOfContributor(),eighthForm.getTypeOfUnit(),
				eighthForm.getStartDateOfParticipation(),eighthForm.getEndDateOfParticipation(),eighthForm.getContributionOfContributor(),eighthForm.getFormerRewardOfCIC(),
				eighthForm.getIdOfEighthForm());
		logger.info(sql);
	}
	
	public void deleteEighthMajorContributor(int idOfEighthForm){
		String sql="DELETE FROM `dicipulus`.`major_contributor` WHERE `idOfEighthForm`=?;";
		jdbcTemplateObject.update(sql,idOfEighthForm);
		logger.info(sql);
	}
}