package com.dicipulus.app.model;

import com.dicipulus.app.ajax.AjaxViews;
import com.fasterxml.jackson.annotation.JsonView;

public class Application {
	@JsonView(AjaxViews.Public.class)
	private String applierUid;
	@JsonView(AjaxViews.Public.class)
	private String projectStatus;
	@JsonView(AjaxViews.Public.class)
	private String projectName;
	@JsonView(AjaxViews.Public.class)
	private String refereeString;
	@JsonView(AjaxViews.Public.class)
	private String applicationType;
	@JsonView(AjaxViews.Public.class)
	private String referingScienceTechnologyAwardRank;
	@JsonView(AjaxViews.Public.class)
	private String formalityExaminationResult;
	@JsonView(AjaxViews.Public.class)
	private String primaryExaminationResult;
	@JsonView(AjaxViews.Public.class)
	private String finalExaminationResult;
	@JsonView(AjaxViews.Public.class)
	private String commentOfAdmin;
	private String yearCreated;//用于隐藏去年的项目
	
	public Application(String applierUid, String projectStatus,
			String projectName, String refereeString, String applicationType,
			String referingScienceTechnologyAwardRank,
			String formalityExaminationResult, String primaryExaminationResult,
			String finalExaminationResult, String commentOfAdmin,
			String yearCreated) {
		super();
		this.applierUid = applierUid;
		this.projectStatus = projectStatus;
		this.projectName = projectName;
		this.refereeString = refereeString;
		this.applicationType = applicationType;
		this.referingScienceTechnologyAwardRank = referingScienceTechnologyAwardRank;
		this.formalityExaminationResult = formalityExaminationResult;
		this.primaryExaminationResult = primaryExaminationResult;
		this.finalExaminationResult = finalExaminationResult;
		this.commentOfAdmin = commentOfAdmin;
		this.yearCreated = yearCreated;
	}
	
	public Application(){
		
	}





	public String getApplierUid() {
		return applierUid;
	}

	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRefereeString() {
		return refereeString;
	}

	public void setRefereeString(String refereeString) {
		this.refereeString = refereeString;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getReferingScienceTechnologyAwardRank() {
		return referingScienceTechnologyAwardRank;
	}

	public void setReferingScienceTechnologyAwardRank(
			String referingScienceTechnologyAwardRank) {
		this.referingScienceTechnologyAwardRank = referingScienceTechnologyAwardRank;
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

	public String getCommentOfAdmin() {
		return commentOfAdmin;
	}

	public void setCommentOfAdmin(String commentOfAdmin) {
		this.commentOfAdmin = commentOfAdmin;
	}

	public String getYearCreated() {
		return yearCreated;
	}

	public void setYearCreated(String yearCreated) {
		this.yearCreated = yearCreated;
	}

	@Override
	public String toString() {
		return "Application [applierUid=" + applierUid + ", projectStatus="
				+ projectStatus + ", projectName=" + projectName
				+ ", refereeString=" + refereeString + ", applicationType="
				+ applicationType + ", referingScienceTechnologyAwardRank="
				+ referingScienceTechnologyAwardRank
				+ ", formalityExaminationResult=" + formalityExaminationResult
				+ ", primaryExaminationResult=" + primaryExaminationResult
				+ ", finalExaminationResult=" + finalExaminationResult
				+ ", commentOfAdmin=" + commentOfAdmin + ", yearCreated="
				+ yearCreated + "]";
	}
	
	
}
