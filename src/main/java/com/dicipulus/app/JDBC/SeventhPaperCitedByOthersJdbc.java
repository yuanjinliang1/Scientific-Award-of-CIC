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
public class SeventhPaperCitedByOthersJdbc{
	private static final Logger logger=LoggerFactory.getLogger(SeventhPaperCitedByOthersJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public SeventhPaperCitedByOthers getSeventhPaperCitedByOthers(int idOfSeventhPaperForm){
		String sql="select * from paper_cited_by_others where idOfSeventhPaperForm=?;";
		SeventhPaperCitedByOthers seventhPaperForm= 
				jdbcTemplateObject.queryForObject(sql, new Object[]{idOfSeventhPaperForm},BeanPropertyRowMapper.newInstance(SeventhPaperCitedByOthers.class));
		return seventhPaperForm;
	}
	
	public List<SeventhPaperCitedByOthers> getSeventhPaperCitedByOtherss(String applierUid){
		String sql="select * from paper_cited_by_others where applierUid=? order by rankOfPaper;";
		List<SeventhPaperCitedByOthers> seventhPaperForm= 
				jdbcTemplateObject.query(sql, new Object[]{applierUid},BeanPropertyRowMapper.newInstance(SeventhPaperCitedByOthers.class));
		logger.info(sql);
		return seventhPaperForm;
	}
	////
	public void createSeventhPaperCitedByOthers(String applierUid,int rankOfPaper){
		String sql="insert into paper_cited_by_others (applierUid,rankOfPaper,titleAndAuthorOfPaper) values(?,?,?);";
		jdbcTemplateObject.update(sql,applierUid,rankOfPaper,"新建引文");
		logger.info(sql);
	}
	
	public void updateSeventhPaperCitedByOthers(SeventhPaperCitedByOthers seventhPaperForm){
		String sql="UPDATE `dicipulus`.`paper_cited_by_others` SET `doiOfPaper`=?, `titleAndAuthorOfPaper`=?,"
				+ " `journalAndIF`=?, `publishDate`=? "
				+ "WHERE `idOfSeventhPaperForm`=?;";
		jdbcTemplateObject.update(sql,seventhPaperForm.getDoiOfPaper(),seventhPaperForm.getTitleAndAuthorOfPaper(),
				seventhPaperForm.getJournalAndIF(),seventhPaperForm.getPublishDate(),
				seventhPaperForm.getIdOfSeventhPaperForm());
		logger.info(sql);
	}
	
	public void deleteSeventhPaperCitedByOthers(int idOfSeventhPaperForm){
		String sql="DELETE FROM `dicipulus`.`paper_cited_by_others` WHERE `idOfSeventhPaperForm`=?;";
		jdbcTemplateObject.update(sql,idOfSeventhPaperForm);
		logger.info(sql);
	}
}