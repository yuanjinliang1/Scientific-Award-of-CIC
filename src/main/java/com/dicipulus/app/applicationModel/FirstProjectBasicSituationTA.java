package com.dicipulus.app.applicationModel;

import java.util.Date;
import java.util.List;

import com.dicipulus.app.model.*;

/*
 * �Ƽ��������� һ����Ŀ�������
 */

public class FirstProjectBasicSituationTA {

	protected String applierUid;// ��ĿID

	/*
	 * �������
	 */
	protected int yearCreated;//�������
	
	protected String refereeString;// �Ƽ���λ���Ƽ�ר��
	protected String projectName;// ��Ŀ����
	//protected List<String> majorContributorNames;// ��Ҫ�����
	//protected List<String> majorContributingOrgNames;// ��Ҫ��ɵ�λ
	protected String secretLevel = "����";// ��Ŀ�ܼ�
	protected String subjectCategoryName1;// ѧ�Ʒ�����������
	protected String subjectCategoryId1;// ѧ�Ʒ���id����
	protected String subjectCategoryName2;// ѧ�Ʒ�����������
	protected String subjectCategoryId2;// ѧ�Ʒ���id����
	protected String subjectCategoryName3;// ѧ�Ʒ�����������
	protected String subjectCategoryId3;// ѧ�Ʒ���id����
	/*
	protected String economicField;// �������񾭼���ҵ
	protected String nationalFocusField;// ���������ص㷢չ����
	protected String taskSource;// ������Դ
	protected String NameAndCodeOfPlansOrFundations;// ����ƻ�����������ƺͱ��
	protected String technicalReportNumber;// �ѳʽ��ĿƼ�������
	protected int numOfInventionPatent;// ��Ȩ����ר������
	protected int numOfOtherIntellectualProperty;// ��Ȩ������֪ʶ��Ȩ����
	protected Date startDate;// ��Ŀ��ʼʱ��
	protected Date finishDate;// ��Ŀ���ʱ��
	protected String refereeContactName;// �Ƽ���λ��ϵ�ˣ������������绰�������
	protected String refereeContactPhone;
	protected String refereeContactEmail;
	protected String applierContactName;// ��Ŀ��ϵ�ˣ������������绰�������
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