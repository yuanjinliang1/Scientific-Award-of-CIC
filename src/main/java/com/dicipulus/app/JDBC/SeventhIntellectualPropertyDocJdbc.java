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
public class SeventhIntellectualPropertyDocJdbc{
	private static final Logger logger=LoggerFactory.getLogger(SeventhIntellectualPropertyDocJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public SeventhIntellectualPropertyDoc getSeventhIntellectualPropertyDoc(int idOfSeventhIPForm){
		String sql="select * from intellectual_property_doc where idOfSeventhIPForm=?;";
		SeventhIntellectualPropertyDoc seventhIPForm= 
				jdbcTemplateObject.queryForObject(sql, new Object[]{idOfSeventhIPForm},BeanPropertyRowMapper.newInstance(SeventhIntellectualPropertyDoc.class));
		return seventhIPForm;
	}
	
	public List<SeventhIntellectualPropertyDoc> getSeventhIntellectualPropertyDocs(String applierUid){
		String sql="select * from intellectual_property_doc where applierUid=? order by rankOfIP;";
		List<SeventhIntellectualPropertyDoc> seventhIPForm= 
				jdbcTemplateObject.query(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(SeventhIntellectualPropertyDoc.class));
		logger.info(sql);
		return seventhIPForm;
	}
	////
	public void createSeventhIntellectualPropertyDoc(String applierUid,int rankOfIP){
		String sql="insert into intellectual_property_doc (applierUid,rankOfIP,nameOfIp) values(?,?,?);";
		jdbcTemplateObject.update(sql,applierUid,rankOfIP,"新建知识产权证明");
		logger.info(sql);
	}
	
	public void updateSeventhIntellectualPropertyDoc(SeventhIntellectualPropertyDoc seventhIPForm){
		String sql="UPDATE `dicipulus`.`intellectual_property_doc` SET `rankOfIP`=?, `typeOfIP`=?, `nameOfIP`=?, "
				+ "`locationOfIP`=?, `authorizationCodeOfIP`=?, `authorizationDateOfIP`=?, `certificateNumberOfIP`=?, "
				+ "`holderOfIP`=?, `inventorOfIP`=?, `isValidIP`=? "
				+ "WHERE `idOfSeventhIPForm`=?;";
		jdbcTemplateObject.update(sql,seventhIPForm.getRankOfIP(),seventhIPForm.getTypeOfIP(),
				seventhIPForm.getNameOfIP(),seventhIPForm.getLocationOfIP(),seventhIPForm.getAuthorizationCodeOfIP(),seventhIPForm.getAuthorizationDateOfIP(),
				seventhIPForm.getCertificateNumberOfIP(),seventhIPForm.getHolderOfIP(),seventhIPForm.getInventorOfIP(),seventhIPForm.getIsValidIP(),
				seventhIPForm.getIdOfSeventhIPForm());
		logger.info(sql);
	}
	
	public void deleteSeventhIntellectualPropertyDoc(int idOfSeventhIPForm){
		String sql="DELETE FROM `dicipulus`.`intellectual_property_doc` WHERE `idOfSeventhIPForm`=?;";
		jdbcTemplateObject.update(sql,idOfSeventhIPForm);
		logger.info(sql);
	}
}