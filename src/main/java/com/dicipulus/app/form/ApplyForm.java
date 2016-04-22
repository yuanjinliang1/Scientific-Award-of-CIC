package com.dicipulus.app.form;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyouqi
 *
 */
public class ApplyForm {
	protected String projectName;//项目名称
	protected String chargerName;//主要完成人姓名
	protected Map<String,String> subjectCategory=new HashMap<String,String>();//学科分类value是名称，key是代码
	protected String technicalField;//所属科学领域
	protected String taskSource;//任务来源
	protected String identify;//具体计划、基金的名称和编号
	protected String numberOfTechnologyReport;//已成交的科技报告编号
	protected String startData;//项目开始日期
	protected String finishData;//项目完成日期
	protected String opinionOfRecommendUnit;//推荐单位意见
	protected String briefIntro;//项目简介
	protected String objectiveEvaluation;//客观评价
	protected Teammate[] mainFinishedPeople;//主要完成人
	protected Unit[] mainFinishedUnit;//主要完成单位
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getChargerName() {
		return chargerName;
	}
	public void setChargerName(String chargerName) {
		this.chargerName = chargerName;
	}
	public Map<String, String> getSubjectCategory() {
		return subjectCategory;
	}
	public void setSubjectCategory(Map<String, String> subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	public String getTechnicalField() {
		return technicalField;
	}
	public void setTechnicalField(String technicalField) {
		this.technicalField = technicalField;
	}
	public String getTaskSource() {
		return taskSource;
	}
	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getNumberOfTechnologyReport() {
		return numberOfTechnologyReport;
	}
	public void setNumberOfTechnologyReport(String numberOfTechnologyReport) {
		this.numberOfTechnologyReport = numberOfTechnologyReport;
	}
	public String getStartData() {
		return startData;
	}
	public void setStartData(String startData) {
		this.startData = startData;
	}
	public String getFinishData() {
		return finishData;
	}
	public void setFinishData(String finishData) {
		this.finishData = finishData;
	}
	public String getOpinionOfRecommendUnit() {
		return opinionOfRecommendUnit;
	}
	public void setOpinionOfRecommendUnit(String opinionOfRecommendUnit) {
		this.opinionOfRecommendUnit = opinionOfRecommendUnit;
	}
	public String getBriefIntro() {
		return briefIntro;
	}
	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}
	public String getObjectiveEvaluation() {
		return objectiveEvaluation;
	}
	public void setObjectiveEvaluation(String objectiveEvaluation) {
		this.objectiveEvaluation = objectiveEvaluation;
	}
	public Teammate[] getMainFinishedPeople() {
		return mainFinishedPeople;
	}
	public void setMainFinishedPeople(Teammate[] mainFinishedPeople) {
		this.mainFinishedPeople = mainFinishedPeople;
	}
	public Unit[] getMainFinishedUnit() {
		return mainFinishedUnit;
	}
	public void setMainFinishedUnit(Unit[] mainFinishedUnit) {
		this.mainFinishedUnit = mainFinishedUnit;
	}
	
}
