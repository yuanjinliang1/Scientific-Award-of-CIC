package com.dicipulus.app.applicationModel;
/*
 *�Ƽ�������  �����Ƽ���λ���
 */
public class SecondRefereeUnitOpinion {
	private String applierUid;
	
	protected String refereeUnitName;//�Ƽ���λ����
	protected String postAddress;//ͨѶ��ַ
	protected String zipCode;//�ʱ�
	protected String contact;//��ϵ��
	protected String phoneNumber;//��ϵ�绰
	protected String email;//��������
	protected String fax;//����
	protected String recommendOpinion;//<==600
	protected String declaration=Constants.REFEREEUNITOPINIONDECLARATIONS;//����
	protected String referingScienceTechnologyAwardRank;
	
	public String getApplierUid() {
		return applierUid;
	}
	public void setApplierUid(String applierUid) {
		this.applierUid = applierUid;
	}
	public String getRefereeUnitName() {
		return refereeUnitName;
	}
	public void setRefereeUnitName(String refereeUnitName) {
		this.refereeUnitName = refereeUnitName;
	}
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getRecommendOpinion() {
		return recommendOpinion;
	}
	public void setRecommendOpinion(String recommendOpinion) {
		this.recommendOpinion = recommendOpinion;
	}
	
	public String getReferingScienceTechnologyAwardRank() {
		return referingScienceTechnologyAwardRank;
	}
	public void setReferingScienceTechnologyAwardRank(
			String referingScienceTechnologyAwardRank) {
		this.referingScienceTechnologyAwardRank = referingScienceTechnologyAwardRank;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	
}
