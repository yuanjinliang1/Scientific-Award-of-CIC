package com.dicipulus.app.JDBC;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dicipulus.app.applicationModel.SixthEconomicAndSocialBenefit;

public class SixthEconomicAndSocialBenefitJdbc {
	private static final Logger logger=LoggerFactory.getLogger(ThirdProjectBriefIntroductionJdbc.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	public void updateSixthEconomicAndSocialBenefitJdbc(SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit,String applierUid){
		String sql="update project_major set "
				+ "firstYear=?, secondYear=?,thirdYear=?,"
				+ "appendSales1=?,appendSales2=?,appendSales3=?,"
				+ "appendProfit1=?,appendProfit2=?,appendProfit3=?,"
				+ "appendSalesByOtherUnit1=?,appendSalesByOtherUnit2=?,appendSalesByOtherUnit3=?,"
				+ "appendProfitByOtherUnit1=?,appendProfitByOtherUnit2=?,appendProfitByOtherUnit3=?,"
				+ "mainEconomicProfitIntroduction=?,otherEconomicProfitIntroduction=?,socialBenefit=?,"
				+ "totalOfAppendSales=?,totalOfAppendProfit=?,totalOfAppendSalesByOtherUnit=?,totalOfAppendProfitByOtherUnit=? "
				+ "where applierUid=?";
		jdbcTemplate.update(sql, new Object[] {sixthEconomicAndSocialBenefit.getFirstYear(),sixthEconomicAndSocialBenefit.getSecondYear(),sixthEconomicAndSocialBenefit.getThirdYear(),
				sixthEconomicAndSocialBenefit.getAppendSales1(),sixthEconomicAndSocialBenefit.getAppendSales2(),sixthEconomicAndSocialBenefit.getAppendSales3(),
				sixthEconomicAndSocialBenefit.getAppendProfit1(),sixthEconomicAndSocialBenefit.getAppendProfit2(),sixthEconomicAndSocialBenefit.getAppendProfit3(),
				sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit1(),sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit2(),sixthEconomicAndSocialBenefit.getAppendSalesByOtherUnit3(),
				sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit1(),sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit2(),sixthEconomicAndSocialBenefit.getAppendProfitByOtherUnit3(),
				sixthEconomicAndSocialBenefit.getMainEconomicProfitIntroduction(),sixthEconomicAndSocialBenefit.getOtherEconomicProfitIntroduction(),sixthEconomicAndSocialBenefit.getSocialBenefit(),
				sixthEconomicAndSocialBenefit.getTotalOfAppendSales(),sixthEconomicAndSocialBenefit.getTotalOfAppendProfit(),sixthEconomicAndSocialBenefit.getTotalOfAppendSalesByOtherUnit(),sixthEconomicAndSocialBenefit.getTotalOfAppendProfitByOtherUnit(),
				applierUid});
		logger.info("updateSixthEconomicAndSocialBenefitJdbc");
	}
	
	public SixthEconomicAndSocialBenefit getSixthEconomicAndSocialBenefit(String applierUid){
		String sql="select * from project_major where applierUid=?";
		SixthEconomicAndSocialBenefit sixthEconomicAndSocialBenefit=jdbcTemplate.queryForObject(sql, new Object[] {applierUid}, BeanPropertyRowMapper.newInstance(SixthEconomicAndSocialBenefit.class));
		logger.info("getSixthEconomicAndSocialBenefit");
		return sixthEconomicAndSocialBenefit;
	}
}
