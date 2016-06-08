package com.dicipulus.app.applicationModel;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EighthMajorContributor {
	private int idOfEighthForm;
	private String applierUid;
	
	private String nameOfContributor;
	private String genderOfContributor;
	private String rankOfContributor;
	private String nationalityOfContributor;
	
	private String isMemberOfCIC;//TI,NS
	private String memberIdOfCIc;//TI,NS
	
	private String birthdayOfContributor;//to be revised
	private String birthPlaceOfContributor;
	private String nationOfContributor;
	private String citizenIdOfContributor;
	private String isReturnedFormOverseas;
	private String returnDate;
	private String technicalTitleOfContributor;
	private String highestEducationOfContributor;
	private String highestDegreeOfContributor;
	private String universityOfContributor;
	private String graduateDateOfContributor;
	private String majorOfContributor;//所学专业
	private String emailOfContributor;
	private String officePhoneOfContributor;
	private String mobileOfContributor;
	private String addressOfContributor;
	private String zipCodeOfContributor;
	private String workUnitOfContributor;
	private String administrativePositionOfContributor;
	private String subWorkUnitOfContributor;
	private String policitalPartyOfContributor;
	private String completeUnitOfContributor;
	private String locationOfContributor;
	private String typeOfUnit;//单位性质
	private String startDateOfParticipation;
	private String endDateOfParticipation;
	private String contributionOfContributor;
	private String formerRewardOfCIC;
	
	public void setStartDateOfParticipation(String startDateOfParticipation) {
		this.startDateOfParticipation = startDateOfParticipation;
	}
	public void setEndDateOfParticipation(String endDateOfParticipation) {
		this.endDateOfParticipation = endDateOfParticipation;
	}
	
	public String getStartDateOfParticipation() {
		return startDateOfParticipation;
	}
	public String getEndDateOfParticipation() {
		return endDateOfParticipation;
	}
	public int getIdOfEighthForm() {
		return idOfEighthForm;
	}
	public void setIdOfEighthForm(int idOfEighthForm) {
		this.idOfEighthForm = idOfEighthForm;
	}
	public String getApplierUid() {
		return applierUid;
	}
	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}
	public String getNameOfContributor() {
		return nameOfContributor;
	}
	public void setNameOfContributor(String nameOfContributor) {
		this.nameOfContributor = nameOfContributor;
	}
	public String getGenderOfContributor() {
		return genderOfContributor;
	}
	public void setGenderOfContributor(String genderOfContributor) {
		this.genderOfContributor = genderOfContributor;
	}
	public String getRankOfContributor() {
		return rankOfContributor;
	}
	public void setRankOfContributor(String rankOfContributor) {
		this.rankOfContributor = rankOfContributor;
	}
	public String getNationalityOfContributor() {
		return nationalityOfContributor;
	}
	public void setNationalityOfContributor(String nationalityOfContributor) {
		this.nationalityOfContributor = nationalityOfContributor;
	}
	public String getIsMemberOfCIC() {
		return isMemberOfCIC;
	}
	public void setIsMemberOfCIC(String isMemberOfCIC) {
		this.isMemberOfCIC = isMemberOfCIC;
	}
	public String getMemberIdOfCIc() {
		return memberIdOfCIc;
	}
	public void setMemberIdOfCIc(String memberIdOfCIc) {
		this.memberIdOfCIc = memberIdOfCIc;
	}
	public String getBirthdayOfContributor() {
		return birthdayOfContributor;
	}
	public void setBirthdayOfContributor(String birthdayOfContributor) {
		this.birthdayOfContributor = birthdayOfContributor;
	}
	public String getBirthPlaceOfContributor() {
		return birthPlaceOfContributor;
	}
	public void setBirthPlaceOfContributor(String birthPlaceOfContributor) {
		this.birthPlaceOfContributor = birthPlaceOfContributor;
	}
	public String getNationOfContributor() {
		return nationOfContributor;
	}
	public void setNationOfContributor(String nationOfContributor) {
		this.nationOfContributor = nationOfContributor;
	}
	public String getCitizenIdOfContributor() {
		return citizenIdOfContributor;
	}
	public void setCitizenIdOfContributor(String citizenIdOfContributor) {
		this.citizenIdOfContributor = citizenIdOfContributor;
	}
	public String getIsReturnedFormOverseas() {
		return isReturnedFormOverseas;
	}
	public void setIsReturnedFormOverseas(String isReturnedFormOverseas) {
		this.isReturnedFormOverseas = isReturnedFormOverseas;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getTechnicalTitleOfContributor() {
		return technicalTitleOfContributor;
	}
	public void setTechnicalTitleOfContributor(String technicalTitleOfContributor) {
		this.technicalTitleOfContributor = technicalTitleOfContributor;
	}
	public String getHighestEducationOfContributor() {
		return highestEducationOfContributor;
	}
	public void setHighestEducationOfContributor(
			String highestEducationOfContributor) {
		this.highestEducationOfContributor = highestEducationOfContributor;
	}
	public String getHighestDegreeOfContributor() {
		return highestDegreeOfContributor;
	}
	public void setHighestDegreeOfContributor(String highestDegreeOfContributor) {
		this.highestDegreeOfContributor = highestDegreeOfContributor;
	}
	public String getUniversityOfContributor() {
		return universityOfContributor;
	}
	public void setUniversityOfContributor(String universityOfContributor) {
		this.universityOfContributor = universityOfContributor;
	}
	public String getGraduateDateOfContributor() {
		return graduateDateOfContributor;
	}
	public void setGraduateDateOfContributor(String graduateDateOfContributor) {
		this.graduateDateOfContributor = graduateDateOfContributor;
	}
	public String getMajorOfContributor() {
		return majorOfContributor;
	}
	public void setMajorOfContributor(String majorOfContributor) {
		this.majorOfContributor = majorOfContributor;
	}
	public String getEmailOfContributor() {
		return emailOfContributor;
	}
	public void setEmailOfContributor(String emailOfContributor) {
		this.emailOfContributor = emailOfContributor;
	}
	public String getOfficePhoneOfContributor() {
		return officePhoneOfContributor;
	}
	public void setOfficePhoneOfContributor(String officePhoneOfContributor) {
		this.officePhoneOfContributor = officePhoneOfContributor;
	}
	public String getMobileOfContributor() {
		return mobileOfContributor;
	}
	public void setMobileOfContributor(String mobileOfContributor) {
		this.mobileOfContributor = mobileOfContributor;
	}
	public String getAddressOfContributor() {
		return addressOfContributor;
	}
	public void setAddressOfContributor(String addressOfContributor) {
		this.addressOfContributor = addressOfContributor;
	}
	public String getZipCodeOfContributor() {
		return zipCodeOfContributor;
	}
	public void setZipCodeOfContributor(String zipCodeOfContributor) {
		this.zipCodeOfContributor = zipCodeOfContributor;
	}
	public String getWorkUnitOfContributor() {
		return workUnitOfContributor;
	}
	public void setWorkUnitOfContributor(String workUnitOfContributor) {
		this.workUnitOfContributor = workUnitOfContributor;
	}
	public String getAdministrativePositionOfContributor() {
		return administrativePositionOfContributor;
	}
	public void setAdministrativePositionOfContributor(
			String administrativePositionOfContributor) {
		this.administrativePositionOfContributor = administrativePositionOfContributor;
	}
	public String getSubWorkUnitOfContributor() {
		return subWorkUnitOfContributor;
	}
	public void setSubWorkUnitOfContributor(String subWorkUnitOfContributor) {
		this.subWorkUnitOfContributor = subWorkUnitOfContributor;
	}
	public String getPolicitalPartyOfContributor() {
		return policitalPartyOfContributor;
	}
	public void setPolicitalPartyOfContributor(String policitalPartyOfContributor) {
		this.policitalPartyOfContributor = policitalPartyOfContributor;
	}
	public String getCompleteUnitOfContributor() {
		return completeUnitOfContributor;
	}
	public void setCompleteUnitOfContributor(String completeUnitOfContributor) {
		this.completeUnitOfContributor = completeUnitOfContributor;
	}
	public String getLocationOfContributor() {
		return locationOfContributor;
	}
	public void setLocationOfContributor(String locationOfContributor) {
		this.locationOfContributor = locationOfContributor;
	}
	public String getTypeOfUnit() {
		return typeOfUnit;
	}
	public void setTypeOfUnit(String typeOfUnit) {
		this.typeOfUnit = typeOfUnit;
	}
	
	public String getContributionOfContributor() {
		return contributionOfContributor;
	}
	public void setContributionOfContributor(String contributionOfContributor) {
		this.contributionOfContributor = contributionOfContributor;
	}
	public String getFormerRewardOfCIC() {
		return formerRewardOfCIC;
	}
	public void setFormerRewardOfCIC(String formerRewardOfCIC) {
		this.formerRewardOfCIC = formerRewardOfCIC;
	}
	
	
	
	
	
}