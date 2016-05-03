package com.dicipulus.app.JDBC;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dicipulus.app.applicationModel.SixthPaperMonographNT;
@Repository
public class SixthPaperMonographNTJdbc {
	private static final Logger logger=LoggerFactory.getLogger(ThirdProjectBriefIntroductionJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public void createSixthPaperMonographNT(String applierUid){
		String sql="insert into paper_monograph(applierUid) values(?)";
		jdbcTemplate.update(sql, new Object[] {applierUid});
		logger.info( sql+" createSixthPaperMonographNT");
	}
	
	public List<SixthPaperMonographNT> getAllSixthPaperMonographNT(String applierUid){
		String sql="select * from paper_monograph where applierUid=?";
		List<SixthPaperMonographNT> list=jdbcTemplate.query(sql, new Object[] {applierUid},BeanPropertyRowMapper.newInstance(SixthPaperMonographNT.class));
		logger.info("getAllSixthPaperMonographNT");
		return list;
	}
	
	public SixthPaperMonographNT getSixthPaperMonographNT(int idOfPaperMonograph){
		String sql="select * from paper_monograph where idOfPaperMonograph=?";
		SixthPaperMonographNT sixthPaperMonographNT=jdbcTemplate.queryForObject(sql, new Object[] {idOfPaperMonograph}, BeanPropertyRowMapper.newInstance(SixthPaperMonographNT.class));
		logger.info("getSixthPaperMonographNT");
		return sixthPaperMonographNT;
	}
	
	public void updateSixthPaperMonographNT(SixthPaperMonographNT sixthPaperMonographNT,int idOfPaperMonograph){
		String sql="update paper_monograph set name=?,influenceFactor=?,yearPage=?,publishTime=?,correspondenceAuthor=?,"
				+ "firstAuthor=?,domesticAuthor=?,referenceBySCI=?,totalReference=?,intellectualRightBelongToNation=? ,representativePaperMonograph=?"
				+ "where idOfPaperMonograph=?";
		jdbcTemplate.update(sql, new Object[] {sixthPaperMonographNT.getName(),sixthPaperMonographNT.getInfluenceFactor(),sixthPaperMonographNT.getYearPage(),sixthPaperMonographNT.getPublishTime(),sixthPaperMonographNT.getCorrespondenceAuthor(),
				sixthPaperMonographNT.getFirstAuthor(),sixthPaperMonographNT.getDomesticAuthor(),sixthPaperMonographNT.getReferenceBySCI(),sixthPaperMonographNT.getTotalReference(),sixthPaperMonographNT.getIntellectualRightBelongToNation(),sixthPaperMonographNT.getRepresentativePaperMonograph(),
				idOfPaperMonograph});
		logger.info("updateSixthPaperMonographNT");
	}
	
	public void deleteSixthPaperMonographNT(int idOfPaperMonograph){
		String sql="delete from paper_monograph where idOfPaperMonograph=?";
		jdbcTemplate.update(sql, new Object[] {idOfPaperMonograph});
		logger.info("deleteSixthPaperMonographNT");
	}
}
