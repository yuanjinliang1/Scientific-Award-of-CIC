package com.dicipulus.app.applicationModel;



import java.util.Date;
import java.util.List;





import org.springframework.format.annotation.DateTimeFormat;

import com.dicipulus.app.model.*;

/*
 * �Ƽ��������� һ����Ŀ�������
 */

public class FirstProjectBasicSituation {

	protected String applierUid;// ��ĿID

	/*
	 * �������
	 */
	protected int yearCreated;//�������
	
	protected String refereeString;// �Ƽ���λ���Ƽ�ר��
	protected String projectName;// ��Ŀ����
	protected String majorContributorNames;// ��Ҫ�����//��String����
	protected String majorContributingOrgNames;// ��Ҫ��ɵ�λ
	protected String secretLevel = "����";// ��Ŀ�ܼ�
	protected String subjectCategoryName1;// ѧ�Ʒ�����������
	protected String subjectCategoryId1;// ѧ�Ʒ���id����
	protected String subjectCategoryName2;// ѧ�Ʒ�����������
	protected String subjectCategoryId2;// ѧ�Ʒ���id����
	protected String subjectCategoryName3;// ѧ�Ʒ�����������
	protected String subjectCategoryId3;// ѧ�Ʒ���id����
	
	protected String economicField;// �������񾭼���ҵ,TA,TI
	protected String nationalFocusField;// ���������ص㷢չ����,TA,TI
	protected String technologicalField;// ������ѧ��������,NS
	
	
	protected String taskSource;// ������Դ
	protected String NameAndCodeOfPlansOrFundations;// ����ƻ�����������ƺͱ��
	protected String technicalReportNumber;// �ѳʽ��ĿƼ�������
	
	protected int numOfInventionPatent;// ��Ȩ����ר������,TA,TI
	protected int numOfOtherIntellectualProperty;// ��Ȩ������֪ʶ��Ȩ����,TA,TI
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	protected Date startDate;// ��Ŀ��ʼʱ��
	@DateTimeFormat(pattern = "yyy-MM-dd")
	protected Date finishDate;// ��Ŀ���ʱ��
	protected String refereeContactName;// �Ƽ���λ��ϵ�ˣ������������绰�������
	protected String refereeContactPhone;
	protected String refereeContactEmail;
	protected String applierContactName;// ��Ŀ��ϵ�ˣ������������绰�������
	protected String applierContactPhone;
	protected String applierContactEmail;
	
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

	public String getMajorContributorNames() {
		return majorContributorNames;
	}

	public void setMajorContributorNames(String majorContributorNames) {
		this.majorContributorNames = majorContributorNames;
	}

	public String getMajorContributingOrgNames() {
		return majorContributingOrgNames;
	}

	public void setMajorContributingOrgNames(String majorContributingOrgNames) {
		this.majorContributingOrgNames = majorContributingOrgNames;
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

	public String getEconomicField() {
		return economicField;
	}

	public void setEconomicField(String economicField) {
		this.economicField = economicField;
	}

	public String getNationalFocusField() {
		return nationalFocusField;
	}

	public void setNationalFocusField(String nationalFocusField) {
		this.nationalFocusField = nationalFocusField;
	}
	
	public String getTechnologicalField() {
		return technologicalField;
	}

	public void setTechnologicalField(String technologicalField) {
		this.technologicalField = technologicalField;
	}

	public String getTaskSource() {
		return taskSource;
	}

	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}

	public String getNameAndCodeOfPlansOrFundations() {
		return NameAndCodeOfPlansOrFundations;
	}

	public void setNameAndCodeOfPlansOrFundations(
			String nameAndCodeOfPlansOrFundations) {
		NameAndCodeOfPlansOrFundations = nameAndCodeOfPlansOrFundations;
	}

	public String getTechnicalReportNumber() {
		return technicalReportNumber;
	}

	public void setTechnicalReportNumber(String technicalReportNumber) {
		this.technicalReportNumber = technicalReportNumber;
	}

	public int getNumOfInventionPatent() {
		return numOfInventionPatent;
	}

	public void setNumOfInventionPatent(int numOfInventionPatent) {
		this.numOfInventionPatent = numOfInventionPatent;
	}

	public int getNumOfOtherIntellectualProperty() {
		return numOfOtherIntellectualProperty;
	}

	public void setNumOfOtherIntellectualProperty(int numOfOtherIntellectualProperty) {
		this.numOfOtherIntellectualProperty = numOfOtherIntellectualProperty;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getRefereeContactName() {
		return refereeContactName;
	}

	public void setRefereeContactName(String refereeContactName) {
		this.refereeContactName = refereeContactName;
	}

	public String getRefereeContactPhone() {
		return refereeContactPhone;
	}

	public void setRefereeContactPhone(String refereeContactPhone) {
		this.refereeContactPhone = refereeContactPhone;
	}

	public String getRefereeContactEmail() {
		return refereeContactEmail;
	}

	public void setRefereeContactEmail(String refereeContactEmail) {
		this.refereeContactEmail = refereeContactEmail;
	}

	public String getApplierContactName() {
		return applierContactName;
	}

	public void setApplierContactName(String applierContactName) {
		this.applierContactName = applierContactName;
	}

	public String getApplierContactPhone() {
		return applierContactPhone;
	}

	public void setApplierContactPhone(String applierContactPhone) {
		this.applierContactPhone = applierContactPhone;
	}

	public String getApplierContactEmail() {
		return applierContactEmail;
	}

	public void setApplierContactEmail(String applierContactEmail) {
		this.applierContactEmail = applierContactEmail;
	}

	@Override
	public String toString() {
		return "FirstProjectBasicSituation [applierUid=" + applierUid
				+ ", yearCreated=" + yearCreated + ", refereeString="
				+ refereeString + ", projectName=" + projectName
				+ ", majorContributorNames=" + majorContributorNames
				+ ", majorContributingOrgNames=" + majorContributingOrgNames
				+ ", secretLevel=" + secretLevel + ", subjectCategoryName1="
				+ subjectCategoryName1 + ", subjectCategoryId1="
				+ subjectCategoryId1 + ", subjectCategoryName2="
				+ subjectCategoryName2 + ", subjectCategoryId2="
				+ subjectCategoryId2 + ", subjectCategoryName3="
				+ subjectCategoryName3 + ", subjectCategoryId3="
				+ subjectCategoryId3 + ", economicField=" + economicField
				+ ", nationalFocusField=" + nationalFocusField
				+ ", taskSource=" + taskSource
				+ ", NameAndCodeOfPlansOrFundations="
				+ NameAndCodeOfPlansOrFundations + ", technicalReportNumber="
				+ technicalReportNumber + ", numOfInventionPatent="
				+ numOfInventionPatent + ", numOfOtherIntellectualProperty="
				+ numOfOtherIntellectualProperty + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", refereeContactName="
				+ refereeContactName + ", refereeContactPhone="
				+ refereeContactPhone + ", refereeContactEmail="
				+ refereeContactEmail + ", applierContactName="
				+ applierContactName + ", applierContactPhone="
				+ applierContactPhone + ", applierContactEmail="
				+ applierContactEmail + "]";
	}
	
	
	
	
}