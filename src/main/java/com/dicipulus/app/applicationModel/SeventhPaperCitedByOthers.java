package com.dicipulus.app.applicationModel;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SeventhPaperCitedByOthers{
	private int idOfSeventhPaperForm;
	private String applierUid;
	private int rankOfPaper;
	
	private String doiOfPaper;//��������������ר�����
	private String titleAndAuthorOfPaper;//������Ŀ/����
	private String journalAndIF;//���Ŀ���/Ӱ������
	private String publishDate;//���ķ���ʱ�䣨�� �� �գ�
	
	
	public int getIdOfSeventhPaperForm() {
		return idOfSeventhPaperForm;
	}
	public void setIdOfSeventhPaperForm(int idOfSeventhPaperForm) {
		this.idOfSeventhPaperForm = idOfSeventhPaperForm;
	}
	public String getApplierUid() {
		return applierUid;
	}
	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}
	public int getRankOfPaper() {
		return rankOfPaper;
	}
	public void setRankOfPaper(int rankOfPaper) {
		this.rankOfPaper = rankOfPaper;
	}
	public String getDoiOfPaper() {
		return doiOfPaper;
	}
	public void setDoiOfPaper(String doiOfPaper) {
		this.doiOfPaper = doiOfPaper;
	}
	public String getTitleAndAuthorOfPaper() {
		return titleAndAuthorOfPaper;
	}
	public void setTitleAndAuthorOfPaper(String titleAndAuthorOfPaper) {
		this.titleAndAuthorOfPaper = titleAndAuthorOfPaper;
	}
	public String getJournalAndIF() {
		return journalAndIF;
	}
	public void setJournalAndIF(String journalAndIF) {
		this.journalAndIF = journalAndIF;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	

}