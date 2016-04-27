package com.dicipulus.app.form;

/**
 * @author chenyouqi
 *推广应用情况、经济效益和社会效益
 */
public class PopularizationApplyAndBenefitsForm {
	String applyUnits;
	String applyTechnique;
	String startTime;
	String contact;
	String situation;
	EconomicBenefit[] economicBenefit;
	String economicBenefitIndicatorINstruction;
	String otherEconomicBenefitIndicatorINstruction;
	public String getApplyTechnique() {
		return applyTechnique;
	}
	public void setApplyTechnique(String applyTechnique) {
		this.applyTechnique = applyTechnique;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	
	public String getApplyUnits() {
		return applyUnits;
	}
	public void setApplyUnits(String applyUnits) {
		this.applyUnits = applyUnits;
	}
	public EconomicBenefit[] getEconomicBenefit() {
		return economicBenefit;
	}
	public void setEconomicBenefit(EconomicBenefit[] economicBenefit) {
		this.economicBenefit = economicBenefit;
	}
	public String getEconomicBenefitIndicatorINstruction() {
		return economicBenefitIndicatorINstruction;
	}
	public void setEconomicBenefitIndicatorINstruction(
			String economicBenefitIndicatorINstruction) {
		this.economicBenefitIndicatorINstruction = economicBenefitIndicatorINstruction;
	}
	public String getOtherEconomicBenefitIndicatorINstruction() {
		return otherEconomicBenefitIndicatorINstruction;
	}
	public void setOtherEconomicBenefitIndicatorINstruction(
			String otherEconomicBenefitIndicatorINstruction) {
		this.otherEconomicBenefitIndicatorINstruction = otherEconomicBenefitIndicatorINstruction;
	}
	
}
