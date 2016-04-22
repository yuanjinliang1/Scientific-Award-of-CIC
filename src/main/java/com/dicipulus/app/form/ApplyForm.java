package com.dicipulus.app.form;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyouqi
 *
 */
public class ApplyForm {
	protected String projectName;//��Ŀ����
	protected String chargerName;//��Ҫ���������
	protected Map<String,String> subjectCategory=new HashMap<String,String>();//ѧ�Ʒ���value�����ƣ�key�Ǵ���
	protected String technicalField;//������ѧ����
	protected String taskSource;//������Դ
	protected String identify;//����ƻ�����������ƺͱ��
	protected String numberOfTechnologyReport;//�ѳɽ��ĿƼ�������
	protected String startData;//��Ŀ��ʼ����
	protected String finishData;//��Ŀ�������
	protected String opinionOfRecommendUnit;//�Ƽ���λ���
	protected String briefIntro;//��Ŀ���
	protected String objectiveEvaluation;//�͹�����
	protected Teammate[] mainFinishedPeople;//��Ҫ�����
	protected Unit[] mainFinishedUnit;//��Ҫ��ɵ�λ
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
