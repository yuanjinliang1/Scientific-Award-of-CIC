package com.dicipulus.app.form;

/**
 * @author chenyouqi
 *科技进步类
 */
public class TechnologicalProgressForm extends ApplyForm{
	protected String technologyInnovation;
	protected String technologyLimited;
	protected PopularizationApplyAndBenefitsForm popularizationApplyAndBenefit;
	protected IntellectualPropertyCertificationForm[] intellectualPropertyCertification;
	public String getTechnologyInnovation() {
		return technologyInnovation;
	}
	public void setTechnologyInnovation(String technologyInnovation) {
		this.technologyInnovation = technologyInnovation;
	}
	public String getTechnologyLimited() {
		return technologyLimited;
	}
	public void setTechnologyLimited(String technologyLimited) {
		this.technologyLimited = technologyLimited;
	}
	public PopularizationApplyAndBenefitsForm getPopularizationApplyAndBenefit() {
		return popularizationApplyAndBenefit;
	}
	public void setPopularizationApplyAndBenefit(
			PopularizationApplyAndBenefitsForm popularizationApplyAndBenefit) {
		this.popularizationApplyAndBenefit = popularizationApplyAndBenefit;
	}
	public IntellectualPropertyCertificationForm[] getIntellectualPropertyCertification() {
		return intellectualPropertyCertification;
	}
	public void setIntellectualPropertyCertification(
			IntellectualPropertyCertificationForm[] intellectualPropertyCertification) {
		this.intellectualPropertyCertification = intellectualPropertyCertification;
	}
	
}
