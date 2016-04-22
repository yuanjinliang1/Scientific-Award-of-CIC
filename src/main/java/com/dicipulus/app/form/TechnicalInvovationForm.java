package com.dicipulus.app.form;
/**
 * @author chenyouqi
 *技术发明类
 */
public class TechnicalInvovationForm extends ApplyForm{
	protected String mainTechnologyInvention;
	protected String technicalLimitation;
	protected PopularizationApplyAndBenefitsForm popularizationApplyAndBenefit;
	protected IntellectualPropertyCertificationForm[] intellectualPropertyCertification;
	public String getMainTechnologyInvention() {
		return mainTechnologyInvention;
	}
	public void setMainTechnologyInvention(String mainTechnologyInvention) {
		this.mainTechnologyInvention = mainTechnologyInvention;
	}
	public String getTechnicalLimitation() {
		return technicalLimitation;
	}
	public void setTechnicalLimitation(String technicalLimitation) {
		this.technicalLimitation = technicalLimitation;
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
