package com.dicipulus.app.applicationModel;


public class NinethMajorOrgContributor{
	private int idOfNinethForm;//primary key
	private String applierUid;// retrieving key
	
	private String nameOfOrg;
	private int rankOfOrg;
	private String legalRepresentative;
	private String locationOfOrg;
	private String typeOfOrg;
	private String faxOfOrg;
	private String zipCodeOfOrg;
	
	private String isOrgMemberOfCIC;//TI, NS
	private String OrgMemberIDOfCIC;//TI, NS
	
	private String addressOfOrg;
	private String contactNameOfOrg;
	private String contactPhoneOfOrg;
	private String mobileOfOrg;
	private String emailOfOrg;
	private String contributionToProject;
	
	
	public int getIdOfNinethForm() {
		return idOfNinethForm;
	}
	public void setIdOfNinethForm(int idOfNinethForm) {
		this.idOfNinethForm = idOfNinethForm;
	}
	public String getApplierUid() {
		return applierUid;
	}
	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}
	public String getNameOfOrg() {
		return nameOfOrg;
	}
	public void setNameOfOrg(String nameOfOrg) {
		this.nameOfOrg = nameOfOrg;
	}
	public int getRankOfOrg() {
		return rankOfOrg;
	}
	public void setRankOfOrg(int rankOfOrg) {
		this.rankOfOrg = rankOfOrg;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getLocationOfOrg() {
		return locationOfOrg;
	}
	public void setLocationOfOrg(String locationOfOrg) {
		this.locationOfOrg = locationOfOrg;
	}
	public String getTypeOfOrg() {
		return typeOfOrg;
	}
	public void setTypeOfOrg(String typeOfOrg) {
		this.typeOfOrg = typeOfOrg;
	}
	public String getFaxOfOrg() {
		return faxOfOrg;
	}
	public void setFaxOfOrg(String faxOfOrg) {
		this.faxOfOrg = faxOfOrg;
	}
	public String getZipCodeOfOrg() {
		return zipCodeOfOrg;
	}
	public void setZipCodeOfOrg(String zipCodeOfOrg) {
		this.zipCodeOfOrg = zipCodeOfOrg;
	}
	public String getIsOrgMemberOfCIC() {
		return isOrgMemberOfCIC;
	}
	public void setIsOrgMemberOfCIC(String isOrgMemberOfCIC) {
		this.isOrgMemberOfCIC = isOrgMemberOfCIC;
	}
	public String getOrgMemberIDOfCIC() {
		return OrgMemberIDOfCIC;
	}
	public void setOrgMemberIDOfCIC(String orgMemberIDOfCIC) {
		OrgMemberIDOfCIC = orgMemberIDOfCIC;
	}
	public String getAddressOfOrg() {
		return addressOfOrg;
	}
	public void setAddressOfOrg(String addressOfOrg) {
		this.addressOfOrg = addressOfOrg;
	}
	public String getContactNameOfOrg() {
		return contactNameOfOrg;
	}
	public void setContactNameOfOrg(String contactNameOfOrg) {
		this.contactNameOfOrg = contactNameOfOrg;
	}
	public String getContactPhoneOfOrg() {
		return contactPhoneOfOrg;
	}
	public void setContactPhoneOfOrg(String contactPhoneOfOrg) {
		this.contactPhoneOfOrg = contactPhoneOfOrg;
	}
	public String getMobileOfOrg() {
		return mobileOfOrg;
	}
	public void setMobileOfOrg(String mobileOfOrg) {
		this.mobileOfOrg = mobileOfOrg;
	}
	public String getEmailOfOrg() {
		return emailOfOrg;
	}
	public void setEmailOfOrg(String emailOfOrg) {
		this.emailOfOrg = emailOfOrg;
	}
	public String getContributionToProject() {
		return contributionToProject;
	}
	public void setContributionToProject(String contributionToProject) {
		this.contributionToProject = contributionToProject;
	}
	@Override
	public String toString() {
		return "NinethMajorOrganizationContributor [idOfNinethForm="
				+ idOfNinethForm + ", applierUid=" + applierUid + ", nameOfOrg="
				+ nameOfOrg + ", rankOfOrg=" + rankOfOrg
				+ ", legalRepresentative=" + legalRepresentative
				+ ", locationOfOrg=" + locationOfOrg + ", typeOfOrg="
				+ typeOfOrg + ", faxOfOrg=" + faxOfOrg + ", zipCodeOfOrg="
				+ zipCodeOfOrg + ", isOrgMemberOfCIC=" + isOrgMemberOfCIC
				+ ", OrgMemberIDOfCIC=" + OrgMemberIDOfCIC + ", addressOfOrg="
				+ addressOfOrg + ", contactNameOfOrg=" + contactNameOfOrg
				+ ", contactPhoneOfOrg=" + contactPhoneOfOrg + ", mobileOfOrg="
				+ mobileOfOrg + ", emailOfOrg=" + emailOfOrg
				+ ", contributionToProject=" + contributionToProject + "]";
	}
	
	
	
}