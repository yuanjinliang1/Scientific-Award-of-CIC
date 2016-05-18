package com.dicipulus.app.ajax;

import com.fasterxml.jackson.annotation.JsonView;

public class AjaxApplication {
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
	
	
}
