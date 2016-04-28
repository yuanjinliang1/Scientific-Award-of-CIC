package com.dicipulus.app.applicationModel;

import java.util.Date;
import java.util.List;

import com.dicipulus.app.model.*;

/*
 * 科技进步奖， 一、项目基本情况
 */

public class FirstProjectBasicSituationTA {

	protected String applierUid;// 项目ID

	/*
	 * 表的内容
	 */
	protected int yearCreated;//创建年份
	
	protected String refereeString;// 推荐单位或推荐专家
	protected String projectName;// 项目名称
	//protected List<String> majorContributorNames;// 主要完成人
	//protected List<String> majorContributingOrgNames;// 主要完成单位
	protected String secretLevel = "非密";// 项目密级
	protected String subjectCategoryName1;// 学科分类名称排序
	protected String subjectCategoryId1;// 学科分类id排序
	protected String subjectCategoryName2;// 学科分类名称排序
	protected String subjectCategoryId2;// 学科分类id排序
	protected String subjectCategoryName3;// 学科分类名称排序
	protected String subjectCategoryId3;// 学科分类id排序
	/*
	protected String economicField;// 所属国民经济行业
	protected String nationalFocusField;// 所属国家重点发展领域
	protected String taskSource;// 任务来源
	protected String NameAndCodeOfPlansOrFundations;// 具体计划、基金的名称和编号
	protected String technicalReportNumber;// 已呈交的科技报告编号
	protected int numOfInventionPatent;// 授权发明专利项数
	protected int numOfOtherIntellectualProperty;// 授权的其它知识产权项数
	protected Date startDate;// 项目开始时间
	protected Date finishDate;// 项目完成时间
	protected String refereeContactName;// 推荐单位联系人，包括姓名、电话、邮箱等
	protected String refereeContactPhone;
	protected String refereeContactEmail;
	protected String applierContactName;// 项目联系人，包括姓名、电话。邮箱等
	protected String applierContactPhone;
	protected String applierContactEmail;
	*/
	public String getApplierUid() {
		return applierUid;
	}
	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}
	public int getYearCreated() {
		return yearCreated;
	}
	public void setYearCreated(int yearCreated) {
		this.yearCreated = yearCreated;
	}
	public String getRefereeString() {
		return refereeString;
	}
	public void setRefereeString(String refereeString) {
		this.refereeString = refereeString;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSecretLevel() {
		return secretLevel;
	}
	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}
	public String getSubjectCategoryName1() {
		return subjectCategoryName1;
	}
	public void setSubjectCategoryName1(String subjectCategoryName1) {
		this.subjectCategoryName1 = subjectCategoryName1;
	}
	public String getSubjectCategoryId1() {
		return subjectCategoryId1;
	}
	public void setSubjectCategoryId1(String subjectCategoryId1) {
		this.subjectCategoryId1 = subjectCategoryId1;
	}
	public String getSubjectCategoryName2() {
		return subjectCategoryName2;
	}
	public void setSubjectCategoryName2(String subjectCategoryName2) {
		this.subjectCategoryName2 = subjectCategoryName2;
	}
	public String getSubjectCategoryId2() {
		return subjectCategoryId2;
	}
	public void setSubjectCategoryId2(String subjectCategoryId2) {
		this.subjectCategoryId2 = subjectCategoryId2;
	}
	public String getSubjectCategoryName3() {
		return subjectCategoryName3;
	}
	public void setSubjectCategoryName3(String subjectCategoryName3) {
		this.subjectCategoryName3 = subjectCategoryName3;
	}
	public String getSubjectCategoryId3() {
		return subjectCategoryId3;
	}
	public void setSubjectCategoryId3(String subjectCategoryId3) {
		this.subjectCategoryId3 = subjectCategoryId3;
	}
	@Override
	public String toString() {
		return "FirstProjectBasicSituationTA [applierUid=" + applierUid
				+ ", yearCreated=" + yearCreated + ", refereeString="
				+ refereeString + ", projectName=" + projectName
				+ ", secretLevel=" + secretLevel + ", subjectCategoryName1="
				+ subjectCategoryName1 + ", subjectCategoryId1="
				+ subjectCategoryId1 + ", subjectCategoryName2="
				+ subjectCategoryName2 + ", subjectCategoryId2="
				+ subjectCategoryId2 + ", subjectCategoryName3="
				+ subjectCategoryName3 + ", subjectCategoryId3="
				+ subjectCategoryId3 + "]";
	}
	
	
}