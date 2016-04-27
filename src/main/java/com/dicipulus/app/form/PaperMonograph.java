package com.dicipulus.app.form;

/**
 * @author chenyouqi
 *ÂÛÎÄÖø×÷
 */
public class PaperMonograph {
	protected String name;
	protected float influenceFactor;
	protected String yearVolumnPage;
	protected String publishDate;
	protected String correspondAuthor;
	protected String firstAuthor;
	protected String domesticAuthor;
	protected int referTimesBySCI;
	protected int referTimesByOthers;
	protected boolean intellectualPropertyOwnedByState;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getInfluenceFactor() {
		return influenceFactor;
	}
	public void setInfluenceFactor(float influenceFactor) {
		this.influenceFactor = influenceFactor;
	}
	public String getYearVolumnPage() {
		return yearVolumnPage;
	}
	public void setYearVolumnPage(String yearVolumnPage) {
		this.yearVolumnPage = yearVolumnPage;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getCorrespondAuthor() {
		return correspondAuthor;
	}
	public void setCorrespondAuthor(String correspondAuthor) {
		this.correspondAuthor = correspondAuthor;
	}
	public String getFirstAuthor() {
		return firstAuthor;
	}
	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}
	public String getDomesticAuthor() {
		return domesticAuthor;
	}
	public void setDomesticAuthor(String domesticAuthor) {
		this.domesticAuthor = domesticAuthor;
	}
	public int getReferTimesBySCI() {
		return referTimesBySCI;
	}
	public void setReferTimesBySCI(int referTimesBySCI) {
		this.referTimesBySCI = referTimesBySCI;
	}
	public int getReferTimesByOthers() {
		return referTimesByOthers;
	}
	public void setReferTimesByOthers(int referTimesByOthers) {
		this.referTimesByOthers = referTimesByOthers;
	}
	public boolean isIntellectualPropertyOwnedByState() {
		return intellectualPropertyOwnedByState;
	}
	public void setIntellectualPropertyOwnedByState(
			boolean intellectualPropertyOwnedByState) {
		this.intellectualPropertyOwnedByState = intellectualPropertyOwnedByState;
	}
	
}
