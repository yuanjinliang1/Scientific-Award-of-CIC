package com.dicipulus.app.form;

/**
 * @author chenyouqi
 *推广应用情况、经济效益和社会效益
 */
public class PopularizationApplyAndBenefitsForm {
	Unit[] applyUnits;
	EconomicBenefit[] economicBenefit;
	String economicBenefitIndicatorINstruction;
	String otherEconomicBenefitIndicatorINstruction;
	public Unit[] getApplyUnits() {
		return applyUnits;
	}
	public void setApplyUnits(Unit[] applyUnits) {
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
