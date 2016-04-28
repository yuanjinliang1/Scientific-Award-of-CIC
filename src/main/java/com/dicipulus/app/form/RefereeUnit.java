package com.dicipulus.app.form;

import com.dicipulus.app.applicationModel.Constants;

/**
 * @author chenyouqi
 *ÍÆ¼öµ¥Î»
 */
public class RefereeUnit {
	String refereeUnit;
	String postalAddress;
	String zipCode;
	String contact;
	String contactNumber;
	String email;
	String faxAddress;
	String referRecommend;
	String declaration=Constants.DECLARATIONS;
	public String getRefereeUnit() {
		return refereeUnit;
	}
	public void setRefereeUnit(String refereeUnit) {
		this.refereeUnit = refereeUnit;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaxAddress() {
		return faxAddress;
	}
	public void setFaxAddress(String faxAddress) {
		this.faxAddress = faxAddress;
	}
	public String getReferRecommend() {
		return referRecommend;
	}
	public void setReferRecommend(String referRecommend) {
		this.referRecommend = referRecommend;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	
}
