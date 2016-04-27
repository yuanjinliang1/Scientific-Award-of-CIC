package com.dicipulus.app.form;

import javax.print.attribute.standard.Finishings;

/**
 * @author chenyouqi
 *完成单位
 */
public class Unit {
	String unitName;
	int rank;//排名
	String legalRepresentative;//法人
	String place;
	String unitAttribute;
	String fax;
	String zipCode;
	boolean isMemberOfCic;
	String address;
	String identifierOfCic;
	String contact;
	String phoneNumber;
	String moblie;
	String email;
	String contrubution;//<=600
	
	String finishUnitDeclarations=Constants.FINISHUNITDECLARATIONS;
	
	public String getFinishUnitDeclarations() {
		return finishUnitDeclarations;
	}
	public void setFinishUnitDeclarations(String finishUnitDeclarations) {
		this.finishUnitDeclarations = finishUnitDeclarations;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getUnitAttribute() {
		return unitAttribute;
	}
	public void setUnitAttribute(String unitAttribute) {
		this.unitAttribute = unitAttribute;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public boolean isMemberOfCic() {
		return isMemberOfCic;
	}
	public void setMemberOfCic(boolean isMemberOfCic) {
		this.isMemberOfCic = isMemberOfCic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentifierOfCic() {
		return identifierOfCic;
	}
	public void setIdentifierOfCic(String identifierOfCic) {
		this.identifierOfCic = identifierOfCic;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrubution() {
		return contrubution;
	}
	public void setContrubution(String contrubution) {
		this.contrubution = contrubution;
	}

	
	
}
