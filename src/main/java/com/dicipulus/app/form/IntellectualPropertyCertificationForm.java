package com.dicipulus.app.form;

/**
 * @author chenyouqi
 * 主要知识产权证明目录
 */
public class IntellectualPropertyCertificationForm {
	String catagory;
	String name;
	String nation;
	String numberOfAuthorization;
	String data;//后续可以改成日期类
	String numberOfCertification;
	String obligee;//权利人
	String inventor;
	String status;
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNumberOfAuthorization() {
		return numberOfAuthorization;
	}
	public void setNumberOfAuthorization(String numberOfAuthorization) {
		this.numberOfAuthorization = numberOfAuthorization;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNumberOfCertification() {
		return numberOfCertification;
	}
	public void setNumberOfCertification(String numberOfCertification) {
		this.numberOfCertification = numberOfCertification;
	}
	public String getObligee() {
		return obligee;
	}
	public void setObligee(String obligee) {
		this.obligee = obligee;
	}
	public String getInventor() {
		return inventor;
	}
	public void setInventor(String inventor) {
		this.inventor = inventor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
