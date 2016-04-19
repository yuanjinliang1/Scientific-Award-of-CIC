package com.dicipulus.app.model;

import java.util.Set;

public class Referee extends Person {
	
	private String role="referee";
	
	private String category;
	
	private String telephone;
	
	private String address;
	
	private Set<Applier> appliers;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Applier> getAppliers() {
		return appliers;
	}

	public void setAppliers(Set<Applier> appliers) {
		this.appliers = appliers;
	}
	
	public void addApplier(Applier applier){
		getAppliers().add(applier);
		applier.setOwner(this);
	}
	
	public Applier getApplier(String uid){
		for(Applier applier:getAppliers()){
			if(applier.getUid().equals(uid)){
				return applier;
			}
		}
		return null;
	}
}