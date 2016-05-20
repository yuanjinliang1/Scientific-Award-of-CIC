package com.dicipulus.app.applicationModel;

public class Excel {
	String applierUid;
	int yearCreated;
	String formalityExaminationResult;
	String primaryExaminationResult;
	String finalExaminationResult;
	String projectName;
	String applicationType;
	String refereeString;//有歧义
	//学科代码没有加，有歧义
	String applierContactName;
	String applierContactPhone;
	String applierContactEmail;
//	String addressOfContributor;//表major_contributor中的
	String refereeContactName;
	String refereeContactPhone;
	String postAddress;//secondrefereeunitopinion表中的地址 
	//成果登记
	String referingScienceTechnologyAwardRank;
	String projectStatus;
	
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
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
	public String getFormalityExaminationResult() {
		return formalityExaminationResult;
	}
	public void setFormalityExaminationResult(String formalityExaminationResult) {
		this.formalityExaminationResult = formalityExaminationResult;
	}
	public String getPrimaryExaminationResult() {
		return primaryExaminationResult;
	}
	public void setPrimaryExaminationResult(String primaryExaminationResult) {
		this.primaryExaminationResult = primaryExaminationResult;
	}
	
	public String getFinalExaminationResult() {
		return finalExaminationResult;
	}
	public void setFinalExaminationResult(String finalExaminationResult) {
		this.finalExaminationResult = finalExaminationResult;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getRefereeString() {
		return refereeString;
	}
	public void setRefereeString(String refereeString) {
		this.refereeString = refereeString;
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
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	public String getReferingScienceTechnologyAwardRank() {
		return referingScienceTechnologyAwardRank;
	}
	public void setReferingScienceTechnologyAwardRank(
			String referingScienceTechnologyAwardRank) {
		this.referingScienceTechnologyAwardRank = referingScienceTechnologyAwardRank;
	}
	
	
}
